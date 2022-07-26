package com.pj.configuration;

import cn.dev33.satoken.basic.SaBasicUtil;
import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.strategy.SaStrategy;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.json.JSONUtil;
import com.pj.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 权限注释拦截器  路由拦截
 * @author: Xu Yuwen
 * @Date: 2022-07-20 16:43
 */

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册Sa-Token的注解拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
        registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**");
        //只放出登陆接口，其余全都需要进行权限匹配
        /*registry.addInterceptor(new SaRouteInterceptor()).addPathPatterns("/**").excludePathPatterns("/users/doLogin");*/
        /**
         * @description: 自定义校验规则
         * @author: Xu Yuwen
         * @date: 2022/7/22 13:58
         * @param:
         * @param: registry
         * @return:
         **/
        registry.addInterceptor(new SaRouteInterceptor((req, res, handler)->{
            // 登录认证 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
            //SaRouter.match("/**", "/users/doLogin", r -> StpUtil.checkLogin());

            //角色认证  --  拦截以users开头的路由 满足角色权限为admin或者normal-admin之一即可
            //SaRouter.match("/users/**",r->StpUtil.checkRoleOr("admin","normal-admin"));

            /*SaRouter.match("/user/**", r -> StpUtil.checkPermission("user-add"));
            SaRouter.match("/kick/**",r->StpUtil.checkPermission("admin"));
            SaRouter.match("/permission/**",r->StpUtil.checkPermission("permission-user"));*/

            // 甚至你可以随意的写一个打印语句
            SaRouter.match("/**", r -> System.out.println("----啦啦啦----"));

            // 连缀写法
            SaRouter.match("/**").check(r -> System.out.println("----啦啦啦----"));

            Users users = new Users();
            users.setName("xyw");
            SaRouter.match("/**").check(r -> System.out.println("----111----"));
            //SaRouter.match("/**").check(r -> System.out.println("----222----")).stop();
            //SaRouter.match("/**").check(r -> System.out.println("----222----")).back(users);
            SaRouter.match("/**").check(r -> System.out.println("----333----"));
            SaRouter.match("/**").check(r -> System.out.println("----444----"));
            //free() 的作用是：打开一个独立的作用域，使内部的 stop() 不再一次性跳出整个 Auth 函数，而是仅仅跳出当前 free 作用域。
            SaRouter.match("/**").free(r -> {
                SaRouter.match("/**").check(c -> System.out.println("----555----"));
                SaRouter.match("/**").check(c -> System.out.println("----666----")).stop();
                SaRouter.match("/**").check(c -> System.out.println("----777----"));
            });
            // 执行 stop() 函数跳出 free 后继续执行下面的 match 匹配
            SaRouter.match("/**").check(r -> System.out.println("----888----"));


        })).addPathPatterns("/**");
    }

    /**
     * @description: 全局过滤器
     * @author: Xu Yuwen
     * @date: 2022/7/25 17:04
     * @param:
     * @return:
 * @return: cn.dev33.satoken.filter.SaServletFilter
     **/
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                //指定 拦截路由 和 放行路由
            .addInclude("/**").addExclude("/favicon.ico")

                //认证函数：每次请求执行
            .setAuth(obj -> {
                //SaRouter.match("/basicAuth/**", () -> SaBasicUtil.check("xyw:123")); 这个不好使 奇了怪了
                System.out.println("---------- 进入Sa-Token全局认证 -----------");

                // 登录认证 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
                //SaRouter.match("/**", "/user/doLogin", () -> StpUtil.checkLogin());
            })// 异常处理函数：每次认证函数发生异常时执行此函数
                .setError(e -> {
                    System.out.println("---------- 进入Sa-Token异常处理 -----------");
                    //return SaResult.error(e.getMessage());
                    SaHolder.getResponse().setHeader("Content-Type", "application/json;charset=UTF-8");
                    // 使用封装的 JSON 工具类转换数据格式
                    return JSONUtil.toJsonStr( SaResult.error(e.getMessage()) );
                })

                // 前置函数：在每次认证函数之前执行
                .setBeforeAuth(r -> {
                    // ---------- 设置一些安全响应头 ----------
                    SaHolder.getResponse()
                            // 服务器名称
                            .setServer("sa-server")
                            // 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以
                            .setHeader("X-Frame-Options", "SAMEORIGIN")
                            // 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
                            .setHeader("X-XSS-Protection", "1; mode=block")
                            // 禁用浏览器内容嗅探
                            .setHeader("X-Content-Type-Options", "nosniff")
                    ;
                })
                ;
    }

    /**
     * @description: 重写SaToken注解处理器 增加注解合并功能
     * @author: Xu Yuwen
     * @date: 2022/7/25 17:09
     * @param:
     * @return:
     **/
    @Autowired
    public void rewriteSaStrategy() {
        // 重写Sa-Token的注解处理器，增加注解合并功能
        SaStrategy.me.getAnnotation = (element, annotationClass) -> {
            return AnnotatedElementUtils.getMergedAnnotation(element, annotationClass);
        };
    }



        /**
         * @description: 特征匹配
         * @author: Xu Yuwen
         * @date: 2022/7/22 15:06
         * @param:
         * @param: null
         * @return:
         * @return: null
         **/
        // 基础写法样例：匹配一个path，执行一个校验函数
        //SaRouter.match("/user/**").check(r -> StpUtil.checkLogin());

        // 根据 path 路由匹配   ——— 支持写多个path，支持写 restful 风格路由
        // 功能说明: 使用 /user , /goods 或者 /art/get 开头的任意路由都将进入 check 方法
        //SaRouter.match("/user/**", "/goods/**", "/art/get/{id}").check( r -> StpUtil.checkLogin());

        // 根据 path 路由排除匹配
        // 功能说明: 使用 .html , .css 或者 .js 结尾的任意路由都将跳过, 不会进入 check 方法
        //SaRouter.match("/**").notMatch("*.html", "*.css", "*.js").check( r -> StpUtil.checkLogin());

        // 根据请求类型匹配
        //SaRouter.match(SaHttpMethod.GET).check( r -> StpUtil.checkLogin());

        // 根据一个 boolean 条件进行匹配
        //SaRouter.match( StpUtil.isLogin() ).check( r -> StpUtil.checkLogin());

        // 根据一个返回 boolean 结果的lambda表达式匹配
        //SaRouter.match( r -> StpUtil.isLogin() ).check( r -> StpUtil.checkLogin());

        // 多个条件一起使用
        // 功能说明: 必须是 Get 请求 并且 请求路径以 `/user/` 开头
        //SaRouter.match(SaHttpMethod.GET).match("/user/**").check( r -> StpUtil.checkLogin());

        // 可以无限连缀下去
        // 功能说明: 同时满足 Get 方式请求, 且路由以 /admin 开头, 路由中间带有 /send/ 字符串, 路由结尾不能是 .js 和 .css
        //SaRouter.match(SaHttpMethod.GET).match("/admin/**").match("/**/send/**").notMatch("/**/*.js").notMatch("/**/*.css").check( r -> StpUtil.checkLogin() );
}
