package com.example.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private DynamicTaskScheduler dynamicTaskScheduler;

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/init")
    public String initializeTasks() {
        taskRepository.deleteAll();
        List<Task> tasks = List.of(
            new Task("task1", "Task 1", 1, 10, ""),
            new Task("task2", "Task 2", 2, 15, "task1"),
            new Task("task3", "Task 3", 3, 20, "task2"),
            new Task("task4", "Task 4", 4, 25, "task3"),
            new Task("task5", "Task 5", 5, 30, ""),
            new Task("task6", "Task 6", 6, 35, "task5"),
            new Task("task7", "Task 7", 7, 40, "task6"),
            new Task("task8", "Task 8", 8, 45, ""),
            new Task("task9", "Task 9", 9, 50, "task8"),
            new Task("task10", "Task 10", 10, 55, "task9")
        );
        for (Task task : tasks) {
            dynamicTaskScheduler.submitTask(task);
        }
        return "Tasks initialized successfully";
    }

    @PostMapping
    public String submitTask(@RequestBody Task task) {
        try {
            dynamicTaskScheduler.submitTask(task);
            return "Task submitted: " + task.getId();
        } catch (IllegalStateException e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/data")
    public List<Task> getTaskData() {
        return taskRepository.findAll();
    }
}