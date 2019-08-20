package com.waken.dorm.common.view.school;

import com.waken.dorm.common.view.base.BaseView;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SchoolClassTreeView
 * @Description 学校类别树形视图
 * @Author zhaoRong
 * @Date 2019/4/5 23:21
 **/
@Getter
@Setter
public class SchoolClassTreeView extends BaseView {
    List<SchoolClassTreeView> childSchoolClassTreeView = new ArrayList<SchoolClassTreeView>();
    private String pkSchoolClassId;
    private String className;
}
