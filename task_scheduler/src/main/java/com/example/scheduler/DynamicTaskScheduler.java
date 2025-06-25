package com.example.scheduler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.*;

@Component
public class DynamicTaskScheduler {
    private final TaskRepository taskRepository;
    private final PredictiveModel predictiveModel;
    private final TaskDependencyGraph taskDependencyGraph;
    private final ExecutorService executorService = new ThreadPoolExecutor(
        2, 4, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>()
    );

    public DynamicTaskScheduler(TaskRepository taskRepository, PredictiveModel predictiveModel, TaskDependencyGraph taskDependencyGraph) {
        this.taskRepository = taskRepository;
        this.predictiveModel = predictiveModel;
        this.taskDependencyGraph = taskDependencyGraph;
    }

    @Transactional
    public void submitTask(Task task) {
        taskRepository.save(task); // Save first
        taskRepository.flush(); // Ensure persistence before cycle check
        if (taskDependencyGraph.hasCycle(task.getId())) {
            throw new IllegalStateException("Cycle detected involving task: " + task.getId());
        }
        double predictedTime = predictiveModel.predict(task.getTaskSize());
        task.setPredictedExecutionTime(predictedTime);
        taskRepository.save(task); // Update with predicted time
        executorService.submit(() -> {
            System.out.println("Executing task: " + task.getId());
            try {
                Thread.sleep((long) (predictedTime * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    public PredictiveModel getPredictiveModel() {
        return predictiveModel;
    }

    public TaskDependencyGraph getTaskDependencyGraph() {
        return taskDependencyGraph;
    }
}