package com.waken.dorm.common.form.role;

import com.waken.dorm.common.form.base.BaseForm;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName QueryRoleForm
 * @Description 查询角色信息的form 表单
 * @Author zhaoRong
 * @Date 2019/3/25 13:49
 **/
@ApiModel(value = "QueryRoleForm", description = "查询角色信息的form 表单")
@ToString
@Getter
@Setter
public class QueryRoleForm extends BaseForm {
    private static final long serialVersionUID = -3442287827988689012L;
    private String pkRoleId;

    private String roleName;

    private String roleDesc;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;
}
