package com.waken.dorm.common.form.dorm;

/**
 * @ClassName UpdateDormViolationForm
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/2 12:57
 **/
public class UpdateDormViolationForm {
    private String pkDormViolationId;

    private Integer status;

    private String memo;

    public String getPkDormViolationId() {
        return pkDormViolationId;
    }

    public void setPkDormViolationId(String pkDormViolationId) {
        this.pkDormViolationId = pkDormViolationId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "UpdateDormViolationForm{" +
                "pkDormViolationId='" + pkDormViolationId + '\'' +
                ", status=" + status +
                ", memo='" + memo + '\'' +
                '}';
    }
}
