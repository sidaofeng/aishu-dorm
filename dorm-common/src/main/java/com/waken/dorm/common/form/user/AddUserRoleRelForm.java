package com.waken.dorm.common.form.user;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @ClassName AddUserRoleRelForm
 * @Description 新增用户与角色关联的form 表单
 * @Author zhaoRong
 * @Date 2019/3/25 18:16
 **/
@ApiModel(value = "AddUserRoleRelForm", description = "新增用户与角色关联的form 表单")
@ToString
@Getter
@Setter
public class AddUserRoleRelForm {
    private String userId;

    private List<String> roleIds;
}
