package xianzhan.hubu.base.util;

import java.util.HashMap;
import java.util.Map;

/**
 * map 工具类
 *
 * @author xianzhan
 * @since 2023-03-11
 */
public class MapUtil {

    public static <K, V> Map<K, V> newHashMap() {
        return new HashMap<>();
    }
}
