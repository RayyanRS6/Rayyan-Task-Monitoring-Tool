import org.junit.Test;
import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void testTaskCreation() {
        Task task = new Task(1, "Test task");
        assertEquals(1, task.getId());
        assertEquals("Test task", task.getDescription());
        assertFalse(task.isCompleted());
        assertTrue(task.getSubtasks().isEmpty());
    }

    @Test
    public void testTaskCompletion() {
        Task task = new Task(1, "Test task");
        Task completedTask = task.complete();
        assertTrue(completedTask.isCompleted());
    }

    @Test
    public void testAddSubtask() {
        Task task = new Task(1, "Parent task");
        Task subtask = new Task(2, "Subtask");
        Task updatedTask = task.addSubtask(subtask);
        assertEquals(1, updatedTask.getSubtasks().size());
        assertEquals(subtask, updatedTask.getSubtasks().get(0));
    }
}
