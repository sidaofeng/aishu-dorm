package com.waken.dorm.common.entity.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 资源信息表
 * </p>
 *
 * @author zhaoRong
 * @since 2019-08-08
 */
@Getter
@Setter
@ToString
@TableName("rm_resource")
public class Resource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父级ID
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 路由名称
     */
    @TableField("route_name")
    private String routeName;

    /**
     * 路由组件
     */
    @TableField("component")
    private String component;

    /**
     * 权限
     */
    @TableField("perms")
    private String perms;

    /**
     * 图标
     */
    @TableField("resource_icon")
    private String resourceIcon;

    /**
     * 资源类型（1-菜单，2-按钮）
     */
    @TableField("resource_type")
    private Integer resourceType;

    /**
     * 资源URL
     */
    @TableField("resource_url")
    private String resourceUrl;

    /**
     * 资源序号
     */
    private Integer sort;

    /**
     * 状态(-1，已删除，1-启用，2-停用,3-作废)
     */
    private Integer status;

    /**
     * 是否父节点-(0-是，1-否)
     */
    @TableField("is_parent")
    private Boolean isParent;

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean isParent) {
        this.isParent = isParent;
    }
}
