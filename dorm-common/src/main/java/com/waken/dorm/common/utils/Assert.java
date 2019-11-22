package com.waken.dorm.common.utils;

import com.waken.dorm.common.exception.ServerException;
import org.apache.commons.lang3.StringUtils;

/**
 * @description: 自定义断言
 * @author: aishu
 * @create: 2019-08-22 15:43
 */
public class Assert {
    private final static String NULL_MSG = "参数为空！";
    private final static String FAIL_MSG = "操作失败！";
    public Assert(){}
    public static void notNull(Object object) {
        if (null == object) {
            throw new ServerException(NULL_MSG);
        }
    }
    public static void notNull(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new ServerException(NULL_MSG);
        }
    }
    public static void notNull(Integer i) {
        if (null == i) {
            throw new ServerException(NULL_MSG);
        }
    }
    public static void notNull(Long L) {
        if (null == L) {
            throw new ServerException(NULL_MSG);
        }
    }
    public static void notNull(Object object, String message) {
        if (null == object) {
            throw new ServerException(message);
        }
    }
    public static void notNull(String str, String message) {
        if (StringUtils.isEmpty(str)) {
            throw new ServerException(message);
        }
    }
    public static void notNull(Integer i, String message) {
        if (null == i) {
            throw new ServerException(message);
        }
    }
    public static void notNull(Long L, String message) {
        if (null == L) {
            throw new ServerException(message);
        }
    }
    public static void notNull(Object object,boolean expression, String message) {
        if (null == object || expression) {
            throw new ServerException(message);
        }
    }
    public static void isNull(Object object, String message) {
        if (null != object) {
            throw new ServerException(message);
        }
    }
    public static void isNull(String str, String message) {
        if (StringUtils.isNotBlank(str)) {
            throw new ServerException(message);
        }
    }
    public static void isNull(Integer i, String message) {
        if (null != i) {
            throw new ServerException(message);
        }
    }
    public static void isNull(Long L, String message) {
        if (null != L) {
            throw new ServerException(message);
        }
    }
    public static void isNull(Object object,boolean expression, String message) {
        if (null != object && !expression) {
            throw new ServerException(message);
        }
    }
    public static void isTrue(boolean expression,String message){
        if (!expression){
            throw new ServerException(message);
        }
    }
    public static void isTrue(boolean expression){
        if (!expression){
            throw new ServerException(FAIL_MSG);
        }
    }
    public static void isFalse(boolean expression,String message){
        if (expression){
            throw new ServerException(message);
        }
    }
    public static void isFalse(boolean expression){
        if (expression){
            throw new ServerException(FAIL_MSG);
        }
    }
}