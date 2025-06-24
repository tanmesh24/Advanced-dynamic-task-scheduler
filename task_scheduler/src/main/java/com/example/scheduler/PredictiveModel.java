package com.example.scheduler;

     import org.springframework.stereotype.Component;

     @Component
     public class PredictiveModel {
         public double predict(int taskSize) {
             // Simple prediction: assume execution time scales linearly with taskSize
             return taskSize * 100; // 100ms per unit of taskSize
         }
     }