package com.waken.dorm.common.form.base;

import com.waken.dorm.common.enums.CodeEnum;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName DeleteForm
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/3/25 13:30
 **/
@ApiModel(value = "DeleteForm", description = "共用的删除表单")
public class DeleteForm {
    private List<String> delIds; //需要删除的id集合

    private Integer delStatus = CodeEnum.YES.getCode(); //删除操作的状态 1 状态删除 0物理删除

    public List<String> getDelIds() {
        return delIds;
    }

    public void setDelIds(List<String> delIds) {
        this.delIds = delIds;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    @Override
    public String toString() {
        return "DeleteForm{" +
                "delIds=" + delIds +
                ", delStatus=" + delStatus +
                '}';
    }
}
