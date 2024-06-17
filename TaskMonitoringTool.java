import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// TaskNotFoundException Class
class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String message) {
        super(message);
    }
}

// Task Class
final class Task {
    private final int id;
    private final String description;
    private final boolean isCompleted;
    private final List<Task> subtasks;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.isCompleted = false;
        this.subtasks = new ArrayList<>();
    }

    private Task(int id, String description, boolean isCompleted, List<Task> subtasks) {
        this.id = id;
        this.description = description;
        this.isCompleted = isCompleted;
        this.subtasks = new ArrayList<>(subtasks);
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public List<Task> getSubtasks() {
        return new ArrayList<>(subtasks);
    }

    public Task addSubtask(Task subtask) {
        List<Task> newSubtasks = new ArrayList<>(this.subtasks);
        newSubtasks.add(subtask);
        return new Task(this.id, this.description, this.isCompleted, newSubtasks);
    }

    public Task complete() {
        return new Task(this.id, this.description, true, this.subtasks);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", isCompleted=" + isCompleted +
                ", subtasks=" + subtasks +
                '}';
    }
}

// TaskManager Class
class TaskManager {
    private final List<Task> tasks;
    private int taskCounter;
    private final Lock lock = new ReentrantLock();

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.taskCounter = 1;
    }

    public synchronized void addTask(Task task) {
        tasks.add(task);
    }

    public synchronized List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public synchronized void completeTask(int taskId) throws TaskNotFoundException {
        Task taskToComplete = getTaskById(taskId);
        if (taskToComplete == null) {
            throw new TaskNotFoundException("Task with ID " + taskId + " not found.");
        }
        tasks.remove(taskToComplete);
        tasks.add(taskToComplete.complete());
    }

    public synchronized Task getTaskById(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                return task;
            }
        }
        return null;
    }

    public synchronized int getNextTaskId() {
        return taskCounter++;
    }
}

// NotificationService Class
class NotificationService {
    private final List<String> notifications;
    private final Lock lock = new ReentrantLock();

    public NotificationService() {
        this.notifications = new ArrayList<>();
    }

    public void notify(String message) {
        lock.lock();
        try {
            notifications.add(message);
            System.out.println("Notification: " + message);
        } finally {
            lock.unlock();
        }
    }

    public List<String> getNotifications() {
        lock.lock();
        try {
            return new ArrayList<>(notifications);
        } finally {
            lock.unlock();
        }
    }
}

// Main Class
public class TaskMonitoringTool {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        NotificationService notificationService = new NotificationService();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add a new task");
            System.out.println("2. Add a subtask");
            System.out.println("3. Mark a task as completed");
            System.out.println("4. View all tasks");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                if (choice == 1) {
                    System.out.print("Enter your name: ");
                    String memberName = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    int taskId = taskManager.getNextTaskId();
                    Task newTask = new Task(taskId, description);
                    taskManager.addTask(newTask);
                    notificationService.notify(memberName + " added a new task: " + description);
                } else if (choice == 2) {
                    System.out.print("Enter the ID of the task to add a subtask to: ");
                    int parentId = scanner.nextInt();
                    scanner.nextLine();
                    Task parentTask = taskManager.getTaskById(parentId);
                    if (parentTask == null) {
                        throw new TaskNotFoundException("Task with ID " + parentId + " not found.");
                    }
                    System.out.print("Enter your name: ");
                    String memberName = scanner.nextLine();
                    System.out.print("Enter subtask description: ");
                    String description = scanner.nextLine();
                    int subtaskId = taskManager.getNextTaskId();
                    Task subtask = new Task(subtaskId, description);
                    Task updatedParentTask = parentTask.addSubtask(subtask);
                    taskManager.completeTask(parentId); 
                    taskManager.addTask(updatedParentTask);
                    notificationService.notify(memberName + " added a subtask: " + description);
                } else if (choice == 3) {
                    System.out.print("Enter task ID to mark as completed: ");
                    int taskId = scanner.nextInt();
                    scanner.nextLine();
                    taskManager.completeTask(taskId);
                    Task task = taskManager.getTaskById(taskId);
                    if (task != null && task.isCompleted()) {
                        notificationService.notify("Task completed: " + task.getDescription());
                    } else {
                        System.out.println("Task not found or already completed.");
                    }
                } else if (choice == 4) {
                    System.out.println("All tasks:");
                    for (Task task : taskManager.getTasks()) {
                        System.out.println(task);
                    }
                } else if (choice == 5) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (TaskNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}
