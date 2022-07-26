package com.pj.stp;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 实现StpInterface接口重写方法
 * @author: Xu Yuwen
 * @Date: 2022-07-20 16:20
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    /**
     * @description: 返回一个账号所拥有的权限码集合
     * @author: Xu Yuwen
     * @date: 2022/7/20 16:21
     * @param:
     * @param: o
     * @param: s
     * @return:
     * @return: java.util.List<java.lang.String>
     **/
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        //此处就可以来获取对应的角色的权限列表 最后返回为list的形式即可。
        //举例子
        List<String> list = new ArrayList<String>();

        list.add("101");
        list.add("user-add");
        list.add("user-delete");
        list.add("user-update");
        list.add("user-get");
        list.add("user-info");
        list.add("article-get");
        list.add("permission*");
        return list;
    }
    /**
     * @description: 返回一个账号的角色标识集合
     * @author: Xu Yuwen
     * @date: 2022/7/20 16:21
     * @param:
     * @param: o
     * @param: s
     * @return:
     * @return: java.util.List<java.lang.String>
     **/
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        //角色标志与权限设置相同 同样最后返回list
        List<String> list = new ArrayList<String>();
        list.add("admin");
        list.add("super-admin");
        list.add("fresh-admin");
        list.add("normal-admin");
        list.add("man-admin");
        return list;
    }
}
