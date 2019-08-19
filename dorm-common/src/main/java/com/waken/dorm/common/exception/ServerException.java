package com.waken.dorm.common.exception;

import com.waken.dorm.common.enums.ResultEnum;

/**
 * @ClassName ZhouException
 * @Description 异常处理
 * @Author zhaoRong
 * @Date 2019/3/19 10:51
 **/
public class ServerException extends RuntimeException {
    private String code;

    public ServerException() {

    }

    public ServerException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public ServerException(String s) {
        super(s);
        this.code = ResultEnum.FAIL.getCode();
    }

    public ServerException(String s, String code) {
        super(s);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
