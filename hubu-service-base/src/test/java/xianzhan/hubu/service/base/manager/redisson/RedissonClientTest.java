package xianzhan.hubu.service.base.manager.redisson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import xianzhan.hubu.service.base.ServiceBaseApplication;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@SpringJUnitConfig
@SpringBootTest(classes = ServiceBaseApplication.class)
public class RedissonClientTest {

    @Resource
    private RedissonClient redissonClient;

    private static final String K = "hello-2022";
    private static final String V = "value-2022";

    @Test
    public void testSet() {
        RBucket<String> hello = redissonClient.getBucket(K);
        hello.set(V, 1, TimeUnit.MINUTES);
    }

    @Test
    public void testGet() {
        RBucket<String> hello = redissonClient.getBucket(K);
        String first = hello.get();
        Assertions.assertEquals(V, first);
    }

    @Test
    public void testGetTimeout() throws InterruptedException {
        RBucket<String> hello = redissonClient.getBucket(K);
        String first = hello.get();
        Assertions.assertEquals(V, first);

        System.out.println(LocalDateTime.now());
        TimeUnit.MINUTES.sleep(1);
        String second = hello.get();
        Assertions.assertNull(second);
        System.out.println(LocalDateTime.now());
    }
}
