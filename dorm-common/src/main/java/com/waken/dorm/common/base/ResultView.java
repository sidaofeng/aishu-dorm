package com.waken.dorm.common.base;

/**
 * @ClassName ResultView
 * @Description 返回结果视图
 * @Author zhaoRong
 * @Date 2019/3/19 11:08
 **/
public class ResultView {
    private String code ="0";
    private String msg="操作成功";
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}
