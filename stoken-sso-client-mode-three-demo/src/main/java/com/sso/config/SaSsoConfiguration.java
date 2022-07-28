package com.sso.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.spring.SpringMVCUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaFoxUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 单点登陆配置
 * @author: Xu Yuwen
 * @Date: 2022-07-28 11:17
 */
@Configuration
public class SaSsoConfiguration implements WebMvcConfigurer {

    /*@Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                .addExclude("/sso/*","/favicon.ico")
                .setAuth(obj -> {
                    System.out.println("---------- 全局过滤器 -----------");
                    System.out.println("是否登陆："+StpUtil.isLogin());
                    if(StpUtil.isLogin() == false) {
                        String back = SaFoxUtil.joinParam(SaHolder.getRequest().getUrl(), SpringMVCUtil.getRequest().getQueryString());
                        SaHolder.getResponse().redirect("/sso/login?back=" + SaFoxUtil.encodeUrl(back));
                        SaRouter.back();
                    }
                })
                ;
    }*/
}
