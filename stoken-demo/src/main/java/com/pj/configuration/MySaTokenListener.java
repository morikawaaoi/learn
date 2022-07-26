package com.pj.configuration;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import org.springframework.stereotype.Component;

/**
 * @Description: 自定义全局监听器
 * @author: Xu Yuwen
 * @Date: 2022-07-25 15:13
 */
@Component
public class MySaTokenListener implements SaTokenListener {

    /**
     *  每次登陆时触发
     * */
    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel saLoginModel) {
        System.out.println(loginType+"我登陆了 我被盯上了");
    }
    /**
     *  每次注销时触发
     * */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {

    }
    /**
     *  每次被踢出时触发
     * */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {

    }
    /**
     *  每次被顶下线时触发
     * */
    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {

    }
    /**
     *  每次被封禁时触发
     * */
    @Override
    public void doDisable(String loginType, Object loginId, long disableTime) {

    }
    /**
     *  每次被解封时触发
     * */
    @Override
    public void doUntieDisable(String loginType, Object loginId) {

    }
    /**
     *  每次创建session时触发
     * */
    @Override
    public void doCreateSession(String id) {

    }
    /**
     *  每次注销session时触发
     * */
    @Override
    public void doLogoutSession(String id) {

    }
}
