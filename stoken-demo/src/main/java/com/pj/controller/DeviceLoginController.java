package com.pj.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 同端互斥登陆
 * @author: Xu Yuwen
 * @Date: 2022-07-25 09:56
 */
@RestController
@RequestMapping("/deviceLogin")
public class DeviceLoginController {

    @GetMapping("/dLogin")
    public String dLogin(){
        StpUtil.login(10001,"PC");
        return "顶号成功";
    }

    @GetMapping("/dLogout")
    public String dLogout(){
        StpUtil.logout(10001,"PC");
        return "注销成功";
    }

}
