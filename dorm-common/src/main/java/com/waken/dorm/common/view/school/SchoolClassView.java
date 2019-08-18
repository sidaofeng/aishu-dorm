package com.waken.dorm.common.view.school;

import com.waken.dorm.common.view.base.BaseView;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName SchoolClassView
 * @Description SchoolClassView
 * @Author zhaoRong
 * @Date 2019/4/4 23:25
 **/
@Getter
@Setter
public class SchoolClassView extends BaseView {
    private String pkSchoolClassId;

    private String className;

    private String parentId;
}
