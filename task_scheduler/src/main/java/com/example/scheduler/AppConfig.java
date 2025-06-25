package com.example.scheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.scheduler")
public class AppConfig {

    @Bean
    public TaskDependencyGraph taskDependencyGraph(TaskRepository taskRepository) {
        return new TaskDependencyGraph(taskRepository);
    }

    @Bean
    public DynamicTaskScheduler dynamicTaskScheduler(TaskRepository taskRepository, PredictiveModel predictiveModel, TaskDependencyGraph taskDependencyGraph) {
        return new DynamicTaskScheduler(taskRepository, predictiveModel, taskDependencyGraph);
    }

    @Bean
    public PredictiveModel predictiveModel() {
        return new PredictiveModel();
    }

    @Bean
    public ShutdownHook shutdownHook(DynamicTaskScheduler dynamicTaskScheduler) {
        return new ShutdownHook(dynamicTaskScheduler);
    }

    @SuppressWarnings("unused")
    private static class ShutdownHook {
        private final DynamicTaskScheduler dynamicTaskScheduler;

        public ShutdownHook(DynamicTaskScheduler dynamicTaskScheduler) {
            this.dynamicTaskScheduler = dynamicTaskScheduler;
            Runtime.getRuntime().addShutdownHook(new Thread(dynamicTaskScheduler::shutdown));
        }
    }
}