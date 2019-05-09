package com.waken.dorm.common.view.resource;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ResourceMenuView
 * @Description 资源菜单视图
 * @Author zhaoRong
 * @Date 2019/4/5 22:03
 **/
@ApiModel(value = "ResourceMenuView", description = "资源菜单视图")
@Getter
@Setter
public class ResourceMenuView {
    private String pkResourceId;

    private String resourceName;

    private Integer resourceType;

    private String resourceUrl;

    private Integer resourceNo;

    private String parentId;

    private Integer isParent;

    private boolean open;

    private boolean zAsync;

    private boolean isSelected;
}
