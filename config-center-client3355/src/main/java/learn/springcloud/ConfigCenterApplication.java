package learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description:
 * @author: Xu Yuwen
 * @Date: 2022-06-15 17:02
 */
@EnableEurekaClient
@SpringBootApplication
public class ConfigCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterApplication.class,args);
    }
}
