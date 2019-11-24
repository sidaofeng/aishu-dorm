package com.waken.dorm.common.enums;

/**
 * @ClassName CodeEnum
 * @Description 返回结果枚举
 * @Author zhaoRong
 * @Date 2019/3/19 11:13
 **/
public enum CodeEnum {

    MALE("男", 1), FEMALE("女", 2),

    YES("是", 1), NO("否", 0),

    PLATFORM_USER("后台用户", 1), SCHOOL_USER("学校用户", 2),

    ANDROID("Android", 1), IOS("IOS", 2), PC("PC", 3),

    DISABLE("无效", 0), ENABLE("生效", 1),

    DELETE("已删除", -1), DISCARD("作废", 2), CHECKED("已审核", 3), UNCOMMITTED("未提交", 4), UNCHECED("待审核", 5), UNPASSED("审核不通过", 6),

    MENU("menu", 1), BUTTON("button", 2), ROLE("role", 3),

    UNCOMPLETE("未完成", 0), COMPLETED("已完成", 1),

    REPAIRING("维修中", 1), REPAIRED("已维修", 2), SCRAP("已报废", 3),

    APP("APP端请求", 1), WEB("web端请求", 2);

    private String msg;
    private Integer code;

    CodeEnum(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
