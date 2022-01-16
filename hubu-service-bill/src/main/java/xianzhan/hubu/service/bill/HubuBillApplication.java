package xianzhan.hubu.service.bill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 账单服务
 *
 * @author xianzhan
 * @since 2022-01-16
 */
@EnableWebMvc
@EnableEurekaClient
@SpringBootApplication
public class HubuBillApplication {

    public static void main(String[] args) {
        SpringApplication.run(HubuBillApplication.class, args);
    }
}
