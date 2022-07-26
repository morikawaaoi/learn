package com.pj.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 强制注销&踢人下线
 * @author: Xu Yuwen
 * @Date: 2022-07-21 09:22
 */
@RestController
@RequestMapping("/kick")
@Slf4j
public class KickController {

    /**
     * @description: 强制注销
     * @author: Xu Yuwen
     * @date: 2022/7/21 9:25
     * @param:
     * @return:
     * @return: java.lang.String
     **/
    @GetMapping("/logout")
    public String logout(){

        //强制指定id下线
        StpUtil.logout(10001);
        return "注销";
    }

    @GetMapping("/loginoutToken")
    public String loginoutToken(){

        //强制指定token注销下线
        StpUtil.logoutByTokenValue("token");
        return "注销";
    }

    @GetMapping("/loginoutDevice")
    public String loginoutDevice(){

        //强制指定账号指定端注销下线
        StpUtil.logout(10001,"PC");
        return "注销";
    }

    /**
     * @description: 踢人下线
     * @author: Xu Yuwen
     * @date: 2022/7/21 9:43
     * @param:
     * @return:
     * @return: java.lang.String
     **/
    @GetMapping("/kickout")
    public String kickout(){
        //将指定id踢下线
        StpUtil.kickout(10001);
        return "已经给10001踢出去了";
    }

    @GetMapping("/kickoutToken")
    public String kickoutToken(){
        //强制指定token踢下线
        StpUtil.kickoutByTokenValue("token");
        return "已经给10001按照token踢出去了";
    }

    @GetMapping("/kickoutDevice")
    public String kickoutDevice(){
        //强制指定账号指定端踢下线
        StpUtil.kickout(10001,"PC");
        return "已经给10001按照设备踢出去了";
    }

    /**
     * @description: 账号封禁
     * @author: Xu Yuwen
     * @date: 2022/7/21 9:44
     * @param:
     * @param: null
     * @return:
     * @return: null
     **/
    @GetMapping("/saDisable")
    public String saDisable(){
        //指定id
        //封禁时间:单位s (86400秒=1天，此值为-1时，代表永久封禁)
        StpUtil.disable(10001, 120);
        return "封禁成功";
    }

    /**
     * @description: 获取账号是否被封禁
     * @author: Xu Yuwen
     * @date: 2022/7/21 9:47
     * @param:
     * @return:
     * @return: java.lang.String
     **/
    @GetMapping("/isDisable")
    public String isDisable(){
        //获取指定账号是否已被封禁 (true=已被封禁, false=未被封禁)
        //指定id
        return "是否封禁："+StpUtil.isDisable(10001);
    }

    /**
     * @description: 获取指定账号剩余封禁时间，单位：秒
     * @author: Xu Yuwen
     * @date: 2022/7/21 9:53
     * @param:
     * @return:
     * @return: java.lang.String
     **/
    @GetMapping("/getDisableTime")
    public String getDisableTime(){
        // 获取指定账号剩余封禁时间，单位：秒
        return "剩余封禁时间："+StpUtil.getDisableTime(10001);
    }
    /**
     * @description: 解除封禁
     * @author: Xu Yuwen
     * @date: 2022/7/21 9:54
     * @param:
     * @return:
     * @return: java.lang.String
     **/
    @GetMapping("/untieDisable")
    public String untieDisable(){
        // 解除封禁
        StpUtil.untieDisable(10001);
        return "解除封禁成功";
    }

}
