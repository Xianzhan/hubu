package xianzhan.hubu.service.corp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 企业服务
 *
 * @author xianzhan
 */
@EnableWebMvc
@SpringBootApplication
@MapperScan("xianzhan.hubu.service.corp.mapper")
public class HubuCorpApplication {

    public static void main(String[] args) {
        SpringApplication.run(HubuCorpApplication.class, args);
    }
}
