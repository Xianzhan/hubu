package xianzhan.hubu.core.util;

import java.util.UUID;

/**
 * id 工具类
 *
 * @author xianzhan
 * @since 2021-12-26
 */
public class IdUtil {

    /**
     * @return uuid 字符串，没有横杠
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
