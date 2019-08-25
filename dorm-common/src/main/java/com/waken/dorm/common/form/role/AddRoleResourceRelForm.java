package com.waken.dorm.common.form.role;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @ClassName AddRoleResourceRelForm
 * @Description 新增角色资源关联的form 表单
 * @Author zhaoRong
 * @Date 2019/3/25 13:57
 **/
@ApiModel(value = "AddRoleResourceRelForm", description = "新增角色资源关联的form 表单")
@ToString
@Getter
@Setter
public class AddRoleResourceRelForm {
    private String roleId; //需要绑定的角色id

    private List<String> resourceIds; //需要绑定的资源 id 集合
}
