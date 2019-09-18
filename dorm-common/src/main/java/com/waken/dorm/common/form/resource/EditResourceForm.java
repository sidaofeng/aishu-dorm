package com.waken.dorm.common.form.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName EditResourceForm
 * @Description EditResourceForm
 * @Author zhaoRong
 * @Date 2019/4/4 14:08
 **/
@ToString
@Getter
@Setter
public class EditResourceForm {
    private String pkResourceId;

    private String resourceName;

    private String routeName;

    private String component;

    private String resourceIcon;

    private Integer resourceType;

    private String resourceUrl;

    private String parentId;

    private String perms;
}
