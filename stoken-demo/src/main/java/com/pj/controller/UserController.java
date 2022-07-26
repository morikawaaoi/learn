package com.pj.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: Xu Yuwen
 * @Date: 2022-07-20 14:02
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    /**
     * @description: 用户登录
     * @author: Xu Yuwen
     * @date: 2022/7/20 15:32
     * @param:
     * @param: username
     * @param: password
     * @return:
     * @return: cn.dev33.satoken.util.SaResult
     **/
    @GetMapping("/doLogin")
    public SaResult doLogin(String username, String password) {
        System.out.println("进来了");

        if("xyw".equals(username) && "123".equals(password)){
            StpUtil.login(10001,"PC");
            //检验是否已经登陆
            StpUtil.checkLogin();
            log.info("当前会话id："+StpUtil.getLoginId());
            //获取token名称
            log.info("当前tokenid："+StpUtil.getTokenName());
            //获取token值
            log.info("当前token值："+StpUtil.getTokenValue());
            log.info("当前token信息："+StpUtil.getTokenInfo());
            log.info("当前token剩余有效期："+StpUtil.getTokenTimeout());

            return SaResult.ok("10001登陆成功");
        }
        return SaResult.ok("失败");
    }
    /**
     * @description:检测是否登陆
     * @author: Xu Yuwen
     * @date: 2022/7/20 15:31
     * @param:
     * @return:
     * @return: java.lang.String
     **/
    @GetMapping("/isLogin")
    public SaResult isLogin() {
        if(StpUtil.isLogin()){
            return SaResult.ok("当前会话已登录");
        }else{
            return SaResult.error("当前会话未登录");
        }
    }

    /**
     * @description: 获取当前登陆会话id 若未登陆 返回“未登录”
     * @author: Xu Yuwen
     * @date: 2022/7/20 15:32
     * @param:
     * @return:
     * @return: java.lang.String
     **/
    @GetMapping("/getLoginId")
    public String getLoginId(){
        return "当前会话id："+StpUtil.getLoginId("未登录");
    }


}
