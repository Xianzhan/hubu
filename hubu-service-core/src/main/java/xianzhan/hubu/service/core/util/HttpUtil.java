package xianzhan.hubu.service.core.util;

import xianzhan.hubu.core.util.StringUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * HTTP 服务工具类
 *
 * @author xianzhan
 * @since 2022-01-02
 */
public class HttpUtil {

    public static String getIp(HttpServletRequest req) {
        final String unknown = "unknown";

        String ip = req.getHeader("HTTP_CLIENT_IP");
        if (StringUtil.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = req.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtil.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = req.getHeader("HTTP_X_FORWARDED");
        }
        if (StringUtil.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = req.getHeader("HTTP_FORWARDED");
        }
        if (StringUtil.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = req.getHeader("X-FORWARDED-FOR");
        }
        if (StringUtil.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (StringUtil.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtil.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }

        return ip;
    }
}
