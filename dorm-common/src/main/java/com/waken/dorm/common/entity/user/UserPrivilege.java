package com.waken.dorm.common.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.annotation.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
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
@TableName("rm_user_privilege")
public class UserPrivilege implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @TableId("pk_privilege_id")
    private String pkPrivilegeId;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 资源id或者角色id
     */
    @TableField("subject_id")
    private String subjectId;

    /**
     * 类型（1菜单，2按钮，3角色）
     */
    @TableField("subject_type")
    private Integer subjectType;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_user_id")
    private String createUserId;
}
