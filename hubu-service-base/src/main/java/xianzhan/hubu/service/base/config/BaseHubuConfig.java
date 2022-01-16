package xianzhan.hubu.service.base.config;

import org.springframework.context.annotation.Bean;
import xianzhan.hubu.service.base.aop.HttpLogAop;
import xianzhan.hubu.service.base.filter.HttpHeaderFilter;

/**
 * Hubu 基础配置
 *
 * @author xianzhan
 * @since 2022-01-16
 */
public abstract class BaseHubuConfig {

    @Bean
    public HttpHeaderFilter httpHeaderFilter() {
        return new HttpHeaderFilter();
    }

    @Bean
    public HttpLogAop httpLogAop() {
        return new HttpLogAop();
    }
}
