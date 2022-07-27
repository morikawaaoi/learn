package com.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: SSO启动类
 * @author: Xu Yuwen
 * @Date: 2022-07-26 13:53
 */
@SpringBootApplication
public class SaSsoServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaSsoServerApplication.class, args);
        System.out.println("\n------ Sa-Token-SSO 认证中心启动成功");
    }
}
