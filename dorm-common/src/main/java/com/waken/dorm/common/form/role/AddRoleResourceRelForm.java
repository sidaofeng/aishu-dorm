package com.waken.dorm.common.form.role;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName AddRoleResourceRelForm
 * @Description 新增角色资源关联的form 表单
 * @Author zhaoRong
 * @Date 2019/3/25 13:57
 **/
@ApiModel(value = "AddRoleResourceRelForm", description = "新增角色资源关联的form 表单")
public class AddRoleResourceRelForm {
    private String pkRoleId; //需要绑定的角色id

    private List<String> pkResourceIds; //需要绑定的资源 id 集合

    public String getPkRoleId() {
        return pkRoleId;
    }

    public void setPkRoleId(String pkRoleId) {
        this.pkRoleId = pkRoleId;
    }

    public List<String> getPkResourceIds() {
        return pkResourceIds;
    }

    public void setPkResourceIds(List<String> pkResourceIds) {
        this.pkResourceIds = pkResourceIds;
    }

    @Override
    public String toString() {
        return "AddRoleResourceRelForm{" +
                "pkRoleId='" + pkRoleId + '\'' +
                ", pkResourceIds=" + pkResourceIds +
                '}';
    }
}
