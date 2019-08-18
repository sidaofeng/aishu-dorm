package com.waken.dorm.common.entity.role;

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
 *
 * </p>
 *
 * @author zhaoRong
 * @since 2019-08-05
 */
@Getter
@Setter
@ToString
@TableName("rm_role_resource_rel")
public class RoleResourceRel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("pk_role_resource_id")
    private String pkRoleResourceId;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private String roleId;

    /**
     * 资源ID
     */
    @TableField("resource_id")
    private String resourceId;

    /**
     * 1菜单，2按钮
     */
    @TableField("resource_type")
    private Integer resourceType;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_user_id")
    private String createUserId;
}
