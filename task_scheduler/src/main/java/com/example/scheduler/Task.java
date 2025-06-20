package com.example.scheduler;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ElementCollection;
import java.util.List;

@Entity
public class Task {
    @Id
    private String id;
    private String name;
    private int priority;
    private long predictedExecutionTime;
    @ElementCollection
    private List<String> dependencies;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
    public long getPredictedExecutionTime() { return predictedExecutionTime; }
    public void setPredictedExecutionTime(long predictedExecutionTime) { this.predictedExecutionTime = predictedExecutionTime; }
    public List<String> getDependencies() { return dependencies; }
    public void setDependencies(List<String> dependencies) { this.dependencies = dependencies; }
}