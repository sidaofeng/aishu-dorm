package com.waken.dorm.common.enums;

/**
 * @ClassName ResultEnum
 * @Description 返回结果枚举
 * @Author zhaoRong
 * @Date 2019/3/19 11:13
 **/
public enum  ResultEnum {
    SUCCESS("成功", "0"),
    FAIL("失败", "-1"),
    FIRST_LOGIN("第一次登陆","1"),
    OTHER_LOGIN("其他设备登陆","410"),
    TIME_OUT("登陆超时","411"),
    UN_AUTH("未授权","401");
    private String msg ;
    private String code ;

    private ResultEnum(String msg , String code ){
        this.msg = msg ;
        this.code = code ;
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
