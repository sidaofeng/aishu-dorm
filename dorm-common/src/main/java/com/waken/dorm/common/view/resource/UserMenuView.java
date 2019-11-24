package com.waken.dorm.common.view.resource;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description 菜单树形视图
 * @Author zhaoRong
 * @Date 2019/8/8 21:29
 **/
@Getter
@Setter
@ApiModel(value = "UserMenuView", description = "用户菜单视图")
public class UserMenuView {
    /**
     * 资源ID
     */
    private String id;

    /**
     * 父节点ID
     */
    private String parentId;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 路由名称
     */
    private String routeName;

    /**
     * 路由组件
     */
    private String component;

    /**
     * 资源图标
     */
    private String resourceIcon;

    /**
     * 资源类型（1-菜单，2-按钮）
     */
    private Integer resourceType;

    /**
     * 资源URL
     */
    private String resourceUrl;

    /**
     * 排序序号
     */
    private Integer sort;

    /**
     * 是否父级节点
     */
    private boolean isParent;

}
