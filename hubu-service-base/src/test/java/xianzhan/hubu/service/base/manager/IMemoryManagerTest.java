package xianzhan.hubu.service.base.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import xianzhan.hubu.service.base.ServiceBaseApplication;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author 42444
 * @since 2022-05-28
 */
@SpringJUnitConfig
@SpringBootTest(classes = ServiceBaseApplication.class)
public class IMemoryManagerTest {

    @Resource
    private IMemoryManager memoryManager;

    private static final String K = "hello-2022";
    private static final String V = "value-2022";

    @Test
    public void testSet() {
        memoryManager.set(K, V, 1, TimeUnit.MINUTES);
    }

    @Test
    public void testGet() {
        String first = memoryManager.get(K);
        Assertions.assertEquals(V, first);
    }

    @Test
    public void testGetTimeout() throws InterruptedException {
        String first = memoryManager.get(K);
        Assertions.assertEquals(V, first);

        System.out.println(LocalDateTime.now());
        TimeUnit.MINUTES.sleep(1);
        String second = memoryManager.get(K);
        Assertions.assertNull(second);
        System.out.println(LocalDateTime.now());
    }
}
