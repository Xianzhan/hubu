package xianzhan.hubu.service.base.filter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import xianzhan.hubu.base.util.HttpUtil;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取网关的请求 id、ip
 *
 * @author xianzhan
 * @since 2022-01-02
 */
@Component
public class HttpHeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest req && response instanceof HttpServletResponse res) {
            String requestId = req.getHeader(HttpUtil.HEADER_REQUEST_ID);
            String requestIp = req.getHeader(HttpUtil.HEADER_REQUEST_IP);

            MDC.put(HttpUtil.HEADER_REQUEST_ID, requestId);
            MDC.put(HttpUtil.HEADER_REQUEST_IP, requestIp);

            res.setHeader(HttpUtil.HEADER_REQUEST_ID, requestId);
        }

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
