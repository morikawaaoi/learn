package com.pj.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.pj.annotation.SaAdminCheckPermission;
import com.pj.annotation.SaAdminCheckRole;
import com.pj.util.StpAdminUtil;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 多账号下admin相关权限等设置
 * @author: Xu Yuwen
 * @Date: 2022-07-25 16:24
 */
@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {



    @GetMapping("/doLoginAdmin")
    public SaResult doLoginAdmin(String username ,String password){
        if("xywadmin".equals(username) && "123".equals(password)){
            StpAdminUtil.login(10001,"PC");
            //检验是否已经登陆
            StpAdminUtil.checkLogin();
            log.info("当前会话id："+ StpAdminUtil.getLoginId());
            //获取token名称
            log.info("当前tokenid："+StpAdminUtil.getTokenName());
            //获取token值
            log.info("当前token值："+StpAdminUtil.getTokenValue());
            log.info("当前token信息："+StpAdminUtil.getTokenInfo());
            log.info("当前token剩余有效期："+StpAdminUtil.getTokenTimeout());

            return SaResult.ok("admin10001登陆成功");
        }
        return SaResult.ok("失败");
    }


    @SaCheckLogin(type=StpAdminUtil.TYPE)
    @GetMapping("/adminInfo")
    public String adminInfo(){
        return "admin用户信息";
    }


    /**
     * @description: 不填写mode 默认为AND
     * @author: Xu Yuwen
     * @date: 2022/7/26 9:20
     * @param:
     * @return:
     * @return: java.lang.String
     **/
    @SaAdminCheckPermission(value={"user-info","user-check"},mode= SaMode.AND)
    @GetMapping("/adminPermissionInfoA")
    public String adminPermissionInfoA(){
        return "我有权限进来了！";
    }

    @SaAdminCheckPermission(value = {"user-info","user-check"} , mode= SaMode.OR )
    @GetMapping("/adminPermissionInfoB")
    public String adminPermissionInfoB(){
        return "我有权限进来了！";
    }

    /**
     * @description: 自定义注解添加admin角色权限
     * @author: Xu Yuwen
     * @date: 2022/7/26 9:36
     * @param:
     * @return:
     * @return: java.lang.String
     **/
    @SaAdminCheckRole(value={"normal-user","normal-admin"},mode = SaMode.AND)
    @GetMapping("/adminRoleInfoA")
    public String adminRoleInfoA(){
        return "我是这个角色我进来了";
    }

    @SaAdminCheckRole(value={"normal-user","normal-admin"},mode = SaMode.OR)
    @GetMapping("/adminRoleInfoB")
    public String adminRoleInfoB(){
        return "我是这个角色我进来了";
    }

}
