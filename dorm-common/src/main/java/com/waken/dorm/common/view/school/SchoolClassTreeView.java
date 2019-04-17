package com.waken.dorm.common.view.school;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SchoolClassTreeView
 * @Description 学校类别树形视图
 * @Author zhaoRong
 * @Date 2019/4/5 23:21
 **/
public class SchoolClassTreeView {
    private String pkSchoolClassId;

    private String className;

    List<SchoolClassTreeView> childSchoolClassTreeView = new ArrayList<SchoolClassTreeView>();

    public String getPkSchoolClassId() {
        return pkSchoolClassId;
    }

    public void setPkSchoolClassId(String pkSchoolClassId) {
        this.pkSchoolClassId = pkSchoolClassId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<SchoolClassTreeView> getChildSchoolClassTreeView() {
        return childSchoolClassTreeView;
    }

    public void setChildSchoolClassTreeView(List<SchoolClassTreeView> childSchoolClassTreeView) {
        this.childSchoolClassTreeView = childSchoolClassTreeView;
    }
}
