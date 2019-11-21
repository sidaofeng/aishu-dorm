package com.waken.dorm.common.entity.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.annotation.Id;
import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 * 用户与角色、资源关联信息
 * </p>
 *
 * @author zhaoRong
 * @since 2019-08-08
 */
@Getter
@Setter
@ToString
@TableName("rm_user_role_rel")
public class UserRoleRel extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @TableId("id")
    private String id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 资源id或者角色id
     */
    @TableField("role_id")
    private String roleId;


    @TableField("create_time")
    private Date createTime;

    @TableField("create_user_id")
    private String createUserId;
}
