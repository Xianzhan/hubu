package xianzhan.hubu.service.base.manager.memory;

import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import xianzhan.hubu.service.base.manager.IMemoryManager;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author xianzhan
 * @since 2022-05-28
 */
public class RedissonMemoryManager implements IMemoryManager {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public <V> void set(String key, V value, long timeToLive, TimeUnit timeUnit) {
        RBucket<V> bucket = redissonClient.getBucket(key);
        bucket.set(value, timeToLive, timeUnit);
    }

    @Override
    public <V> V get(String key) {
        RBucket<V> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }

    @Override
    public boolean del(String key) {
        RBucket<?> bucket = redissonClient.getBucket(key);
        return bucket.delete();
    }

    @Override
    public long incr(String key) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
        return atomicLong.getAndIncrement();
    }

    @Override
    public boolean exists(String key) {
        RBucket<?> bucket = redissonClient.getBucket(key);
        return bucket.isExists();
    }

    @Override
    public boolean expire(String key, long seconds) {
        RBucket<?> bucket = redissonClient.getBucket(key);
        return bucket.expire(Duration.ofSeconds(seconds));
    }

    @Override
    public long ttl(String key) {
        RBucket<?> bucket = redissonClient.getBucket(key);
        return bucket.remainTimeToLive();
    }

    @Override
    public void lock(String lockName, Runnable criticalSection) {
        RLock lock = redissonClient.getLock(lockName);
        try {
            lock.lock();
            criticalSection.run();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void semaphore(String semaphoreName, int permits, Runnable criticalSection) {
        RSemaphore semaphore = redissonClient.getSemaphore(semaphoreName);
        try {
            semaphore.trySetPermits(permits);
            semaphore.acquire();
            criticalSection.run();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
