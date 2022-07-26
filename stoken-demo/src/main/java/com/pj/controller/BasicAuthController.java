package com.pj.controller;

import cn.dev33.satoken.annotation.SaCheckBasic;
import cn.dev33.satoken.basic.SaBasicUtil;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: http basic校验
 * @author: Xu Yuwen
 * @Date: 2022-07-25 11:18
 */
@RestController
@Slf4j
@RequestMapping("/basicAuth")
public class BasicAuthController {

    @GetMapping("/basicCheckA")
    public SaResult basicCheckA(){
        SaBasicUtil.check("xyw:123");
        return SaResult.ok("验证成功！");
    }

    @SaCheckBasic(account = "sa:123456")
    @GetMapping("/checkBasic")
    public SaResult checkBasic() {
        return SaResult.ok();
    }

    @RequestMapping("/basicCheckB")
    public SaResult basicCheckB(){
        //SaBasicUtil.check("xyw:123");
        return SaResult.ok("验证成功！");
    }


}
