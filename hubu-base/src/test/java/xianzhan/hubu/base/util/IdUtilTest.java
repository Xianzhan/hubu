package xianzhan.hubu.base.util;

import org.junit.jupiter.api.Test;

/**
 * @author xianzhan
 * @since 2021-12-26
 */
public class IdUtilTest {

    @Test
    public void testUuid() {
        for (int i = 0; i < 10; i++) {
            System.out.println(IdUtil.uuid());
        }
    }

    @Test
    public void testOrderId() {
        String corpId = "TENCENT";
        String employeeId = "123456";
        long orderId = IdUtil.orderId(corpId, employeeId, 0);
        System.out.println(orderId);
        System.out.println(Long.toBinaryString(orderId));
    }
}
