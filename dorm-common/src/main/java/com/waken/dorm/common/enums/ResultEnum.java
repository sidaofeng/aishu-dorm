package com.waken.dorm.common.enums;

/**
 * @ClassName ResultEnum
 * @Description 返回结果枚举
 * @Author zhaoRong
 * @Date 2019/3/19 11:13
 **/
public enum ResultEnum {
    SUCCESS("操作成功", "1"),
    FAIL("操作失败", "0"),
    FIRST_LOGIN("第一次登陆", "10"),
    OTHER_LOGIN("其他设备登陆", "410"),
    TIME_OUT("登陆过期，请重新登录", "411"),
    UNKNOWN_ERROR("未知错误", "-2"),
    SERVER_ERROR("服务端异常", "500"),
    UN_PERMS("您没有对应的权限", "403"),
    UN_AUTH("尚未登录，无权限访问", "401");
    private String msg;
    private String code;

    ResultEnum(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
