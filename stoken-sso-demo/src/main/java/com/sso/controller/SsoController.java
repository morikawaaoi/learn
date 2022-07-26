package com.sso.controller;

import cn.dev33.satoken.sso.SaSsoHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: sso单点登录服务器端  Sa-Token-SSO Server端 Controller
 * @author: Xu Yuwen
 * @Date: 2022-07-26 11:09
 */
@RestController
@Slf4j
@RequestMapping("/sso")
public class SsoController {

    /**
     *  SSO-Server端：处理所有SSO相关请求
     * */
    @GetMapping
    public Object ssoRequest(){
        return SaSsoHandle.serverRequest();
    }
}
