package com.waken.dorm.common.entity.role;

import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class Role extends BaseEntity {
    private static final long serialVersionUID = 8559688006409759963L;
    private String pkRoleId;

    private String roleName;

    private String roleDesc;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;
}