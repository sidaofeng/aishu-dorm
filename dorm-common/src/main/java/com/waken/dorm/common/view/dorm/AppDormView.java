package com.waken.dorm.common.view.dorm;

import com.waken.dorm.common.view.base.BaseView;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName AppDormView
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/3 20:49
 **/
@Getter
@Setter
public class AppDormView extends BaseView {

    private String id;

    private String buildingNum;

    private String buildingLevelth;

    private String dormNum;

    private Integer dormType;

    private String dormDesc;
}
