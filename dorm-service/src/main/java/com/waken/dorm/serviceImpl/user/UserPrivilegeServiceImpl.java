package com.waken.dorm.serviceImpl.user;

import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.view.base.Tree;
import com.waken.dorm.common.view.resource.UserMenuView;
import com.waken.dorm.dao.user.UserPrivilegeMapper;
import com.waken.dorm.service.user.UserPrivilegeService;
import com.waken.dorm.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/8/8 22:21
 **/
@Service
public class UserPrivilegeServiceImpl implements UserPrivilegeService {
    @Autowired
    UserPrivilegeMapper privilegeMapper;
    @Autowired
    UserService userService;

    /**
     * 获取用户的角色信息
     *
     * @param userName
     * @return
     */
    @Override
    public Set<String> getUserRoles(String userName) {
        User user = userService.queryUserInfo(userName);
        List<String> roles = privilegeMapper.selectUserRoles(user.getUserId());
        if (null != roles && !roles.isEmpty()) {
            return new HashSet<>(roles);
        } else {
            return new HashSet<>();
        }
    }

    /**
     * 获取用户的操作权限
     *
     * @param userName
     * @return
     */
    @Override
    public Set<String> getUserPrivileges(String userName) {
        User user = userService.queryUserInfo(userName);
        //获取用户所有的按钮资源
        List<String> perms = this.privilegeMapper.selectUserPerms(user.getUserId());
        if (null != perms && !perms.isEmpty()) {
            return new HashSet<>(perms);
        } else {
            return new HashSet<>();
        }
    }

    /**
     * 获得当前登录用户的菜单树
     *
     * @param userId
     * @return
     */
    @Override
    public List<Tree<UserMenuView>> getUserMenu(String userId) {
        //获取用户所有的菜单资源
        List<UserMenuView> var3 = privilegeMapper.selectUserMenu(userId);

        //将list对象转化为菜单树形视图对象
        List<Tree<UserMenuView>> var5 = this.toUserMenuTree(var3);

        //菜单树根节点
        List<Tree<UserMenuView>> rootList = new ArrayList<>();

        Iterator<Tree<UserMenuView>> var6 = var5.iterator();
        while (var6.hasNext()) {
            Tree<UserMenuView> menuTree = var6.next();
            if (StringUtils.equals(menuTree.getAttribute().getParentId(), Constant.ROOT)) {
                rootList.add(menuTree);
            }
        }
        rootList.stream().forEach(root -> this.getChildren(root, var5));
        return rootList;
    }

    /**
     * 将菜单视图列表转为树形对象
     *
     * @param userMenuViewList
     * @return
     */
    private List<Tree<UserMenuView>> toUserMenuTree(List<UserMenuView> userMenuViewList) {
        List<Tree<UserMenuView>> menuTree = new ArrayList<>();
        Iterator<UserMenuView> var3 = userMenuViewList.iterator();
        Tree<UserMenuView> tree;
        UserMenuView menuView;
        while (var3.hasNext()) {
            tree = new Tree<>();
            menuView = var3.next();
            tree.setId(menuView.getPkResourceId());
            tree.setKey(menuView.getPkResourceId());
            tree.setTitle(menuView.getResourceName());
            tree.setIcon(menuView.getResourceIcon());
            tree.setSort(menuView.getSort());
            tree.setLeaf(!menuView.isParent());
            tree.setAttribute(menuView);
            menuTree.add(tree);
        }
        return menuTree;
    }

    /**
     * 找到菜单子节点并设置进去
     *
     * @param root
     * @param menuTreeList
     */
    private void getChildren(Tree<UserMenuView> root, List<Tree<UserMenuView>> menuTreeList) {
        Iterator<Tree<UserMenuView>> var3 = menuTreeList.iterator();
        Tree<UserMenuView> tree;
        List<Tree<UserMenuView>> childrenTree = new ArrayList<>();
        while (var3.hasNext()) {
            tree = var3.next();
            if (StringUtils.equals(root.getId(), tree.getAttribute().getParentId())) {
                childrenTree.add(tree);
                root.setChildren(childrenTree);
                var3.remove();
            }
        }
        if (!childrenTree.isEmpty()) {
            childrenTree.stream().forEach(childTree -> this.getChildren(childTree, menuTreeList));
        }
    }
}
