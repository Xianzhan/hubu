package xianzhan.hubu.service.base.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.redisson.spring.starter.RedissonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import xianzhan.hubu.service.base.manager.IMemoryManager;
import xianzhan.hubu.service.base.manager.memory.RedissonMemoryManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * 基础配置
 *
 * @author xianzhan
 * @since 2022-05-28
 */
public abstract class BaseConfig {

    @Autowired
    private ApplicationContext ctx;
    @Autowired
    private RedissonProperties redissonProperties;

    /**
     * @return RedissonClient
     * @see RedissonAutoConfiguration#redisson()
     */
    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean(RedissonClient.class)
    public RedissonClient redisson() {
        Config config;
        try {
            InputStream is = getConfigStream();
            config = Config.fromYAML(is);
            // 使用 Jackson 序列化
            config.setCodec(new JsonJacksonCodec());
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't parse config", e);
        }
        return Redisson.create(config);
    }

    @Bean
    public IMemoryManager memoryManager() {
        return new RedissonMemoryManager();
    }

    private InputStream getConfigStream() throws IOException {
        Resource resource = ctx.getResource(redissonProperties.getFile());
        return resource.getInputStream();
    }
}
