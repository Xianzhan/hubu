package xianzhan.hubu.service.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ServiceBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceBaseApplication.class, args);
    }
}
