package xianzhan.hubu.base.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BitUtilTest {

    @Test
    public void testSetLong() {
        long ret = BitUtil.setLong(0, 1, 0, 1);
        Assertions.assertEquals(1, ret);
        ret = BitUtil.setLong(0, 2, 0, 2);
        Assertions.assertEquals(2, ret);
        ret = BitUtil.setLong(0, 2, 0, 1);
        Assertions.assertEquals(0, ret);
        ret = BitUtil.setLong(0, 15, 0, 1);
        Assertions.assertEquals(1, ret);
        ret = BitUtil.setLong(0, 15, 0, 2);
        Assertions.assertEquals(3, ret);
        ret = BitUtil.setLong(0, 15, 0, 3);
        Assertions.assertEquals(7, ret);
    }
}
