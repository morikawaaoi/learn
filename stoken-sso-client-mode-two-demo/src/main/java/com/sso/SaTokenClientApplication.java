package com.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: 启动类
 * @author: Xu Yuwen
 * @Date: 2022-07-27 09:54
 */
@SpringBootApplication
public class SaTokenClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaTokenClientApplication.class, args);
        System.out.println("\nSa-Token SSO模式二 Client端启动成功");
    }
}
