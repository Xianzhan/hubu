package xianzhan.hubu.base.util;

import xianzhan.hubu.base.exception.BaseException;

/**
 * 位工具类
 *
 * @author xianzhan
 * @since 2022-07-28
 */
public class BitUtil {

    public static long setLong(final long raw,
                               final long num,
                               final int bitIndex,
                               final int size
    ) {
        if (bitIndex < 0 || bitIndex + size >= Long.SIZE) {
            throw new BaseException("bitIndex 错误");
        }
        return set(raw, num, bitIndex, size);
    }

    private static long set(final long raw,
                            final long num,
                            final int bitIndex,
                            final int size
    ) {
        var rightNum = num;
        var ret = raw;

        for (var i = 0; i < size; i++) {
            var lastBit = rightNum & 1L;
            if (lastBit == 1) {
                var or = 1L << (bitIndex + i);
                ret |= or;
            } else {
                var and = ~(1L << (bitIndex + i));
                ret &= and;
            }
            rightNum >>= 1;
        }
        return ret;
    }
}
