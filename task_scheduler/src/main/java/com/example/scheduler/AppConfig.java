package com.example.scheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public PredictiveModel predictiveModel() {
        return new PredictiveModel(); // Replace with actual implementation if needed
    }

    @Bean
    public TaskDependencyGraph taskDependencyGraph(TaskRepository taskRepository) {
        return new TaskDependencyGraph(taskRepository);
    }

    @Bean
    public DynamicTaskScheduler dynamicTaskScheduler(TaskRepository taskRepository, PredictiveModel predictiveModel, TaskDependencyGraph taskDependencyGraph) {
        return new DynamicTaskScheduler(taskRepository, predictiveModel, taskDependencyGraph);
    }
}