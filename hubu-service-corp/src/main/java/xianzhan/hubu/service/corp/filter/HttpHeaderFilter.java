package xianzhan.hubu.service.corp.filter;

import org.springframework.stereotype.Component;
import xianzhan.hubu.service.core.filter.AbstractHttpHeaderFilter;

/**
 * 开启获取网关的请求 id、ip
 *
 * @author xianzhan
 * @since 2022-01-02
 */
@Component
public class HttpHeaderFilter extends AbstractHttpHeaderFilter {
}
