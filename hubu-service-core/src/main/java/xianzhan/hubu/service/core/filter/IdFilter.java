package xianzhan.hubu.service.core.filter;

import org.slf4j.MDC;
import xianzhan.hubu.core.util.IdUtil;
import xianzhan.hubu.service.core.util.HttpUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xianzhan
 * @since 2022-01-02
 */
public class IdFilter implements Filter {

    private static final String REQUEST_ID = "hubu-request-id";
    private static final String REQUEST_IP = "hubu-request-ip";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest req && response instanceof HttpServletResponse res) {
            String requestId = req.getHeader(REQUEST_ID);
            if (requestId == null) {
                requestId = IdUtil.uuid();
            }
            String ip = HttpUtil.getIp(req);

            MDC.put(REQUEST_ID, requestId);
            MDC.put(REQUEST_IP, ip);

            res.setHeader(REQUEST_ID, requestId);
        }

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
