# 🧠 Advanced Dynamic Task Scheduler 🚀

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-green?logo=spring)](https://spring.io/projects/spring-boot)
[![H2 Database](https://img.shields.io/badge/H2%20Database-Latest-blue?logo=database)](https://www.h2database.com/html/main.html)
[![Chart.js](https://img.shields.io/badge/Chart.js-Latest-yellow?logo=chartdotjs)](https://www.chartjs.org/)
[![WebSocket](https://img.shields.io/badge/WebSocket-Spring%20Implementation-purple?logo=websockets)](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

> A real-time, dependency-aware, asynchronous task scheduling and visualization system powered by Spring Boot, WebSockets, and Chart.js.

---

## 📌 Overview

The **Advanced Dynamic Task Scheduler** is a Java-based project that enables intelligent task scheduling with support for:
- Custom priorities
- Dependency graphs with cycle detection
- Predictive execution time modeling
- Asynchronous scheduling
- Live Gantt chart updates via WebSockets

This system is ideal for real-time workflow tracking and can be extended to support enterprise-grade process orchestration and monitoring.

---

## ✨ Key Features

- ✅ **Task Submission via REST API**
- 🔁 **Dependency Management with Cycle Detection**
- ⏱️ **Execution Time Prediction with Mock AI Model**
- 📊 **Gantt-style Real-Time Visualization**
- 🧵 **Asynchronous Execution with Thread Pools**
- 💾 **Persistent Storage using H2 Database**
- 🧪 **JUnit & Mockito-Based Testing Suite**
- 🌐 **Live Chart Updates via WebSockets**

---

## 🖥️ Tech Stack

| Layer       | Technology               |
|------------|---------------------------|
| Backend     | Java 17, Spring Boot 3.2.5 |
| Frontend    | HTML5, CSS, Chart.js       |
| Real-Time   | WebSocket (Spring)         |
| Database    | H2 (in-memory/file)        |
| Testing     | JUnit 5, Mockito           |
| Build Tool  | Maven                     |

---

## 📁 Project Directory Structure

```text
tanmesh24-advanced-dynamic-task-scheduler/
├── Advanced_scheduler.txt
│   └── 📄 High-level overview and notes on system architecture
└── task_scheduler/
    ├── Demo Instructions Run.txt
    │   └── 📝 Instructions for running the demo locally
    ├── logs.txt
    │   └── 📂 Runtime logs captured during execution
    ├── output.txt
    │   └── 📂 Sample output from the scheduler
    ├── pom.xml
    │   └── ⚙️ Maven build configuration with dependencies
    ├── taskdb.mv.db
    │   └── 🛢️ File-based H2 database for task persistence
    └── src/
        ├── main/
        │   ├── java/
        │   │   └── com/example/scheduler/
        │   │       ├── AppConfig.java
        │   │       │   └── Spring context and thread pool config
        │   │       ├── DynamicTaskScheduler.java
        │   │       │   └── Core class to schedule and execute tasks
        │   │       ├── PredictiveModel.java
        │   │       │   └── Simulates execution time prediction logic
        │   │       ├── Task.java
        │   │       │   └── JPA entity representing a task
        │   │       ├── TaskController.java
        │   │       │   └── REST API controller for handling task requests
        │   │       ├── TaskDependencyGraph.java
        │   │       │   └── Detects cycles in task dependencies
        │   │       ├── TaskRepository.java
        │   │       │   └── JPA repository for data persistence
        │   │       ├── TaskRequest.java
        │   │       │   └── DTO for parsing incoming task requests
        │   │       └── TemporaryApp.java
        │   │           └── Main entry point for Spring Boot application
        │   └── resources/
        │       ├── application.properties
        │       │   └── Configuration for port, DB, JPA, and WebSocket
        │       └── static/
        │           └── index.html
        │               └── Gantt chart frontend with WebSocket integration
        └── test/
            └── java/com/example/scheduler/
                └── TaskSchedulerTest.java
                    └── JUnit test class for validating scheduling logic
```

---


