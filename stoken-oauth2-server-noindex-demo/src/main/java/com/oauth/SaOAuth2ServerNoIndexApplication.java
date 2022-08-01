package com.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: OAuth2.0 server 启动类
 * @author: Xu Yuwen
 * @Date: 2022-07-29 15:53
 */
@SpringBootApplication
public class SaOAuth2ServerNoIndexApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaOAuth2ServerNoIndexApplication.class, args);
        System.out.println("\n------ Sa-Token-OAuth2.0 无主页 认证中心启动成功");
    }
}
