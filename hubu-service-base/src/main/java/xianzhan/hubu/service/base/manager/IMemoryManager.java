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
}
