package com.pj.controller;

import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 会话治理
 * @author: Xu Yuwen
 * @Date: 2022-07-25 14:29
 */
@RestController
@Slf4j
@RequestMapping("/searchSession")
public class SearchSessionController {


    /**
     * @description: 查询包含关键字的所有token
     * @author: Xu Yuwen
     * @date: 2022/7/25 14:51
     * @param:
     * @return:
     * @return: java.lang.String
     **/
    @GetMapping("/searchTokenValue")
    public String searchTokenValue(){
        List<String> tokenList = StpUtil.searchTokenValue("f",0,30);
        for(String token : tokenList){
            log.info("token:"+token);
        }
        return  "结束";
    }
}
