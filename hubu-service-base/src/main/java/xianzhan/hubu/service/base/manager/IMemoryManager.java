package xianzhan.hubu.service.base.manager;

import java.util.concurrent.TimeUnit;

/**
 * 管理缓存、锁工具类
 *
 * @author xianzhan
 */
public interface IMemoryManager {

    /**
     * 设置 key-value
     *
     * @param key        key
     * @param value      value
     * @param timeToLive 存活时间
     * @param timeUnit   存活时间单位
     * @param <V>        value 类型
     */
    <V> void set(String key, V value, long timeToLive, TimeUnit timeUnit);

    /**
     * 获取 key-value
     *
     * @param key key
     * @param <V> value 类型
     * @return value
     */
    <V> V get(String key);

    /**
     * 删除 key
     *
     * @param key key
     * @return 是否删除
     */
    boolean del(String key);

    /**
     * 当前 key 的 value（需要是数字）自增 1
     *
     * @param key key
     * @return value
     */
    long incr(String key);

    /**
     * 判断 key 是否存在
     *
     * @param key key
     * @return key 是否存在
     */
    boolean exists(String key);

    /**
     * 设置 key 的过期时间（单位：秒）
     *
     * @param key     key
     * @param seconds 过期时间（单位：秒）
     * @return 设置过期时间是否成功
     */
    boolean expire(String key, long seconds);

    /**
     * 返回 key 的剩余存活时间（单位：毫秒）
     *
     * @param key key
     * @return key 的剩余存活时间（单位：毫秒）
     */
    long ttl(String key);

    /**
     * 获取锁，执行临界区代码（可重入）
     *
     * @param lockName        锁名
     * @param criticalSection 临界区
     */
    void lock(String lockName, Runnable criticalSection);

    /**
     * 获取信号，根据凭证执行临界区代码
     *
     * @param semaphoreName   信号名
     * @param permits         凭证，同一时间可以有多少线程进入临界区
     * @param criticalSection 临界区
     */
    void semaphore(String semaphoreName, int permits, Runnable criticalSection);
}
