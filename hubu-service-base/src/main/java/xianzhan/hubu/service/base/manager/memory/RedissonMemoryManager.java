package xianzhan.hubu.service.base.manager.memory;

import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import xianzhan.hubu.service.base.manager.IMemoryManager;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author xianzhan
 * @since 2022-05-28
 */
public class RedissonMemoryManager implements IMemoryManager {

    @Resource
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
}
