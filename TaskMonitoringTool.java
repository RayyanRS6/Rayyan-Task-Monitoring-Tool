import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Task Class
class Task {
    private int id;
    private String description;
    private boolean isCompleted;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.isCompleted = false;
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

    public void complete() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}

// TaskManager Class
class TaskManager {
    private List<Task> tasks;
    private int taskCounter;

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.taskCounter = 1;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void completeTask(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.complete();
                break;
            }
        }
    }

    public Task getTaskById(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                return task;
            }
        }
        return null;
    }

    public int getNextTaskId() {
        return taskCounter++;
    }
}

// NotificationService Class
class NotificationService {
    private List<String> notifications;

    public NotificationService() {
        this.notifications = new ArrayList<>();
    }

    public void notify(String message) {
        notifications.add(message);
        System.out.println("Notification: " + message);
    }

    public List<String> getNotifications() {
        return notifications;
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
            System.out.println("2. Mark a task as completed");
            System.out.println("3. View all tasks");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

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
                System.out.print("Enter task ID to mark as completed: ");
                int taskId = scanner.nextInt();
                scanner.nextLine(); // consume the newline
                taskManager.completeTask(taskId);
                Task task = taskManager.getTaskById(taskId);
                if (task != null && task.isCompleted()) {
                    notificationService.notify("Task completed: " + task.getDescription());
                } else {
                    System.out.println("Task not found or already completed.");
                }
            } else if (choice == 3) {
                System.out.println("All tasks:");
                for (Task task : taskManager.getTasks()) {
                    System.out.println(task);
                }
            } else if (choice == 4) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
