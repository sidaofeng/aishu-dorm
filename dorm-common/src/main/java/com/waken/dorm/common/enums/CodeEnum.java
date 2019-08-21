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

    FILE("文件", 1), FILEDIRECTORY("文件夹", 0),

    IDCARD("身份证.", 1), PASSPORT("护照.", 2),

    PLATFORM_USER("后台用户", 1), SCHOOL_USER("学校用户", 2),

    ANDROID("Android", 1), IOS("IOS", 2), PC("PC", 3),

    DISABLE("无效", 0), ENABLE("生效", 1),

    DELETE("已删除", -1), DISCARD("作废", 0), CHECKED("已审核", 1), UNCOMMITTED("未提交", 2), UNCHECED("待审核", 3), UNPASSED("审核不通过", 4),

    MENU("menu", 1), BUTTON("button", 2), ROLE("role", 3),

    UNCOMPLETE("未完成", 0), COMPLETED("已完成", 1),

    VIDEO_CLASS_END_NODE("父节点", 0), VIDEO_CLASS_END_LEAF("叶子节点", 1),

    PROVINCE_LEVEL("省代", 1), CITY_LEVEL("市代", 2), AREA_LEVEL("区代", 3),

    REPAIRING("维修中", 1), REPAIRED("已维修", 2), SCRAP("已报废", 3),

    APP("APP端请求", 1), WEB("web端请求", 2);

    private String msg;
    private Integer code;

    private CodeEnum(String msg, Integer code) {
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
