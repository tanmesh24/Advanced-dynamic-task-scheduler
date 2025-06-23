package com.example.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import java.util.*;

@SpringBootApplication
public class TemporaryApp implements CommandLineRunner {
    @Autowired
    private TaskRepository taskRepository;

    public static void main(String[] args) {
        SpringApplication.run(TemporaryApp.class, args);
    }

    @Override
    public void run(String... args) {
        // Initialize predictive model
        PredictiveModel model = new PredictiveModel();
        double[][] trainingData = {
            {10, 1000}, {20, 2000}, {30, 3000}, {40, 4000}, {50, 5000}
        };
        model.train(trainingData);

        // Create tasks
        Task task1 = new Task();
        task1.setId("task1");
        task1.setName("Task 1");
        task1.setPriority(1);
        task1.setPredictedExecutionTime(model.predict(25)); // Size 25
        task1.setDependencies(List.of("task2"));

        Task task2 = new Task();
        task2.setId("task2");
        task2.setName("Task 2");
        task2.setPriority(2);
        task2.setPredictedExecutionTime(model.predict(20)); // Size 20
        task2.setDependencies(List.of("task3"));

        Task task3 = new Task();
        task3.setId("task3");
        task3.setName("Task 3");
        task3.setPriority(3);
        task3.setPredictedExecutionTime(model.predict(15)); // Size 15
        task3.setDependencies(List.of()); // No dependencies

        // Build dependency graph
        TaskDependencyGraph graph = new TaskDependencyGraph();
        graph.addTask("task1", task1.getDependencies());
        graph.addTask("task2", task2.getDependencies());
        graph.addTask("task3", task3.getDependencies());

        // Check for cycles
        if (graph.hasCycle()) {
            System.out.println("Error: Dependency cycle detected!");
        } else {
            System.out.println("No dependency cycles detected. Saving tasks...");
            taskRepository.save(task1);
            taskRepository.save(task2);
            taskRepository.save(task3);
            System.out.println("Saved task1: " + taskRepository.findById("task1"));
            System.out.println("Saved task2: " + taskRepository.findById("task2"));
            System.out.println("Saved task3: " + taskRepository.findById("task3"));
        }
    }
}