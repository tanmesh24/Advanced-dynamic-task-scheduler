# Advanced Dynamic Task Scheduler

## Description

The Advanced Dynamic Task Scheduler is a Java-based Spring Boot application designed to manage and visualize task scheduling with real-time updates. This project leverages modern web technologies and a relational database to provide an interactive Gantt-style chart interface, making it ideal for task prioritization, dependency management, and execution tracking.

This project implements a dynamic task scheduling system that allows users to submit tasks with customizable properties such as priority, size, and dependencies. Tasks are processed asynchronously, with predicted execution times calculated by a mock predictive model, and their progress is visualized in a web-based Gantt chart. The application supports real-time updates via WebSocket technology, ensuring the chart reflects task completion without manual refreshes.

## Features

- **Task Management:** Submit tasks via a REST API (`/tasks` endpoint) with attributes including `id`, `name`, `priority` (1-10), `taskSize`, and `dependencies`. The system detects cyclic dependencies and prevents invalid submissions.
- **Real-Time Visualization:** A Gantt-style chart, rendered using Chart.js, displays tasks horizontally with colors indicating priority (red for 1-3, yellow for 4-6, green for 7-10). The chart updates automatically as tasks complete, powered by WebSocket connections.
- **Database Persistence:** Utilizes an H2 database (file-based) for storing task data, ensuring data integrity.
- **Asynchronous Processing:** Employs a thread pool executor to handle task execution, simulating real-world scheduling with delays based on predicted execution times.
- **Testing and Debugging:** Includes unit tests covering cycle detection and task submission scenarios, and enhanced logging for troubleshooting.

## Technologies Used

- **Framework:** Spring Boot 3.2.5
- **Database:** H2 (in-memory and file-based) with JPA/Hibernate
- **Frontend:** HTML5, CSS, Chart.js
- **Real-Time:** WebSocket
- **Build Tool:** Maven
- **Java Version:** 17

## Installation

1.  **Clone the repository:**

    ```bash
    git clone <repository_url>
    cd task_scheduler
    ```

2.  **Build the project using Maven:**

    ```bash
    mvn clean install
    ```

3.  **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

    Alternatively, you can run the packaged JAR file:

    ```bash
    java -jar target/*.jar
    ```

## Usage

1.  **Access the application:**

    Open your web browser and navigate to `http://localhost:8081`.

2.  **Submit tasks via REST API:**

    Use a tool like `curl` or Postman to submit tasks to the `/tasks` endpoint. Example:

    ```bash
    curl -X POST -H "Content-Type: application/json" -d '{"id": "task13", "name": "Task 13", "priority": 2, "taskSize": 5, "dependencies": []}' http://localhost:8081/tasks
    ```

3.  **View the Gantt chart:**

    The Gantt chart will display the tasks submitted. It updates in real-time as tasks complete.

4.  **H2 Console:**

    The H2 console is accessible at `/h2-console`.

## License

MIT License
