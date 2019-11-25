package com.waken.dorm.common.entity.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.annotation.Id;
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
    @Id
    private String id;

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

    @TableField("create_time")
    private Date createTime;

    @TableField("create_user_id")
    private String createUserId;
}
