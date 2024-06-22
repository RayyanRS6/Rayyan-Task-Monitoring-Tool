import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NotificationServiceTest {
    private NotificationService notificationService;

    @Before
    public void setUp() {
        notificationService = new NotificationService();
    }

    @Test
    public void testNotify() {
        notificationService.notify("Test notification");
        assertEquals(1, notificationService.getNotifications().size());
        assertEquals("Test notification", notificationService.getNotifications().get(0));
    }
}
