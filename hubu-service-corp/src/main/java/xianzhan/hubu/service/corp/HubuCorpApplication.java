package xianzhan.hubu.service.corp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 企业服务
 *
 * @author xianzhan
 */
@EnableWebMvc
@EnableEurekaClient
@SpringBootApplication
public class HubuCorpApplication {

    public static void main(String[] args) {
        SpringApplication.run(HubuCorpApplication.class, args);
    }
}
