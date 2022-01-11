package xianzhan.hubu.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author xianzhan
 * @since 2022-01-02
 */
public class StringUtilTest {

    @Test
    public void testIsEmpty() {
        for (String str : Arrays.asList(null, "")) {
            Assertions.assertTrue(StringUtil.isEmpty(str));
        }
    }
}
