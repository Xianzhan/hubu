package xianzhan.hubu.service.base.manager.memory;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import xianzhan.hubu.service.base.manager.IMemoryManager;

import javax.annotation.Resource;
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
}
