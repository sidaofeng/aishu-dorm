package com.waken.dorm.common.view.role;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @description:
 * @author: aishu
 * @create: 2019-10-23 10:45
 */
@Getter
@Setter
public class RoleView {
    private String pkRoleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 角色状态(-1已删除，0失效，1-生效)
     */
    private Integer status;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;
}