package xianzhan.hubu.component.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import xianzhan.hubu.base.util.HttpUtil;
import xianzhan.hubu.base.util.StringUtil;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * request id
 *
 * @author xianzhan
 * @since 2022-01-13
 */
@Slf4j
@Component
public class GlobalHttpHeaderFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // 向请求头添加 id、ip
        ServerHttpRequest headerRequest = request.mutate()
                .header(HttpUtil.HEADER_REQUEST_ID, request.getId())
                .header(HttpUtil.HEADER_REQUEST_IP, getIp(request))
                .build();
        ServerWebExchange idExchange = exchange.mutate()
                .request(headerRequest)
                .build();
        return chain.filter(idExchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

    private String getIp(ServerHttpRequest req) {
        final String unknown = "unknown";

        HttpHeaders headers = req.getHeaders();
        // 转发 IP
        String ip = headers.getFirst("HTTP_CLIENT_IP");
        if (StringUtil.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtil.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_X_FORWARDED");
        }
        if (StringUtil.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_FORWARDED");
        }
        if (StringUtil.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = headers.getFirst("X-FORWARDED-FOR");
        }
        if (StringUtil.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = headers.getFirst("Proxy-Client-IP");
        }
        if (StringUtil.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = headers.getFirst("WL-Proxy-Client-IP");
        }

        // 没有转发 IP，则获取当前通信的请求 IP
        if (StringUtil.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            InetSocketAddress remoteAddress = req.getRemoteAddress();
            if (remoteAddress != null) {
                ip = remoteAddress.getAddress().getHostAddress();
            }
            // 如果是 127.0.0.1，则取本地真实地址
            final String localIpV4 = "127.0.0.1";
            final String localIpV6 = "0:0:0:0:0:0:0:1";
            if (localIpV4.equals(ip)) {
                ip = getLocalHostAddress();
            } else if (localIpV6.equals(ip)) {
                ip = getLocalHostAddress();
            }
        }

        return ip;
    }

    private String getLocalHostAddress() {
        String ip = "";
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            ip = localHost.getHostAddress();
        } catch (UnknownHostException e) {
            log.error("Http - getLocalHostAddress: Get local real IP exception.", e);
        }
        return ip;
    }
}
