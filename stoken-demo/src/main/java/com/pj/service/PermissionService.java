package com.pj.service;

import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: Xu Yuwen
 * @Date: 2022-07-22 09:34
 */
@Service
public class PermissionService {

    @SaCheckPermission("user-add")
    public String aaa(){
        return "来了";
    }

}
