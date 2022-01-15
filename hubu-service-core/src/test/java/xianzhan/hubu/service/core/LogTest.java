package xianzhan.hubu.service.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import xianzhan.hubu.core.util.HttpUtil;
import xianzhan.hubu.core.util.IdUtil;

import java.net.InetAddress;
import java.time.LocalDateTime;

/**
 * @author xianzhan
 * @since 2022-01-03
 */
@Slf4j
public class LogTest {

    @Test
    public void test() throws Exception {
        MDC.put(HttpUtil.HEADER_REQUEST_IP, InetAddress.getLocalHost().getHostAddress());
        MDC.put(HttpUtil.HEADER_REQUEST_ID, IdUtil.uuid());

        LocalDateTime now = LocalDateTime.now();
        log.error("{}", now);
        log.warn("{}", now);
        log.info("{}", now);
    }
}
