package xianzhan.hubu.service.base.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import xianzhan.hubu.service.base.aop.HttpLogAop;
import xianzhan.hubu.service.base.filter.HttpHeaderFilter;
import xianzhan.hubu.service.base.interceptor.HubuFeignInterceptor;
import xianzhan.hubu.service.base.manager.IMemoryManager;
import xianzhan.hubu.service.base.manager.memory.RedissonMemoryManager;

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

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public HubuFeignInterceptor feignInterceptor() {
        return new HubuFeignInterceptor();
    }

    @Bean
    public IMemoryManager memoryManager() {
        return new RedissonMemoryManager();
    }
}
