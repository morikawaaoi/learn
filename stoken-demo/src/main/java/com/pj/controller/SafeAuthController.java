package com.pj.controller;

import cn.dev33.satoken.annotation.SaCheckSafe;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 二级认证
 * @author: Xu Yuwen
 * @Date: 2022-07-25 10:44
 */
@RestController
@Slf4j
@RequestMapping("/safeAuth")
public class SafeAuthController {


    @GetMapping("/openSafe")
    public SaResult openSafe(String password){
        if("123".equals(password)){
            StpUtil.openSafe(120);
            log.info("打开二级认证");
            return SaResult.ok("成功打开二级认证");
        }
        log.info("二级认证打开失败");
        return SaResult.error("二级认证打开失败");
    }


    @GetMapping("/deleteInfoA")
    public SaResult deleteInfoA(){
        log.info("是否进行二级认证："+StpUtil.isSafe());
        if(!StpUtil.isSafe()){
            return SaResult.error("先进行二级认证");
        }
        //相关操作
        log.info("已经进行二级认证");
        return SaResult.ok("已经进行二级认证");
    }

    /**
     * @description: 注解鉴权 使用二级认证  如果没有进行二级认证 直接抛出异常
     * @author: Xu Yuwen
     * @date: 2022/7/25 11:09
     * @param:
     * @return:
     * @return: cn.dev33.satoken.util.SaResult
     **/
    @SaCheckSafe
    @GetMapping("/deleteInfoB")
    public SaResult deleteInfoB(){
        //相关操作
        log.info("已经进行二级认证");
        return SaResult.ok("已经进行二级认证");
    }

}
