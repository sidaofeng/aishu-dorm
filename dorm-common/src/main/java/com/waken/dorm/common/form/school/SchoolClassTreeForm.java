package com.waken.dorm.common.form.school;

/**
 * @ClassName SchoolClassTreeForm
 * @Description SchoolClassTreeForm
 * @Author zhaoRong
 * @Date 2019/4/5 23:24
 **/
public class SchoolClassTreeForm {

    private String pkSchoolClassId;

    private String parentId;

    private String schoolId;

    public String getPkSchoolClassId() {
        return pkSchoolClassId;
    }

    public void setPkSchoolClassId(String pkSchoolClassId) {
        this.pkSchoolClassId = pkSchoolClassId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
        return "SchoolClassTreeForm{" +
                "pkSchoolClassId='" + pkSchoolClassId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", schoolId='" + schoolId + '\'' +
                '}';
    }
}
