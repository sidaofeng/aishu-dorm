package com.waken.dorm.service.system;

import com.waken.dorm.common.entity.auth.User;
import com.waken.dorm.common.form.role.UserRoleRelForm;
import com.waken.dorm.common.form.user.AddUserRoleRelForm;
import com.waken.dorm.common.view.base.Tree;
import com.waken.dorm.common.view.resource.UserMenuView;

import java.util.List;
import java.util.Map;
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
     * @param userId
     * @return
     */
    Set<String> getUserRoles(String userId);

    /**
     * 获取用户的操作权限
     *
     * @return
     */
    Set<String> getUserPrivileges(String userId);

    /**
     * 获得当前登录用户的菜单树
     *
     * @return
     */
    List<Tree<UserMenuView>> getUserMenu(String userId);

    /**
     * 单个新增用户与角色关联
     *
     * @param userRoleRelForm
     */
    int addUserRoleRel(UserRoleRelForm userRoleRelForm);

    /**
     * 批量新增用户与角色的关联
     *
     * @param addUserRoleRelForm
     */
    int batchAddUserRoleRel(AddUserRoleRelForm addUserRoleRelForm);

    /**
     * 查询用户信息
     *
     * @param userName user name
     */
    User queryUserInfo(String userName);

    /**
     * 获取权限信息
     *
     * @return
     */
    Map<String, String> getPermsMap();

    /**
     * 获取角色信息
     *
     * @return
     */
    Map<String, String> getRoleMap();

}
