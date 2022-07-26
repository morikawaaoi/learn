package com.pj;

import cn.dev33.satoken.SaManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: 启动类
 * @author: Xu Yuwen
 * @Date: 2022-07-20 13:48
 */
@SpringBootApplication
public class StokenApplication {
    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(StokenApplication.class,args);
        System.out.println("启动成功：Sa-Token配置如下：" + SaManager.getConfig());
    }
}
