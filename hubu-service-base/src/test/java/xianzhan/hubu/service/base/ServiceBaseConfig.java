package xianzhan.hubu.service.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xianzhan.hubu.service.base.manager.IMemoryManager;
import xianzhan.hubu.service.base.manager.memory.RedissonMemoryManager;

/**
 * @author xianzhan
 * @since 2022-05-28
 */
@Configuration
public class ServiceBaseConfig {

    @Bean
    public IMemoryManager memoryManager() {
        return new RedissonMemoryManager();
    }
}
