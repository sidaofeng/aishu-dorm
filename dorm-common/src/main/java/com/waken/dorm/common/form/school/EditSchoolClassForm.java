package com.waken.dorm.common.form.school;

import java.util.Date;

/**
 * @ClassName EditSchoolClassForm
 * @Description EditSchoolClassForm
 * @Author zhaoRong
 * @Date 2019/4/1 18:34
 **/
public class EditSchoolClassForm {
    private String pkSchoolClassId;

    private String schoolId;

    private String parentId;

    private String className;

    private String classDesc;

    private String memo;

    public String getPkSchoolClassId() {
        return pkSchoolClassId;
    }

    public void setPkSchoolClassId(String pkSchoolClassId) {
        this.pkSchoolClassId = pkSchoolClassId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "EditSchoolClassForm{" +
                "pkSchoolClassId='" + pkSchoolClassId + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", className='" + className + '\'' +
                ", classDesc='" + classDesc + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
