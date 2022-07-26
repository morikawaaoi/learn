package com.pj.annotation;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import com.pj.util.StpAdminUtil;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @description: 自定义注解 本注解专门用于对admin账号的权限注解
 * @author: Xu Yuwen
 * @date: 2022/7/26 8:46
 * @param: null
 * @return: null
 **/
@SaCheckPermission(type= StpAdminUtil.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE})
public @interface SaAdminCheckPermission {

    /**
     * 需要校验的权限码
     * @return 需要校验的权限码
     */
    @AliasFor(annotation = SaCheckPermission.class)
    String [] value() default {};

    /**
     * 验证模式：AND | OR，默认AND
     * @return 验证模式
     */
    @AliasFor(annotation = SaCheckPermission.class)
    SaMode mode() default SaMode.AND;
}
