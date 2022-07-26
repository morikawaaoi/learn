package com.pj.controller;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 临时切换身份
 * @author: Xu Yuwen
 * @Date: 2022-07-25 09:31
 */

@RestController
@RequestMapping("/switch")
@Slf4j
public class SwitchController {


    @GetMapping("/switchA")
    public String switchA(){
        log.info("转换为10004账号");

        StpUtil.switchTo(10004,()->{
            log.info("是否正在转换："+StpUtil.isSwitch());
            log.info("获取当前登陆id："+StpUtil.getLoginId());

        });
        log.info("转换结束");
        log.info("当前登陆id："+StpUtil.getLoginId());
        return "结束";
    }

    @GetMapping("/switchB")
    public String switchB(){

        StpUtil.switchTo(10005);
        log.info("转换为10005账号");
        log.info("获取当前登陆id："+StpUtil.getLoginId());
        StpUtil.endSwitch();
        log.info("转换结束");
        log.info("当前登陆id："+StpUtil.getLoginId());
        return "结束";
    }
}
