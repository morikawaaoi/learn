package com.pj.util;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.fun.SaFunction;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;

import java.util.List;

/**
 * @Description: 多账号相关操作认证
 *  我们会在一个项目中设计两套账号体系，比如一个电商系统的 user表 和 admin表， 在这种场景下，如果两套账号我们都使用 StpUtil 类的API进行登录鉴权，那么势必会发生逻辑冲突
 *  对于原生StpUtil类，我们只做admin账号权限认证，而对于user账号，我们则：
 *  新建一个新的权限认证类，比如： StpUserUtil.java。
 *  将StpUtil.java类的全部代码复制粘贴到 StpUserUtil.java里。
 *  更改一下其 LoginType,下面的新建stpLogin对象中也要替换为你新建的type
 * @author: Xu Yuwen
 * @Date: 2022-07-25 16:08
 */
public class StpAdminUtil {
    public static final String TYPE = "admin";
    public static StpLogic stpLogic = new StpLogic(TYPE){
        //重写config 自定义config对象
        SaTokenConfig config = new SaTokenConfig()
                .setTimeout(3592000)
                .setTokenName("admintoken")
        ;
        // 然后重写 stpLogic 配置获取方法
        @Override
        public SaTokenConfig getConfig() {
            return config;
        }

        // 重写 StpLogic 类下的 `splicingKeyTokenName` 函数，返回一个与 `StpUtil` 不同的token名称, 防止冲突
        @Override
        public String splicingKeyTokenName() {
            return super.splicingKeyTokenName() + "-admin";
        }
        // 同理你可以按需重写一些其它方法 ...
    };

    public StpAdminUtil() {
    }

    public static String getLoginType() {
        return stpLogic.getLoginType();
    }

    public static void setStpLogic(StpLogic stpLogic) {
        StpUtil.stpLogic = stpLogic;
        SaManager.putStpLogic(stpLogic);
    }

    public static String getTokenName() {
        return stpLogic.getTokenName();
    }

    public static void setTokenValue(String tokenValue) {
        stpLogic.setTokenValue(tokenValue);
    }

    public static void setTokenValue(String tokenValue, int cookieTimeout) {
        stpLogic.setTokenValue(tokenValue, cookieTimeout);
    }

    public static String getTokenValue() {
        return stpLogic.getTokenValue();
    }

    public static String getTokenValueNotCut() {
        return stpLogic.getTokenValueNotCut();
    }

    public static SaTokenInfo getTokenInfo() {
        return stpLogic.getTokenInfo();
    }

    public static void login(Object id) {
        stpLogic.login(id);
    }

    public static void login(Object id, String device) {
        stpLogic.login(id, device);
    }

    public static void login(Object id, boolean isLastingCookie) {
        stpLogic.login(id, isLastingCookie);
    }

    public static void login(Object id, SaLoginModel loginModel) {
        stpLogic.login(id, loginModel);
    }

    public static String createLoginSession(Object id) {
        return stpLogic.createLoginSession(id);
    }

    public static String createLoginSession(Object id, SaLoginModel loginModel) {
        return stpLogic.createLoginSession(id, loginModel);
    }

    public static void logout() {
        stpLogic.logout();
    }

    public static void logout(Object loginId) {
        stpLogic.logout(loginId);
    }

    public static void logout(Object loginId, String device) {
        stpLogic.logout(loginId, device);
    }

    public static void logoutByTokenValue(String tokenValue) {
        stpLogic.logoutByTokenValue(tokenValue);
    }

    public static void kickout(Object loginId) {
        stpLogic.kickout(loginId);
    }

    public static void kickout(Object loginId, String device) {
        stpLogic.kickout(loginId, device);
    }

    public static void kickoutByTokenValue(String tokenValue) {
        stpLogic.kickoutByTokenValue(tokenValue);
    }

    public static void replaced(Object loginId, String device) {
        stpLogic.replaced(loginId, device);
    }

    public static boolean isLogin() {
        return stpLogic.isLogin();
    }

    public static void checkLogin() {
        stpLogic.checkLogin();
    }

    public static Object getLoginId() {
        return stpLogic.getLoginId();
    }

    public static <T> T getLoginId(T defaultValue) {
        return stpLogic.getLoginId(defaultValue);
    }

    public static Object getLoginIdDefaultNull() {
        return stpLogic.getLoginIdDefaultNull();
    }

    public static String getLoginIdAsString() {
        return stpLogic.getLoginIdAsString();
    }

    public static int getLoginIdAsInt() {
        return stpLogic.getLoginIdAsInt();
    }

    public static long getLoginIdAsLong() {
        return stpLogic.getLoginIdAsLong();
    }

    public static Object getLoginIdByToken(String tokenValue) {
        return stpLogic.getLoginIdByToken(tokenValue);
    }

    public static Object getExtra(String key) {
        return stpLogic.getExtra(key);
    }

    public static SaSession getSessionByLoginId(Object loginId, boolean isCreate) {
        return stpLogic.getSessionByLoginId(loginId, isCreate);
    }

    public static SaSession getSessionBySessionId(String sessionId) {
        return stpLogic.getSessionBySessionId(sessionId);
    }

    public static SaSession getSessionByLoginId(Object loginId) {
        return stpLogic.getSessionByLoginId(loginId);
    }

    public static SaSession getSession(boolean isCreate) {
        return stpLogic.getSession(isCreate);
    }

    public static SaSession getSession() {
        return stpLogic.getSession();
    }

    public static SaSession getTokenSessionByToken(String tokenValue) {
        return stpLogic.getTokenSessionByToken(tokenValue);
    }

    public static SaSession getTokenSession() {
        return stpLogic.getTokenSession();
    }

    public static void checkActivityTimeout() {
        stpLogic.checkActivityTimeout();
    }

    public static void updateLastActivityToNow() {
        stpLogic.updateLastActivityToNow();
    }

    public static long getTokenTimeout() {
        return stpLogic.getTokenTimeout();
    }

    public static long getSessionTimeout() {
        return stpLogic.getSessionTimeout();
    }

    public static long getTokenSessionTimeout() {
        return stpLogic.getTokenSessionTimeout();
    }

    public static long getTokenActivityTimeout() {
        return stpLogic.getTokenActivityTimeout();
    }

    public static void renewTimeout(long timeout) {
        stpLogic.renewTimeout(timeout);
    }

    public static void renewTimeout(String tokenValue, long timeout) {
        stpLogic.renewTimeout(tokenValue, timeout);
    }

    public static List<String> getRoleList() {
        return stpLogic.getRoleList();
    }

    public static List<String> getRoleList(Object loginId) {
        return stpLogic.getRoleList(loginId);
    }

    public static boolean hasRole(String role) {
        return stpLogic.hasRole(role);
    }

    public static boolean hasRole(Object loginId, String role) {
        return stpLogic.hasRole(loginId, role);
    }

    public static boolean hasRoleAnd(String... roleArray) {
        return stpLogic.hasRoleAnd(roleArray);
    }

    public static boolean hasRoleOr(String... roleArray) {
        return stpLogic.hasRoleOr(roleArray);
    }

    public static void checkRole(String role) {
        stpLogic.checkRole(role);
    }

    public static void checkRoleAnd(String... roleArray) {
        stpLogic.checkRoleAnd(roleArray);
    }

    public static void checkRoleOr(String... roleArray) {
        stpLogic.checkRoleOr(roleArray);
    }

    public static List<String> getPermissionList() {
        return stpLogic.getPermissionList();
    }

    public static List<String> getPermissionList(Object loginId) {
        return stpLogic.getPermissionList(loginId);
    }

    public static boolean hasPermission(String permission) {
        return stpLogic.hasPermission(permission);
    }

    public static boolean hasPermission(Object loginId, String permission) {
        return stpLogic.hasPermission(loginId, permission);
    }

    public static boolean hasPermissionAnd(String... permissionArray) {
        return stpLogic.hasPermissionAnd(permissionArray);
    }

    public static boolean hasPermissionOr(String... permissionArray) {
        return stpLogic.hasPermissionOr(permissionArray);
    }

    public static void checkPermission(String permission) {
        stpLogic.checkPermission(permission);
    }

    public static void checkPermissionAnd(String... permissionArray) {
        stpLogic.checkPermissionAnd(permissionArray);
    }

    public static void checkPermissionOr(String... permissionArray) {
        stpLogic.checkPermissionOr(permissionArray);
    }

    public static String getTokenValueByLoginId(Object loginId) {
        return stpLogic.getTokenValueByLoginId(loginId);
    }

    public static String getTokenValueByLoginId(Object loginId, String device) {
        return stpLogic.getTokenValueByLoginId(loginId, device);
    }

    public static List<String> getTokenValueListByLoginId(Object loginId) {
        return stpLogic.getTokenValueListByLoginId(loginId);
    }

    public static List<String> getTokenValueListByLoginId(Object loginId, String device) {
        return stpLogic.getTokenValueListByLoginId(loginId, device);
    }

    public static String getLoginDevice() {
        return stpLogic.getLoginDevice();
    }

    public static List<String> searchTokenValue(String keyword, int start, int size) {
        return stpLogic.searchTokenValue(keyword, start, size);
    }

    public static List<String> searchSessionId(String keyword, int start, int size) {
        return stpLogic.searchSessionId(keyword, start, size);
    }

    public static List<String> searchTokenSessionId(String keyword, int start, int size) {
        return stpLogic.searchTokenSessionId(keyword, start, size);
    }

    public static void disable(Object loginId, long disableTime) {
        stpLogic.disable(loginId, disableTime);
    }

    public static boolean isDisable(Object loginId) {
        return stpLogic.isDisable(loginId);
    }

    public static long getDisableTime(Object loginId) {
        return stpLogic.getDisableTime(loginId);
    }

    public static void untieDisable(Object loginId) {
        stpLogic.untieDisable(loginId);
    }

    public static void switchTo(Object loginId) {
        stpLogic.switchTo(loginId);
    }

    public static void endSwitch() {
        stpLogic.endSwitch();
    }

    public static boolean isSwitch() {
        return stpLogic.isSwitch();
    }

    public static void switchTo(Object loginId, SaFunction function) {
        stpLogic.switchTo(loginId, function);
    }

    public static void openSafe(long safeTime) {
        stpLogic.openSafe(safeTime);
    }

    public static boolean isSafe() {
        return stpLogic.isSafe();
    }

    public static void checkSafe() {
        stpLogic.checkSafe();
    }

    public static long getSafeTime() {
        return stpLogic.getSafeTime();
    }

    public static void closeSafe() {
        stpLogic.closeSafe();
    }
}
