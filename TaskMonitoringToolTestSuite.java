import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TaskTest.class,
        TaskManagerTest.class,
        NotificationServiceTest.class
})
public class TaskMonitoringToolTestSuite {
}
