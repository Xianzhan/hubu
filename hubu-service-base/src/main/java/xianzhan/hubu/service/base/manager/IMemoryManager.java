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
}
