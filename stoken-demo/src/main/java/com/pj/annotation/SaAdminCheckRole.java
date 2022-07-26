package com.pj.annotation;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.pj.util.StpAdminUtil;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 自定义角色权限注解
 * @author: Xu Yuwen
 * @date: 2022/7/26 9:31
 * @param: null
 * @return: null
 **/
@SaCheckRole(type= StpAdminUtil.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE})
public @interface SaAdminCheckRole {

    /**
     * 需要校验的权限码
     * @return 需要校验的权限码
     */
    @AliasFor(annotation = SaCheckRole.class)
    String [] value() default {};

    /**
     * 验证模式：AND | OR，默认AND
     * @return 验证模式
     */
    @AliasFor(annotation = SaCheckRole.class)
    SaMode mode() default SaMode.AND;
}
