package com.waken.dorm.common.constant;

/**
 * @description: 缓存常数类
 * @author: aishu
 * @create: 2019-08-22 15:23
 */
public class CacheConstant {
    public static final String USER_TOKEN = "AuthenticationToken";

    public static final String STUDENT_TOKEN = "StudentToken";

    public static final String STUDENT_CACHE_PREFIX = "dorm.cache.student.";
    // user缓存前缀
    public static final String USER_CACHE_PREFIX = "dorm.cache.user.";
    // user角色缓存前缀
    public static final String USER_ROLE_CACHE_PREFIX = "dorm.cache.user.role.";
    // user权限缓存前缀
    public static final String USER_PERMISSION_CACHE_PREFIX = "dorm.cache.user.permission.";

    public static final String TOKEN_CACHE_PREFIX = "dorm.cache.token.";
    // 存储在线用户的 zset前缀
    public static final String ACTIVE_USERS_ZSET_PREFIX = "dorm.user.active";
}