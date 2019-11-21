package com.waken.dorm.common.form.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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

    private String resourceUrl;

    private String parentId;

    private List<ButtonResources> buttonResourcesList = new ArrayList<>();
}
