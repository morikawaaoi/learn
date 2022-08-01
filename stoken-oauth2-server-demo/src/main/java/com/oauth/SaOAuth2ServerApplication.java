package com.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: OAuth2.0 Server 启动
 * @author: Xu Yuwen
 * @Date: 2022-07-28 16:54
 */
@SpringBootApplication
public class SaOAuth2ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaOAuth2ServerApplication.class, args);
        System.out.println("\n------ Sa-Token-OAuth2.0 认证中心启动成功");
    }
}
