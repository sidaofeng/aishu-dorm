package com.waken.dorm.common.constant;

/**
 * @ClassName CodeEnum
 * @Description 常数类
 * @Author zhaoRong
 * @Date 2019/3/19 11:13
 **/
public class Constant {
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

    public static final String ANON = "Anon";
    /**
     * 加密key
     */
    public static final String SERC_KEY1 = "o28feng#bao#bao~`()";
    public static final String SERC_KEY2 = "o28~`(feng#bao#bao)";

    public static final String SPLIT = "/";

    public static final String SPLIT_UNDERLINE = "_";

    public static final String UNDER_LINE = "-";

    public static final String NULL_STRING = "";

    public static final String SPLIT_BLANK = " ";

    public static final String SPLIT_COMMA = ",";

    public static final String SPLIT_SPOT = "\\.";

    public static final String SPOT = ".";

    public static final String SPLIT_COLON = ":";

    public static final int ZERO = 0;

    public static final int ONE = 1;

    public static final int TWO = 2;

    public static final int TEN = 10;

    public static final String STRING_ZERO = "0";

    public static final int DEFAULT_LENGTH = 1024000;

    public static final String SuperAdmin = "superAdmin";

    public static final String JPG = "jpg";

    public static final String STUDENT = "student";

    public static final String ROOT = "root";//跟节点

}
