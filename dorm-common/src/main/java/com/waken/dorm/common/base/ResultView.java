package com.waken.dorm.common.base;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ResultView
 * @Description 返回结果视图
 * @Author zhaoRong
 * @Date 2019/3/19 11:08
 **/
@Getter
@Setter
public class ResultView {
    private String code ="0";

    private String msg="操作成功";

    private Object data;
}
