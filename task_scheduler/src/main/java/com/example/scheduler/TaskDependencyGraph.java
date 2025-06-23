package com.example.scheduler;

import java.util.*;

public class TaskDependencyGraph {
    private Map<String, List<String>> graph;

    public TaskDependencyGraph() {
        this.graph = new HashMap<>();
    }

    /**
     * Adds a task and its dependencies to the graph.
     * @param taskId Unique ID of the task.
     * @param dependencies List of task IDs that this task depends on.
     */
    public void addTask(String taskId, List<String> dependencies) {
        if (taskId == null || taskId.isEmpty()) {
            throw new IllegalArgumentException("Task ID cannot be null or empty");
        }
        graph.put(taskId, new ArrayList<>(dependencies != null ? dependencies : Collections.emptyList()));
    }

    /**
     * Checks if the graph contains a cycle using DFS.
     * @return true if a cycle is detected, false otherwise.
     */
    public boolean hasCycle() {
        Set<String> visited = new HashSet<>();
        Set<String> recStack = new HashSet<>();
        for (String task : graph.keySet()) {
            if (!visited.contains(task)) {
                if (hasCycleUtil(task, visited, recStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Helper method for DFS-based cycle detection.
     */
    private boolean hasCycleUtil(String task, Set<String> visited, Set<String> recStack) {
        visited.add(task);
        recStack.add(task);
        List<String> dependencies = graph.getOrDefault(task, Collections.emptyList());
        for (String dep : dependencies) {
            if (!visited.contains(dep)) {
                if (hasCycleUtil(dep, visited, recStack)) {
                    return true;
                }
            } else if (recStack.contains(dep)) {
                return true;
            }
        }
        recStack.remove(task);
        return false;
    }

    /**
     * Returns the number of tasks in the graph.
     */
    public int getTaskCount() {
        return graph.size();
    }
}