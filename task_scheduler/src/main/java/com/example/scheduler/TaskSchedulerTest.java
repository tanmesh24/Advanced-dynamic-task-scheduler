package com.example.scheduler;

     import org.springframework.boot.autoconfigure.SpringBootApplication;
     import org.springframework.boot.builder.SpringApplicationBuilder;
     import org.springframework.boot.WebApplicationType;
     import org.springframework.context.ConfigurableApplicationContext;

     import java.util.*;

     @SpringBootApplication
     public class TaskSchedulerTest {
         public static void main(String[] args) {
             // Run Spring Boot application context without web server
             SpringApplicationBuilder builder = new SpringApplicationBuilder(TaskSchedulerTest.class)
                     .web(WebApplicationType.NONE);
             ConfigurableApplicationContext context = builder.run(args);
             DynamicTaskScheduler scheduler = context.getBean(DynamicTaskScheduler.class);

             // 10 tasks with varying priorities, sizes, and dependencies
             List<Task> tasks = new ArrayList<>();
             tasks.add(new Task("task1", "Task 1", 1, 10, new ArrayList<>()));
             tasks.add(new Task("task2", "Task 2", 2, 15, Arrays.asList("task1")));
             tasks.add(new Task("task3", "Task 3", 3, 20, Arrays.asList("task2")));
             tasks.add(new Task("task4", "Task 4", 4, 25, Arrays.asList("task3")));
             tasks.add(new Task("task5", "Task 5", 5, 30, new ArrayList<>()));
             tasks.add(new Task("task6", "Task 6", 6, 35, Arrays.asList("task5")));
             tasks.add(new Task("task7", "Task 7", 7, 40, Arrays.asList("task6")));
             tasks.add(new Task("task8", "Task 8", 8, 45, new ArrayList<>()));
             tasks.add(new Task("task9", "Task 9", 9, 50, Arrays.asList("task8")));
             tasks.add(new Task("task10", "Task 10", 10, 55, Arrays.asList("task9")));

             // Submit all tasks
             for (Task task : tasks) {
                 scheduler.submitTask(task);
             }

             // Wait for all tasks to complete
             try {
                 Thread.sleep(60000); // 60 seconds to allow all tasks to finish
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

             scheduler.shutdown();
             context.close();
         }
     }