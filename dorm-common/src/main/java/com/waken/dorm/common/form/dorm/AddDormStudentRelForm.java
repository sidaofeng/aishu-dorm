package com.waken.dorm.common.form.dorm;

import java.util.List;

/**
 * @ClassName AddDormStudentRelForm
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/3/31 14:12
 **/
public class AddDormStudentRelForm {
    private String pkDormId; //需要绑定的宿舍id

    private List<String> pkStudentIds; //需要绑定的学生 id 集合

    public String getPkDormId() {
        return pkDormId;
    }

    public void setPkDormId(String pkDormId) {
        this.pkDormId = pkDormId;
    }

    public List<String> getPkStudentIds() {
        return pkStudentIds;
    }

    public void setPkStudentIds(List<String> pkStudentIds) {
        this.pkStudentIds = pkStudentIds;
    }

    @Override
    public String toString() {
        return "AddDormStudentRelForm{" +
                "pkDormId='" + pkDormId + '\'' +
                ", pkStudentIds=" + pkStudentIds +
                '}';
    }
}
