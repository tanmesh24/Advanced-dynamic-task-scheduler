package com.example.scheduler;

  import java.util.List;

  public class TaskRequest {
      private int priority;
      private long estimatedTimeMs;
      private int resourceDemand;
      private List<String> dependencies;

      // Getters, setters, and no-args constructor required for Jackson deserialization
      public TaskRequest() {}

      public int getPriority() { return priority; }
      public void setPriority(int priority) { this.priority = priority; }
      public long getEstimatedTimeMs() { return estimatedTimeMs; }
      public void setEstimatedTimeMs(long estimatedTimeMs) { this.estimatedTimeMs = estimatedTimeMs; }
      public int getResourceDemand() { return resourceDemand; }
      public void setResourceDemand(int resourceDemand) { this.resourceDemand = resourceDemand; }
      public List<String> getDependencies() { return dependencies; }
      public void setDependencies(List<String> dependencies) { this.dependencies = dependencies; }
  }
