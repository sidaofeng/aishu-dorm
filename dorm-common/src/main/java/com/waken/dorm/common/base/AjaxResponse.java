package com.waken.dorm.common.base;

import com.waken.dorm.common.enums.ResultEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName
 * @Description 响应model
 * @Author zhaoRong
 * @Date 2019/6/10 16:55
 **/
@Getter
@Setter
public class AjaxResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code = "1";

    private String msg = "操作成功";

    private Object data = "";

    public AjaxResponse(){}

    public AjaxResponse(Object data){
        this.data = data;
    }
    public AjaxResponse(String msg){
        this.msg = msg;
    }
    public AjaxResponse(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public AjaxResponse(String code,Object data){
        this.code = code;
        this.data = data;
    }


    public AjaxResponse(String code,String msg,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }



    public static AjaxResponse success() {
        return new AjaxResponse();
    }

    public static AjaxResponse success(Object object) {
        return new AjaxResponse(object);
    }

    public static AjaxResponse success(String msg) {
        return new AjaxResponse(msg);
    }

    public static AjaxResponse success(String code,Object object) {
        return new AjaxResponse(code,object);
    }

    public static AjaxResponse error() {
        return new AjaxResponse(ResultEnum.FAIL.getCode(),ResultEnum.FAIL.getMsg());
    }

    public static AjaxResponse error(String code, String msg) {
        return new AjaxResponse(code,msg);
    }

    public static AjaxResponse error(ResultEnum resultEnum) {
        return new AjaxResponse(resultEnum.getCode(),resultEnum.getMsg());
    }
    public static AjaxResponse error(ResultEnum resultEnum,Object object) {
        return new AjaxResponse(resultEnum.getCode(),resultEnum.getMsg(),object);
    }

    public static AjaxResponse error(Object object) {
        return AjaxResponse.error(ResultEnum.FAIL,object);
    }

    public static AjaxResponse error(String msg) {
        return AjaxResponse.error(ResultEnum.FAIL.getCode(),msg);
    }
}
