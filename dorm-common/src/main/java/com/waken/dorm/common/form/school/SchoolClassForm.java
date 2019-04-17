package com.waken.dorm.common.form.school;

/**
 * @ClassName SchoolClassForm
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/4 23:22
 **/
public class SchoolClassForm{
    private String pkSchoolClassId;

    private String schoolId;

    private String className;

    private Integer status;

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SchoolClassForm{" +
                "pkSchoolClassId='" + pkSchoolClassId + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", className='" + className + '\'' +
                ", status=" + status +
                '}';
    }
}
