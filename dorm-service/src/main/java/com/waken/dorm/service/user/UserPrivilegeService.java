package com.waken.dorm.service.user;

import com.waken.dorm.common.view.base.Tree;
import com.waken.dorm.common.view.resource.UserMenuView;

import java.util.List;
import java.util.Set;

/**
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/8/8 22:18
 **/
public interface UserPrivilegeService {
    /**
     * 获取用户的角色信息
     *
     * @param userName
     * @return
     */
    Set<String> getUserRoles(String userName);

    /**
     * 获取用户的操作权限
     *
     * @return
     */
    Set<String> getUserPrivileges(String userName);

    /**
     * 获得当前登录用户的菜单树
     *
     * @return
     */
    List<Tree<UserMenuView>> getUserMenu(String userId);
}
