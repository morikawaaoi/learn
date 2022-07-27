package com.sso.controller;

import cn.dev33.satoken.sso.SaSsoManager;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: Xu Yuwen
 * @Date: 2022-07-26 16:13
 */
@RestController
@Slf4j
@RequestMapping("/client")
public class SaTokenClientController {
    // SSO-Client端：首页
    @GetMapping("/index")
    public String index(){
        //认证中心地址
        String authUrl = SaSsoManager.getConfig().getAuthUrl();
        //单点注销地址
        String solUrl = SaSsoManager.getConfig().getSloUrl();
        log.info("authUrl:"+authUrl+",solUrl:"+solUrl);
        String str = "<h2>Sa-Token SSO-Client 应用端 模式一</h2>" +
                "<p>当前会话是否登录：" + StpUtil.isLogin() + "</p>" +
                "<p><a href=\"javascript:location.href='" + authUrl + "?mode=simple&redirect=' + encodeURIComponent(location.href);\">登录</a> " +
                "<a href=\"javascript:location.href='" + solUrl + "?back=' + encodeURIComponent(location.href);\">注销</a> </p>";
        return str;
    }
}
