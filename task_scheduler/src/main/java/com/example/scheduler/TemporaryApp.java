package com.example.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import java.util.List;

@SpringBootApplication
public class TemporaryApp implements CommandLineRunner {
    @Autowired
    private TaskRepository taskRepository;

    public static void main(String[] args) {
        SpringApplication.run(TemporaryApp.class, args);
    }

    @Override
    public void run(String... args) {
        Task task = new Task();
        task.setId("task1");
        task.setName("Sample Task");
        task.setPriority(1);
        task.setPredictedExecutionTime(1000L);
        task.setDependencies(List.of("task2"));
        taskRepository.save(task);
        System.out.println("Saved task: " + taskRepository.findById("task1"));
    }
}