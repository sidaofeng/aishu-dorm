package com.waken.dorm.common.form.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName ResourceMenuForm
 * @Description ResourceMenuForm
 * @Author zhaoRong
 * @Date 2019/4/5 22:04
 **/
@ToString
@Getter
@Setter
public class ResourceMenuForm {
    private String pkResourceId;

    private String userId;

    private String parentId;

    private String roleId;

    private Integer resourceType;

    private Integer status;
}
