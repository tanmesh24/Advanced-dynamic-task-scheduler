package com.example.scheduler;

       import org.springframework.web.bind.annotation.*;

       import java.util.List;
       import java.util.Map;
       import java.util.HashMap;

       @RestController
       @RequestMapping("/api/tasks")
       public class TaskController {
           private final DynamicTaskScheduler dynamicTaskScheduler;
           private final TaskRepository taskRepository;

           public TaskController(DynamicTaskScheduler dynamicTaskScheduler, TaskRepository taskRepository) {
               this.dynamicTaskScheduler = dynamicTaskScheduler;
               this.taskRepository = taskRepository;
           }

           @PostMapping
           public String createTask(@RequestBody Task task) {
               taskRepository.save(task);
               dynamicTaskScheduler.submitTask(task);
               return "Task submitted: " + task.getId();
           }

           @GetMapping
           public List<Task> getAllTasks() {
               return taskRepository.findAll();
           }

           @GetMapping("/{id}")
           public Task getTask(@PathVariable String id) {
               return taskRepository.findById(id).orElse(null);
           }

           @GetMapping("/status")
           public Map<String, String> getTaskStatuses() {
               Map<String, String> statuses = new HashMap<>();
               taskRepository.findAll().forEach(task -> statuses.put(task.getId(), "Submitted")); // Placeholder
               return statuses;
           }
       }