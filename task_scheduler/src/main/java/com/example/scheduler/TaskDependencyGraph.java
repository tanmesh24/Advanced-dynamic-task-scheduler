package com.example.scheduler;

     import java.util.HashMap;
     import java.util.Map;

     public class TaskDependencyGraph {
         private final Map<String, Boolean> taskStatus; // true = completed, false = submitted

         public TaskDependencyGraph() {
             this.taskStatus = new HashMap<>();
         }

         public boolean isTaskCompleted(String taskId) {
             return taskStatus.getOrDefault(taskId, false);
         }

         public void markTaskSubmitted(String taskId) {
             taskStatus.put(taskId, false);
         }

         public void markTaskCompleted(String taskId) {
             taskStatus.put(taskId, true);
         }
     }