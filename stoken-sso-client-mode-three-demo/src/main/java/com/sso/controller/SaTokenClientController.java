package com.sso.controller;

import cn.dev33.satoken.config.SaSsoConfig;
import cn.dev33.satoken.sso.SaSsoHandle;
import cn.dev33.satoken.sso.SaSsoUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.ejlchina.okhttps.OkHttps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: SSO模式三 http请求获取会话
 * @author: Xu Yuwen
 * @Date: 2022-07-27 14:40
 */
@RestController
@RequestMapping("/sso")
public class SaTokenClientController {

    @GetMapping("/index")
    public String index() {
        String str = "<h2>Sa-Token SSO-Client 应用端 模式三</h2>" +
                "<p>当前会话是否登录：" + StpUtil.isLogin() + "</p>" +
                "<p><a href=\"javascript:location.href='/sso/login?back=' + encodeURIComponent(location.href);\">登录</a> " +
                "<a href='/sso/logout?back=self'>注销</a></p>";
        return str;
    }

    // 查询我的账号信息
    @GetMapping("/myInfo")
    public Object myinfo() {
        Object userinfo = SaSsoUtil.getUserinfo(StpUtil.getLoginId());
        System.out.println("--------info：" + userinfo);
        return userinfo;
    }

    /**
     * @description: 自定义传输信息
     * @author: Xu Yuwen
     * @date: 2022/7/27 15:48
     * @param:
     * @return:
     * @return: java.lang.Object
     **/
    @GetMapping("/myList")
    public Object myList() {
        // 组织url，加上签名参数
        String url = SaSsoUtil.addSignParams("http://sa-sso-server.com:9000/sso/myList", StpUtil.getLoginId());
        System.out.println("url:"+url);
        // 调用，并返回 SaResult 结果
        SaResult res = SaSsoUtil.request(url);
        return res;
    }


    /*
     * SSO-Client端：处理所有SSO相关请求
     *         http://{host}:{port}/sso/login          -- Client端登录地址，接受参数：back=登录后的跳转地址
     *         http://{host}:{port}/sso/logout         -- Client端单点注销地址（isSlo=true时打开），接受参数：back=注销后的跳转地址
     *         http://{host}:{port}/sso/logoutCall     -- Client端单点注销回调地址（isSlo=true时打开），此接口为框架回调，开发者无需关心
     */
    @RequestMapping("/*")
    public Object ssoRequest() {
        return SaSsoHandle.clientRequest();
    }


    // 配置SSO相关参数
    @Resource
    private void configSso(SaSsoConfig sso) {
        // ... 其他代码

        // 配置 Http 请求处理器
        sso.setSendHttp(url -> {
            System.out.println("发起请求：" + url);
            return OkHttps.sync(url).get().getBody().toString();
        });
    }
}
