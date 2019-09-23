package com.waken.dorm.common.annotation;

import com.waken.dorm.common.enums.AccessStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author aishu
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrivilegeResource {
    String code() default "";//资源编码

    String desc() default "";//描述

    AccessStrategy strategy() default AccessStrategy.Verification;//默认需要验证权限

}
