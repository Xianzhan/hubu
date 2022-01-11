package xianzhan.hubu.core.util;

/**
 * 字符串工具类
 *
 * @author xianzhan
 * @since 2022-01-02
 */
public class StringUtil {

    /**
     * @param str 字符串
     * @return 字符串是否为空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
