package com.waken.dorm.common.exception;

/**
 * @ClassName ZhouException
 * @Description 异常处理
 * @Author zhaoRong
 * @Date 2019/3/19 10:51
 **/
public class DormException extends RuntimeException {
    public DormException() {

    }
    public DormException(String s) {
        super(s);
    }

    public DormException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DormException(Throwable throwable) {
        super(throwable);
    }
}
