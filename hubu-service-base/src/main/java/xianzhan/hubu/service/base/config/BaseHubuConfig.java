package xianzhan.hubu.service.base.config;

import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import xianzhan.hubu.base.util.JsonUtil;
import xianzhan.hubu.base.util.MapUtil;
import xianzhan.hubu.service.base.aop.HttpLogAop;
import xianzhan.hubu.service.base.filter.HttpHeaderFilter;
import xianzhan.hubu.service.base.interceptor.HubuFeignInterceptor;

import java.util.Map;

/**
 * Hubu 基础配置
 *
 * @author xianzhan
 * @since 2022-01-16
 */
@Slf4j
public abstract class BaseHubuConfig extends BaseConfig implements ApplicationRunner, ApplicationContextAware {

    private ApplicationContext applicationContext;

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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("hubu - run: spring 环境已初始化...");
        if (applicationContext.getEnvironment() instanceof ConfigurableEnvironment configurableEnvironment) {
            var map = MapUtil.newHashMap();
            var propertySources = configurableEnvironment.getPropertySources();
            propertySources.forEach(ps -> {
                if (ps.getSource() instanceof Map<?, ?> sourceMap) {
                    var strMap = MapUtil.newHashMap();
                    sourceMap.forEach((k, v) -> strMap.put(String.valueOf(k), String.valueOf(v)));
                    map.put(ps.getName(), strMap);
                }
            });

            log.info("hubu - run: 配置. map: {}", JsonUtil.toJson(map));
        }
    }
}
