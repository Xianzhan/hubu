package xianzhan.hubu.service.base.manager;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import xianzhan.hubu.service.base.ServiceBaseApplication;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 42444
 * @since 2022-05-28
 */
@Slf4j
@SpringJUnitConfig
@SpringBootTest(classes = ServiceBaseApplication.class)
public class IMemoryManagerTest {

    @Autowired
    private IMemoryManager memoryManager;

    private static final String K = "hello-2022";
    private static final String V = "value-2022";

    @Test
    public void testSet() {
        memoryManager.set(K, V, 1, TimeUnit.MINUTES);
    }

    @Test
    public void testGet() {
        memoryManager.set(K, V, 1, TimeUnit.MINUTES);
        String first = memoryManager.get(K);
        Assertions.assertEquals(V, first);
    }

    @Test
    public void testDel() {
        memoryManager.set(K, V, 1, TimeUnit.MINUTES);
        Assertions.assertTrue(memoryManager.del(K));
    }

    @Test
    public void testIncr() {
        long first = memoryManager.incr("times");
        Assertions.assertEquals(0L, first);
        long second = memoryManager.incr("times");
        Assertions.assertEquals(1L, second);
        Assertions.assertTrue(memoryManager.del("times"));
    }

    @Test
    public void testExists() {
        memoryManager.set(K, V, 1, TimeUnit.MINUTES);
        Assertions.assertTrue(memoryManager.exists(K));
    }

    @Test
    public void testExpire() {
        memoryManager.set(K, V, 1, TimeUnit.MINUTES);
        Assertions.assertTrue(memoryManager.expire(K, 10));
    }

    @Test
    public void testTtl() {
        memoryManager.set(K, V, 2, TimeUnit.MINUTES);
        long ttl = memoryManager.ttl(K);
        log.info("剩余时间. key: {}, ttl: {}ms", K, ttl);
        Assertions.assertTrue(ttl > 60L);
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

    @Test
    public void testSetObject() {
        var p = new Person("xianzhan", 2022);
        memoryManager.set("xianzhan", p, 1, TimeUnit.MINUTES);
    }

    @Test
    public void testGetObject() {
        Person xianzhan = memoryManager.get("xianzhan");
        Assertions.assertEquals("xianzhan", xianzhan.getName());
        Assertions.assertEquals(2022, xianzhan.getId());
    }

    @Test
    public void testLock() throws InterruptedException {
        String lockName = "testLock";
        Runnable r = () -> {
            String threadName = Thread.currentThread().getName();
            log.info("{}, 开始获取锁{}.", threadName, lockName);
            memoryManager.lock(lockName, () -> {
                log.info("{}，获取到锁{}，当前时间{}.", threadName, lockName, LocalDateTime.now());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    log.error("线程被中断.", e);
                }
                log.info("{}，开始释放锁{}，当前时间{}.", threadName, lockName, LocalDateTime.now());
            });
        };

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            threadPool.execute(r);
        }
        threadPool.shutdown();
        if (threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS)) {
            log.info("结束");
        }
    }

    @Test
    public void testSemaphore() throws InterruptedException {
        AtomicInteger i = new AtomicInteger();
        Runnable r = () -> {
            memoryManager.semaphore("testSemaphore2", 2, () -> {
                String name = Thread.currentThread().getName();
                log.info("{} - {} 凭证进入，当前时间：{}", name, i.get(), LocalDateTime.now());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("{} - {} 凭证退出，当前时间：{}", name, i.getAndIncrement(), LocalDateTime.now());
            });
        };

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for (int j = 0; j < 3; j++) {
            threadPool.execute(r);
        }
        threadPool.shutdown();
        if (threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS)) {
            log.info("结束");
        }
    }

    public static class Person {
        private String name;
        private int    id;

        public Person() {
        }

        public Person(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
