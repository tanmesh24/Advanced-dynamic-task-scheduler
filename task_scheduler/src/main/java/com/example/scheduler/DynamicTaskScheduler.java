package com.example.scheduler;

     import java.util.concurrent.*;

     public class DynamicTaskScheduler {
         private final ExecutorService executorService;
         private final PredictiveModel predictiveModel;
         private final TaskDependencyGraph taskDependencyGraph;
         private final BlockingQueue<Task> pendingTasks = new LinkedBlockingQueue<>();
         private volatile boolean isShuttingDown = false;

         public DynamicTaskScheduler(PredictiveModel predictiveModel, TaskDependencyGraph taskDependencyGraph) {
             this.executorService = Executors.newFixedThreadPool(5);
             this.predictiveModel = predictiveModel;
             this.taskDependencyGraph = taskDependencyGraph;
             startDependencyChecker();
         }

         private void startDependencyChecker() {
             executorService.submit(() -> {
                 while (!executorService.isShutdown()) {
                     try {
                         Task task = pendingTasks.take(); // Blocks until a task is available
                         if (canExecuteTask(task)) {
                             executeTask(task);
                         } else {
                             pendingTasks.offer(task); // Re-queue if still blocked
                         }
                     } catch (InterruptedException e) {
                         Thread.currentThread().interrupt();
                         break;
                     }
                 }
             });
         }

         private boolean canExecuteTask(Task task) {
             if (task.getDependencies().isEmpty()) return true;
             for (String dependencyId : task.getDependencies()) {
                 if (!taskDependencyGraph.isTaskCompleted(dependencyId)) {
                     return false;
                 }
             }
             return true;
         }

         public void submitTask(Task task) {
             pendingTasks.offer(task); // Queue all tasks immediately
         }

         private void executeTask(Task task) {
             taskDependencyGraph.markTaskSubmitted(task.getId());
             System.out.println("Executing task: " + task.getName() + " (Priority: " + task.getPriority() + ")");
             executorService.submit(() -> {
                 try {
                     Thread.sleep((long) predictiveModel.predict(task.getTaskSize()));
                     System.out.println("Completed task: " + task.getName());
                     taskDependencyGraph.markTaskCompleted(task.getId());
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             });
         }

         public void shutdown() {
             if (!isShuttingDown) {
                 isShuttingDown = true;
                 executorService.shutdown();
                 try {
                     if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                         executorService.shutdownNow();
                     }
                 } catch (InterruptedException e) {
                     executorService.shutdownNow();
                 }
                 System.out.println("Shutting down executor, active count: " + !executorService.isTerminated());
             }
         }
     }