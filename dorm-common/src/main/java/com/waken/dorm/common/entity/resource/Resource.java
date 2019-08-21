package com.waken.dorm.common.entity.resource;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

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
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资源ID
     */
    @TableId("pk_resource_id")
    private String pkResourceId;

    /**
     * 父级ID
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 资源名称
     */
    @TableField("resource_name")
    private String resourceName;

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

    @TableField("create_time")
    private Date createTime;

    @TableField("create_user_id")
    private String createUserId;

    @TableField("last_modify_time")
    private Date lastModifyTime;

    @TableField("last_modify_user_id")
    private String lastModifyUserId;

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean isParent) {
        this.isParent = isParent;
    }
}
