package com.example.scheduler;

     import jakarta.persistence.Entity;
     import jakarta.persistence.Id;
     import java.util.List;

     @Entity
     public class Task {
         @Id
         private String id;
         private String name;
         private int priority;
         private int taskSize;
         private double predictedExecutionTime; // Add this field
         private List<String> dependencies;

         // Constructors
         public Task() {}
         public Task(String id, String name, int priority, int taskSize, List<String> dependencies) {
             this.id = id;
             this.name = name;
             this.priority = priority;
             this.taskSize = taskSize;
             this.dependencies = dependencies;
         }

         // Getters and setters
         public String getId() { return id; }
         public void setId(String id) { this.id = id; }
         public String getName() { return name; }
         public void setName(String name) { this.name = name; }
         public int getPriority() { return priority; }
         public void setPriority(int priority) { this.priority = priority; }
         public int getTaskSize() { return taskSize; }
         public void setTaskSize(int taskSize) { this.taskSize = taskSize; }
         public double getPredictedExecutionTime() { return predictedExecutionTime; } // Add getter
         public void setPredictedExecutionTime(double predictedExecutionTime) { this.predictedExecutionTime = predictedExecutionTime; } // Add setter
         public List<String> getDependencies() { return dependencies; }
         public void setDependencies(List<String> dependencies) { this.dependencies = dependencies; }
     }