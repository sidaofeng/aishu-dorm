package com.waken.dorm.common.view.dorm;

/**
 * @ClassName DormStudentRelView
 * @Description DormStudentRelView
 * @Author zhaoRong
 * @Date 2019/3/31 14:22
 **/
public class DormStudentRelView {
    private String pkDormStudentId;

    private String studentId;

    private String studentName;

    private String studentNum;

    public String getPkDormStudentId() {
        return pkDormStudentId;
    }

    public void setPkDormStudentId(String pkDormStudentId) {
        this.pkDormStudentId = pkDormStudentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }
}
