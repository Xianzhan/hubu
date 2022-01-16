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
}
