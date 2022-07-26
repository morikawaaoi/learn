package com.pj.controller;

import cn.dev33.satoken.annotation.*;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.pj.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 注解鉴权
 * @author: Xu Yuwen
 * @Date: 2022-07-21 11:44
 */
@RestController
@Slf4j
@RequestMapping("/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;


    /**
     * @description: 根据用户权限来判断是否能调用 若无权限 抛出异常
     * @author: Xu Yuwen
     * @date: 2022/7/20 16:57
     * @param:
     * @return:
     * @return: java.lang.String
     **/
    @SaCheckPermission("user-add")
    @GetMapping("/add")
    public String add(){
        log.info("当前id权限列表："+ StpUtil.getPermissionList());
        log.info("判断当前id是否有某个权限："+StpUtil.hasPermission("user-add"));
        // 获取：当前账号所拥有的权限集合
        StpUtil.getPermissionList();

        // 判断：当前账号是否含有指定权限, 返回true或false
        StpUtil.hasPermission("user-update");

        // 校验：当前账号是否含有指定权限, 如果验证未通过，则抛出异常: NotPermissionException
        StpUtil.checkPermission("user-update");

        // 校验：当前账号是否含有指定权限 [指定多个，必须全部验证通过]
        StpUtil.checkPermissionAnd("user-update", "user-delete");

        // 校验：当前账号是否含有指定权限 [指定多个，只要其一验证通过即可]
        StpUtil.checkPermissionOr("user-update", "user-delete");
        return "很ok";
    }

    /**
     * @description: 权限通配符
     * @author: Xu Yuwen
     * @date: 2022/7/21 9:10
     * @param:
     * @return:
     * @return: java.lang.String
     **/
    @SaCheckPermission("permission-add")
    @GetMapping("/addP")
    public String addP(){
        log.info("当前id权限列表："+StpUtil.getPermissionList());
        log.info("判断当前id是否有某个权限："+StpUtil.hasPermission("user-add"));
        // 判断：当前账号是否含有指定权限, 返回true或false
        //true
        StpUtil.hasPermission("permission-add");
        //true
        StpUtil.hasPermission("permission-update");
        //false
        StpUtil.hasPermission("get-add");

        return "ok";
    }


    @SaCheckRole
    @GetMapping("/roleAdd")
    public String roleAdd(){
        log.info("当前id角色标识列表："+StpUtil.getRoleList());
        log.info("判断当前id是否有某个权限："+StpUtil.hasRole("admin"));
        log.info("判断当前id是否某些权限（全部满足）："+StpUtil.hasRoleAnd("admin","man-admin"));
        log.info("判断当前id是否某些权限（其中之一）："+StpUtil.hasRoleOr("admin","woman-admin"));

        // 获取：当前账号所拥有的角色集合
        StpUtil.getRoleList();

        // 判断：当前账号是否拥有指定角色, 返回true或false
        StpUtil.hasRole("super-admin");

        // 校验：当前账号是否含有指定角色标识, 如果验证未通过，则抛出异常: NotRoleException
        StpUtil.checkRole("super-admin");

        // 校验：当前账号是否含有指定角色标识 [指定多个，必须全部验证通过]
        StpUtil.checkRoleAnd("super-admin", "shop-admin");

        // 校验：当前账号是否含有指定角色标识 [指定多个，只要其一验证通过即可]
        StpUtil.checkRoleOr("super-admin", "shop-admin");

        return "ok";
    }

    /**
     * @description: 登录认证 只有登陆后才能进入该方法 如果未登陆报异常
     * @author: Xu Yuwen
     * @date: 2022/7/21 11:47
     * @param:
     * @return:
     * @return: java.lang.String
     **/
    @SaCheckLogin
    @GetMapping("/info")
    public String info(){
        return "查询用户信息";
    }

    /**
     * @description: 二级认证 必须二级认证之后才能进入该方法
     * @author: Xu Yuwen
     * @date: 2022/7/21 11:49
     * @param:
     * @param: null
     * @return:
     * @return: null
     **/
    @SaCheckSafe
    @GetMapping("/addSafe")
    public String addSafe(){
        return "二级认证";
    }

    /**
     * @description:Http Basic 认证：只有通过 Basic 认证后才能进入该方法
     * @author: Xu Yuwen
     * @date: 2022/7/21 13:43
     * @param:
     * @return:
     * @return: java.lang.String
     **/
    @SaCheckBasic(account = "sa:123456")
    @GetMapping("/addBasic")
    public String addBasic() {
        return "用户增加";
    }
    /**
     * @description: 权限满足其中一个即可
     * @author: Xu Yuwen
     * @date: 2022/7/21 13:54
     * @param:
     * @return:
     * @return: cn.dev33.satoken.util.SaResult
     **/
    @SaCheckPermission(value = {"user-add", "user-all", "user-delete"}, mode = SaMode.OR)
    @GetMapping("/atJurOr")
    public SaResult atJurOr(){
        return SaResult.data("用户信息");
    }

    /**
     * @description: 权限必须全部满足
     * @author: Xu Yuwen
     * @date: 2022/7/21 14:00
     * @param:
     * @return:
     * @return: cn.dev33.satoken.util.SaResult
     **/
    @SaCheckPermission(value = {"user-add", "user-all", "user-delete"}, mode = SaMode.AND)
    @GetMapping("/atJurAnd")
    public SaResult atJurAnd(){
        return SaResult.data("用户信息And");
    }

    /**
     * @description:角色权限双重检验 or校验  只要满足其中之一就行
     * @author: Xu Yuwen
     * @date: 2022/7/22 9:25
     * @param: 
     * @param: null
     * @return: 
     * @return: null
     **/
    @SaCheckPermission(value="user-add",orRole = "admin")
    @GetMapping("/userAdd")
    public String userAdd(){
        return "双重检测";
    }

    @GetMapping("/userAddP")
    public String userAddP(){
        return permissionService.aaa();
    }



}
