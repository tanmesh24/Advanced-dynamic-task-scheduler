package com.example.scheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ContextConfiguration(classes = {TemporaryApp.class, AppConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class TaskSchedulerTest {

    @Autowired
    private DynamicTaskScheduler dynamicTaskScheduler;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        if (dynamicTaskScheduler == null || taskRepository == null) {
            throw new IllegalStateException("Dependencies not injected. Check Spring context configuration.");
        }
        taskRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testTaskSubmission() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("task1", "Task 1", 1, 10, ""));
        tasks.add(new Task("task2", "Task 2", 2, 15, "task1"));
        tasks.add(new Task("task3", "Task 3", 3, 20, "task2"));
        tasks.add(new Task("task4", "Task 4", 4, 25, "task3"));
        tasks.add(new Task("task5", "Task 5", 5, 30, ""));
        tasks.add(new Task("task6", "Task 6", 6, 35, "task5"));
        tasks.add(new Task("task7", "Task 7", 7, 40, "task6"));
        tasks.add(new Task("task8", "Task 8", 8, 45, ""));
        tasks.add(new Task("task9", "Task 9", 9, 50, "task8"));
        tasks.add(new Task("task10", "Task 10", 10, 55, "task9"));

        for (Task task : tasks) {
            dynamicTaskScheduler.submitTask(task);
        }

        // Wait for execution and ensure persistence
        try {
            Thread.sleep(3000); // Increased to 3 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Flush and verify persistence
        taskRepository.flush();
        assertEquals(10, taskRepository.findAll().size(), "Expected 10 tasks to be persisted");
    }

    @Test
    @Transactional
    public void testCycleDetection() {
        Task taskC = new Task("taskC", "Task C", 1, 10, "taskD");
        Task taskD = new Task("taskD", "Task D", 2, 5, "taskC");

        taskRepository.save(taskC);
        taskRepository.save(taskD);
        taskRepository.flush(); // Ensure tasks are persisted

        System.out.println("Cycle detected for taskD: " + dynamicTaskScheduler.getTaskDependencyGraph().hasCycle("taskD"));

        assertThrows(IllegalStateException.class, () -> dynamicTaskScheduler.submitTask(taskD));
    }
}