package com.waken.dorm.common.form.dorm;

import java.math.BigDecimal;

/**
 * @ClassName UpdateRepairForm
 * @Description 更新维修表form表单
 * @Author zhaoRong
 * @Date 2019/4/1 21:09
 **/
public class UpdateRepairForm {

    private String pkDormRepairId;

    private Integer status;

    private BigDecimal repairCost;

    private String repairBillUrl;

    private String memo;

    public String getPkDormRepairId() {
        return pkDormRepairId;
    }

    public void setPkDormRepairId(String pkDormRepairId) {
        this.pkDormRepairId = pkDormRepairId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(BigDecimal repairCost) {
        this.repairCost = repairCost;
    }

    public String getRepairBillUrl() {
        return repairBillUrl;
    }

    public void setRepairBillUrl(String repairBillUrl) {
        this.repairBillUrl = repairBillUrl;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "UpdateRepairForm{" +
                "pkDormRepairId='" + pkDormRepairId + '\'' +
                ", status=" + status +
                ", repairCost=" + repairCost +
                ", repairBillUrl='" + repairBillUrl + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
