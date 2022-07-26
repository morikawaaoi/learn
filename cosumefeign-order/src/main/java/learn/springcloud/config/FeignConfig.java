package learn.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class FeignConfig {

    @Bean
    feign.Logger.Level feignLoggerLeverl(){
        return feign.Logger.Level.FULL;
    }
}
