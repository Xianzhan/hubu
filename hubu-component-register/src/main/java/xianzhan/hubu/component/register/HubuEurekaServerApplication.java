package xianzhan.hubu.component.register;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务注册
 *
 * @author xianzhan
 */
@Slf4j
@EnableEurekaServer
@SpringBootApplication
public class HubuEurekaServerApplication {

    public static void main(String[] args) {
        try {
            log.info("hubu - main: eureka 启动中...");
            SpringApplication.run(HubuEurekaServerApplication.class, args);
            log.info("hubu - main: eureka 启动完成.");
        } catch (Exception e) {
            log.error("hubu - main: eureka 启动异常!", e);
            // notification
        }
    }
}
