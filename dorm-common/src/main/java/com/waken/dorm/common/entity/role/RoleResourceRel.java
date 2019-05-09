package com.waken.dorm.common.entity.role;

import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class RoleResourceRel extends BaseEntity {
    private static final long serialVersionUID = 7127819495240564880L;
    private String pkRoleResourceId;

    private String roleId;

    private String resourceId;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;
}