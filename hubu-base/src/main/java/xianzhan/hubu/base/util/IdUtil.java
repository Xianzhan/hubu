package xianzhan.hubu.base.util;

import java.time.LocalDate;
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

    public static long orderId(String corpId, String employeeId, long index) {
        var now = LocalDate.now();
        var year = now.getYear();

        // year - 2000 只要差，55 + 7 = 62，最高位 63 符号数为 0 保持正数，7 位可表 127 年
        var ret = BitUtil.setLong(0L, (year - 2000), 55, 7);
        // 20 + 35 = 55，35 位可表一年毫秒数
        ret = BitUtil.setLong(ret, System.currentTimeMillis(), 20, 35);
        // 16 + 4 = 20，4 位 corpId 哈希定位库
        ret = BitUtil.setLong(ret, StringUtil.hash(corpId), 16, 4);
        // 12 + 4 = 16，4 位 employeeId 哈希定位表
        ret = BitUtil.setLong(ret, StringUtil.hash(employeeId), 12, 4);
        // 0  + 12 = 12，12 位并发 4096
        ret = BitUtil.setLong(ret, index, 0, 12);
        return ret;
    }
}
