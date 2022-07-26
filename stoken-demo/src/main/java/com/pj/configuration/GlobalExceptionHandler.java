package com.pj.configuration;

import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description: 全局异常拦截器
 * @author: Xu Yuwen
 * @Date: 2022-07-25 11:36
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        e.printStackTrace();
        return SaResult.error(e.getMessage());
    }
}
