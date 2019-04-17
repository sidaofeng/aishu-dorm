package com.waken.dorm.common.view.school;

/**
 * @ClassName SchoolClassView
 * @Description SchoolClassView
 * @Author zhaoRong
 * @Date 2019/4/4 23:25
 **/
public class SchoolClassView {
    private String pkSchoolClassId;

    private String className;

    private String parentId;

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
