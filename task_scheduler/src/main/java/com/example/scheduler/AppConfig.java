package com.example.scheduler;

     import org.springframework.context.annotation.Bean;
     import org.springframework.context.annotation.Configuration;
     import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

     @Configuration
     @EnableJpaRepositories(basePackages = "com.example.scheduler")
     public class AppConfig {
         @Bean
         public PredictiveModel predictiveModel() {
             return new PredictiveModel() {
                 @Override
                 public double predict(int taskSize) {
                     return taskSize * 100; // 100ms per task size unit
                 }
             };
         }

         @Bean
         public TaskDependencyGraph taskDependencyGraph() {
             return new TaskDependencyGraph();
         }

         @Bean
         public DynamicTaskScheduler dynamicTaskScheduler(PredictiveModel predictiveModel, TaskDependencyGraph taskDependencyGraph) {
             return new DynamicTaskScheduler(predictiveModel, taskDependencyGraph);
         }
     }