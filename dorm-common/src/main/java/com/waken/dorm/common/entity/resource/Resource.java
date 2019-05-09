package com.waken.dorm.common.entity.resource;

import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class Resource extends BaseEntity {
    private static final long serialVersionUID = 3127442015766327511L;
    private String pkResourceId;

    private String resourceName;

    private Integer resourceType;

    private String resourceUrl;

    private Integer resourceNo;

    private String parentId;

    private Integer status;

    private Integer isParent;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;
}