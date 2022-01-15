package xianzhan.hubu.core;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Hubu 线程池
 * 1. 执行任务时复制父线程 MDC
 * 2. 拒绝策略为任务队列满了由当前线程执行
 *
 * @author xianzhan
 * @since 2022-01-15
 */
@Slf4j
public class HubuThreadPoolExecutor extends ThreadPoolExecutor {

    public HubuThreadPoolExecutor(int corePoolSize,
                                  int maximumPoolSize,
                                  long keepAliveTime,
                                  TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue
    ) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                new HubuThreadFactory(),
                new HubuRejectedExecutionHandler()
        );
    }

    @Override
    public void execute(Runnable command) {
        // 父线程 MDC
        Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
        super.execute(() -> {
            // 子线程 MDC
            MDC.setContextMap(copyOfContextMap);
            try {
                command.run();
            } finally {
                MDC.clear();
            }
        });
    }

    private static class HubuThreadFactory implements ThreadFactory {

        private static final AtomicInteger POOL_NUMBER  = new AtomicInteger(1);
        private final        AtomicInteger threadNumber = new AtomicInteger(1);
        private final        ThreadGroup   group;
        private final        String        namePrefix;

        public HubuThreadFactory() {
            group = Thread.currentThread().getThreadGroup();
            namePrefix = "hubu-pool-" + POOL_NUMBER.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            final String name = namePrefix + threadNumber.getAndIncrement();
            final int stackSize = 0;
            Thread t = new Thread(group, r, name, stackSize);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

    private static class HubuRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            log.warn("Thread - rejectedExecution: Exceeds thread pool maximum capacity. taskCount: {}", executor.getQueue().size());
            r.run();
        }
    }
}
