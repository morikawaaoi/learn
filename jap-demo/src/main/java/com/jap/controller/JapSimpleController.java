package com.jap.controller;

import cn.hutool.core.util.URLUtil;
import com.fujieid.jap.core.JapUserService;
import com.fujieid.jap.core.config.JapConfig;
import com.fujieid.jap.core.context.JapAuthentication;
import com.fujieid.jap.core.result.JapResponse;
import com.fujieid.jap.http.adapter.jakarta.JakartaRequestAdapter;
import com.fujieid.jap.http.adapter.jakarta.JakartaResponseAdapter;
import com.fujieid.jap.simple.SimpleConfig;
import com.fujieid.jap.simple.SimpleStrategy;
import com.fujieid.jap.spring.boot.japsimplespringbootstarter.autoconfigure.SimpleProperties;
import com.fujieid.jap.spring.boot.starter.JapTemplate;
import com.jap.config.JapConfigContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: simple认证模块
 * @author: Xu Yuwen
 * @Date: 2022-08-02 09:50
 */
@RestController
@RequestMapping("/simple")
public class JapSimpleController {

    @Resource
    private SimpleStrategy simpleStrategy;
    @Resource
    private SimpleProperties simpleProperties;
    @Resource
    private JapTemplate japTemplate;
    @Resource
    private JapUserService japUserService;

    @GetMapping("/login")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {
        if (JapAuthentication.checkUser(new JakartaRequestAdapter(request), new JakartaResponseAdapter(response)).isSuccess()) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView renderAuth(HttpServletRequest request, HttpServletResponse response) {
        SimpleStrategy simpleStrategy = new SimpleStrategy(japUserService, new JapConfig());
        JapResponse japResponse = simpleStrategy.authenticate(new SimpleConfig(), new JakartaRequestAdapter(request), new JakartaResponseAdapter(response));

        System.out.println("res:"+japResponse.getData());
        if (!japResponse.isSuccess()) {
            return new ModelAndView(new RedirectView("/?error=" + URLUtil.encode(japResponse.getMessage())));
        }
        if (japResponse.isRedirectUrl()) {
            return new ModelAndView(new RedirectView((String) japResponse.getData()));
        } else {
            // 登录成功，需要对用户数据进行处理
            // ...
            System.out.println(japResponse.getData());
            return new ModelAndView(new RedirectView("/"));
        }
    }


    /*@GetMapping("/login")
    public Object renderAuth(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("jinlaile");

        JapConfigContext.strategy = "simple";
        return simpleStrategy.authenticate(new SimpleConfig()
                .setRememberMeCookieDomain("jap.com"), request, response);
    }*/

    /*@PostMapping("/login")
    public ModelAndView renderAuth(HttpServletRequest request, HttpServletResponse response) {
        SimpleStrategy simpleStrategy = new SimpleStrategy(japUserService, new JapConfig());
        JapResponse japResponse = simpleStrategy.authenticate(new SimpleConfig(), new JakartaRequestAdapter(request), new JakartaResponseAdapter(response));
        if (!japResponse.isSuccess()) {
            return new ModelAndView(new RedirectView("/?error=" + URLUtil.encode(japResponse.getMessage())));
        }
        if (japResponse.isRedirectUrl()) {
            return new ModelAndView(new RedirectView((String) japResponse.getData()));
        } else {
            // 登录成功，需要对用户数据进行处理
            // ...
            System.out.println(japResponse.getData());
            return new ModelAndView(new RedirectView("/"));
        }
    }
*/

    /*// 进行授权
    @GetMapping("/1")
    public JapResponse simple1() {
        return japTemplate.opsForSimple().authenticate();
    }
    //授权方式2
    @GetMapping("/2")
    public JapResponse simple2(HttpServletRequest request, HttpServletResponse response){
        return simpleStrategy.authenticate(simpleProperties.getSimple(),request,response);
    }*/

}
