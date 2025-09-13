# Task Monitoring Tool

A Java-based console application for managing and monitoring tasks with support for subtasks, notifications, and thread-safe operations.

## Features

- **Task Management**: Create, complete, and organize tasks
- **Subtask Support**: Add subtasks to existing tasks for better organization
- **Real-time Notifications**: Get notified when tasks are added or completed
- **Thread-Safe Operations**: Built with concurrent programming in mind using locks
- **Interactive Console Interface**: User-friendly menu-driven interface
- **Comprehensive Testing**: Full test suite with JUnit

## Project Structure

```
TaskMonitoringTool/
├── TaskMonitoringTool.java          # Main application with all classes
├── TaskTest.java                    # Unit tests for Task class
├── TaskManagerTest.java             # Unit tests for TaskManager class
├── NotificationServiceTest.java     # Unit tests for NotificationService class
├── TaskMonitoringToolTestSuite.java # Test suite runner
└── README.md                        # This file
```

## Classes Overview

### Task
- Immutable task representation with ID, description, and completion status
- Support for subtasks
- Thread-safe operations

### TaskManager
- Manages a collection of tasks
- Provides thread-safe task operations
- Handles task ID generation and task completion

### NotificationService
- Manages and displays notifications
- Thread-safe notification handling
- Stores notification history

### TaskNotFoundException
- Custom exception for handling non-existent tasks

## Getting Started

### Prerequisites

- Java 8 or higher
- JUnit 4 (for running tests)

### Running the Application

1. Compile the Java files:
   ```bash
   javac *.java
   ```

2. Run the main application:
   ```bash
   java TaskMonitoringTool
   ```

### Running Tests

1. Compile with JUnit:
   ```bash
   javac -cp ".:junit-4.13.2.jar:hamcrest-core-1.3.jar" *.java
   ```

2. Run individual test classes:
   ```bash
   java -cp ".:junit-4.13.2.jar:hamcrest-core-1.3.jar" org.junit.runner.JUnitCore TaskTest
   java -cp ".:junit-4.13.2.jar:hamcrest-core-1.3.jar" org.junit.runner.JUnitCore TaskManagerTest
   java -cp ".:junit-4.13.2.jar:hamcrest-core-1.3.jar" org.junit.runner.JUnitCore NotificationServiceTest
   ```

3. Run the complete test suite:
   ```bash
   java -cp ".:junit-4.13.2.jar:hamcrest-core-1.3.jar" org.junit.runner.JUnitCore TaskMonitoringToolTestSuite
   ```

## Usage

When you run the application, you'll see a menu with the following options:

1. **Add a new task**: Create a new task with a description
2. **Add a subtask**: Add a subtask to an existing task
3. **Mark a task as completed**: Mark a task as done
4. **View all tasks**: Display all tasks and their status
5. **Exit**: Close the application

### Example Workflow

1. Start the application
2. Choose option 1 to add a new task
3. Enter your name and task description
4. The system will notify you that the task was added
5. Use option 2 to add subtasks if needed
6. Use option 3 to mark tasks as completed
7. Use option 4 to view all your tasks

## Technical Details

### Thread Safety
- Uses `ReentrantLock` for thread-safe operations
- Synchronized methods in TaskManager
- Immutable Task objects to prevent race conditions

### Design Patterns
- **Immutable Objects**: Task class is immutable
- **Exception Handling**: Custom TaskNotFoundException
- **Observer Pattern**: Notification system for task updates

### Testing
- Unit tests for all major classes
- Test suite for comprehensive testing
- Covers normal operations and edge cases

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Ensure all tests pass
6. Submit a pull request

## License

This project is open source and available under the [MIT License](LICENSE).

## Future Enhancements

- Database persistence for tasks
- Web-based interface
- Task prioritization
- Due dates and reminders
- Team collaboration features
- Export/import functionality
