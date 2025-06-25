package com.example.scheduler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class TaskDependencyGraph {
    private final TaskRepository taskRepository;

    public TaskDependencyGraph(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    public boolean hasCycle(String taskId) {
        Set<String> visited = new HashSet<>();
        Set<String> path = new HashSet<>();
        return hasCycleDFS(taskId, visited, path);
    }

    private boolean hasCycleDFS(String taskId, Set<String> visited, Set<String> path) {
        if (path.contains(taskId)) {
            System.out.println("Cycle detected at: " + taskId);
            return true;
        }
        if (visited.contains(taskId)) {
            return false;
        }

        visited.add(taskId);
        path.add(taskId);

        java.util.Optional<Task> taskOpt = taskRepository.findById(taskId);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            String dependencies = task.getDependencies();
            System.out.println("Checking dependencies for " + taskId + ": " + dependencies);
            if (dependencies != null && !dependencies.trim().isEmpty()) {
                String[] depArray = dependencies.split(",");
                for (String depId : depArray) {
                    depId = depId.trim();
                    if (!depId.isEmpty() && hasCycleDFS(depId, visited, path)) {
                        return true;
                    }
                }
            }
        } else {
            System.out.println("Task not found: " + taskId);
        }

        path.remove(taskId);
        return false;
    }
}