package com.waken.dorm.common.view.resource;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName ResourceView
 * @Description ResourceView
 * @Author zhaoRong
 * @Date 2019/4/6 22:27
 **/
@Getter
@Setter
public class ResourceView implements Serializable {
    private static final long serialVersionUID = 763199863740443440L;

    /**
     * 资源ID
     */
    private String id;

    /**
     * 父节点ID
     */
    private String parentId;

    /**
     * 父节点名称
     */
    private String parentName;

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
     * 状态
     */
    private Integer status;

    /**
     * 是否父级节点
     */
    private boolean isParent;

}
