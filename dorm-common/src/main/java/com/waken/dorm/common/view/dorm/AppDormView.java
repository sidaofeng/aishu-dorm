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

    private String buildingCode;

    private String floorName;

    private String code;

    private Integer type;

    private String memo;
}
