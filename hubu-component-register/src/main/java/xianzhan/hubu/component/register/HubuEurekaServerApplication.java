package xianzhan.hubu.component.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务注册
 *
 * @author xianzhan
 */
@EnableEurekaServer
@SpringBootApplication
public class HubuEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HubuEurekaServerApplication.class, args);
    }
}
