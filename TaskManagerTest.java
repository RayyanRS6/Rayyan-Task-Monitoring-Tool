import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TaskManagerTest {
    private TaskManager taskManager;

    @Before
    public void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    public void testAddTask() {
        Task task = new Task(1, "Test task");
        taskManager.addTask(task);
        assertEquals(1, taskManager.getTasks().size());
        assertEquals(task, taskManager.getTasks().get(0));
    }

    @Test
    public void testCompleteTask() throws TaskNotFoundException {
        Task task = new Task(1, "Test task");
        taskManager.addTask(task);
        taskManager.completeTask(1);
        Task completedTask = taskManager.getTaskById(1);
        assertTrue(completedTask.isCompleted());
    }

    @Test(expected = TaskNotFoundException.class)
    public void testCompleteNonExistentTask() throws TaskNotFoundException {
        taskManager.completeTask(999);
    }

    @Test
    public void testGetNextTaskId() {
        assertEquals(1, taskManager.getNextTaskId());
        assertEquals(2, taskManager.getNextTaskId());
    }
}
