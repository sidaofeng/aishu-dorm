package com.waken.dorm.common.entity.user;

import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class UserRoleRel extends BaseEntity {
    private static final long serialVersionUID = 6615582806728462790L;
    private String pkUserRoleId;

    private String userId;

    private String roleId;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;
}