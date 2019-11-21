package com.waken.dorm.common.base;

import com.waken.dorm.common.entity.auth.Role;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @Description Privilege
 * @Author zhaoRong
 * @Date 2019/8/12 21:44
 **/
@Data
public class Workspace {
    /**
     * 当前登录用户的信息
     */
    private ShiroAccount shiroAccount;
    /**
     * 当前登录拥有的权限信息
     */
    private Set<String> privileges;
    /**
     * 当前登录拥有的角色信息
     */
    private List<Role> roles;
}
