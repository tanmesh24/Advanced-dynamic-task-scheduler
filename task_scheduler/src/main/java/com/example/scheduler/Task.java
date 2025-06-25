package com.example.scheduler;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Task {
    @Id
    private String id;
    private String name;
    private int priority;
    private int taskSize;
    private String dependencies;
    private double predictedExecutionTime;

    public Task() {}

    public Task(String id, String name, int priority, int taskSize, String dependencies) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.taskSize = taskSize;
        this.dependencies = dependencies != null ? dependencies : "";
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
    public String getDependencies() { return dependencies != null ? dependencies : ""; }
    public void setDependencies(String dependencies) { this.dependencies = dependencies != null ? dependencies : ""; }
    public double getPredictedExecutionTime() { return predictedExecutionTime; }
    public void setPredictedExecutionTime(double predictedExecutionTime) { this.predictedExecutionTime = predictedExecutionTime; }
}