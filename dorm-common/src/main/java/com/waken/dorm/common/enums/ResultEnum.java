package com.waken.dorm.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @ClassName ResultEnum
 * @Description 返回结果枚举
 * @Author zhaoRong
 * @Date 2019/3/19 11:13
 **/
@Getter
@ToString
@AllArgsConstructor
public enum ResultEnum {
    SUCCESS("操作成功", "1"),
    FAIL("操作失败", "0"),
    FIRST_LOGIN("第一次登陆", "10"),
    OTHER_LOGIN("其他设备登陆", "410"),
    TIME_OUT("登陆过期，请重新登录", "411"),
    UNKNOWN_ERROR("服务端发生了未知错误,请联系管理员", "-2"),
    SERVER_ERROR("服务端异常", "500"),
    UN_PERMS("您没有对应的权限", "403"),
    UN_AUTH("尚未登录，无权限访问", "401"),
    LIMIT("你手速太快了，请稍后再试！", "501");
    private String msg;
    private String code;
}
