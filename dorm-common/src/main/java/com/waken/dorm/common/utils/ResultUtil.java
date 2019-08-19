package com.waken.dorm.common.utils;

import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.enums.ResultEnum;

/**
 * @ClassName ResultUtil
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/6/10 16:55
 **/
public class ResultUtil {
    public static ResultView success() {
        return new ResultView();
    }

    public static ResultView success(Object object) {
        ResultView resultView = new ResultView();
        resultView.setData(object);
        return resultView;
    }

    public static ResultView success(Object object, String msg) {
        ResultView resultView = new ResultView();
        resultView.setData(object);
        resultView.setMsg(msg);
        return resultView;
    }

    public static ResultView successByMsg(String msg) {
        ResultView resultView = new ResultView();
        resultView.setMsg(msg);
        return resultView;
    }

    public static ResultView error() {
        ResultView resultView = new ResultView();
        resultView.setCode(ResultEnum.FAIL.getCode());
        resultView.setMsg(ResultEnum.FAIL.getMsg());
        return resultView;
    }

    public static ResultView error(String code, String msg) {
        ResultView resultView = new ResultView();
        resultView.setCode(code);
        resultView.setMsg(msg);
        return resultView;
    }

    public static ResultView error(ResultEnum resultEnum) {
        ResultView resultView = new ResultView();
        resultView.setCode(resultEnum.getCode());
        resultView.setMsg(resultEnum.getMsg());
        return resultView;
    }

    public static ResultView error(Object object) {
        ResultView resultView = new ResultView();
        resultView.setData(object);
        return resultView;
    }

    public static ResultView errorByMsg(String msg) {
        ResultView resultView = new ResultView();
        resultView.setCode(ResultEnum.FAIL.getCode());
        resultView.setMsg(msg);
        return resultView;
    }
}
