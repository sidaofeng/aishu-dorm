package com.waken.dorm.common.enums;

/**
 *
 * @author zhaoRong
 */
public enum AccessStrategy {
    /**
     * 需要验证权限
     */
    Verification,
    /**
     * 登录之后便能访问
     */
    Auth,
    /**
     * 测试用户不拦截过滤
     */
    Guest,
    /**
     * 测试用户不拦截过滤
     */
    Anon;

    private AccessStrategy() {
    }
}
