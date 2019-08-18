package com.waken.dorm.common.enums;

public enum AccessStrategy {
    Verification,//需要验证权限
    Auth,//登录之后便能访问
    Guest;//测试用户不拦截过滤

    private AccessStrategy() {
    }
}
