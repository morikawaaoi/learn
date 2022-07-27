package com.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: Sso单点登录 客户端
 * @author: Xu Yuwen
 * @Date: 2022-07-26 16:26
 */
@SpringBootApplication
public class SaSsoClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaSsoClientApplication.class, args);
        System.out.println("\nSa-Token SSO模式一 Client端启动成功");
    }
}
