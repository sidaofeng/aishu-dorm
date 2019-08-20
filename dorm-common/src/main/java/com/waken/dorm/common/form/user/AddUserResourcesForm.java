package com.waken.dorm.common.form.user;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @description:
 * @author: aishu
 * @create: 2019-08-20 18:46
 */
@ApiModel(value = "AddUserResourcesForm", description = "新增用户与资源关联的form 表单")
@ToString
@Getter
@Setter
public class AddUserResourcesForm {
    private String userId;

    private List<String> resourceIds;
}