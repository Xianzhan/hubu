package xianzhan.hubu.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author xianzhan
 * @since 2022-01-15
 */
public class HubuThreadPoolExecutorTest {

    private HubuThreadPoolExecutor executor = new HubuThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1));

    @Test
    public void testExecute() {
        final String key = "key";
        final String value = "value";
        MDC.put(key, value);

        executor.execute(() -> {
            String v = MDC.get(key);
            System.out.println(v);
            Assertions.assertEquals(value, v);
        });
    }
}
