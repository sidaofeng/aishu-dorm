package com.waken.dorm.serviceImpl.auth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.entity.user.UserPrivilege;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.form.role.UserRoleRelForm;
import com.waken.dorm.common.form.user.AddUserRoleRelForm;
import com.waken.dorm.common.utils.Assert;
import com.waken.dorm.common.utils.TreeUtil;
import com.waken.dorm.common.view.base.Tree;
import com.waken.dorm.common.view.resource.UserMenuView;
import com.waken.dorm.dao.auth.ResourceMapper;
import com.waken.dorm.dao.auth.RoleResourceRelMapper;
import com.waken.dorm.dao.auth.UserMapper;
import com.waken.dorm.dao.auth.UserPrivilegeMapper;
import com.waken.dorm.handle.DataHandle;
import com.waken.dorm.service.auth.UserPrivilegeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author zhaoRong
 * @Date 2019/8/8 22:21
 **/
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserPrivilegeServiceImpl implements UserPrivilegeService {
    private final UserPrivilegeMapper privilegeMapper;
    private final UserMapper userMapper;
    private final ResourceMapper resourceMapper;
    private final TreeUtil treeUtil;
    private final RoleResourceRelMapper roleResourceRelMapper;

    /**
     * 获取用户的角色信息
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> getUserRoles(String userId) {
        List<String> roles = privilegeMapper.selectUserRoles(userId);
        if (null != roles && !roles.isEmpty()) {
            return new HashSet<>(roles);
        } else {
            return new HashSet<>();
        }
    }

    /**
     * 获取用户的操作权限
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> getUserPrivileges(String userId) {
        //获取用户所有的按钮资源
        List<String> perms = this.privilegeMapper.selectUserPerms(userId);
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
        List<UserMenuView> var3 = privilegeMapper.selectUserResources(userId,1);

        //将list对象转化为菜单树形视图对象
        List<Tree<UserMenuView>> var5 = this.toUserMenuTree(var3);

        return treeUtil.toTree(var5, Constant.ROOT);
    }

    /**
     * 添加单个用户与角色关联
     *
     * @param userRoleRelForm
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addUserRoleRel(UserRoleRelForm userRoleRelForm) {
        String userId = userRoleRelForm.getUserId();
        String roleId = userRoleRelForm.getRoleId();
        Assert.notNull(userId);
        Assert.notNull(roleId);
        UserPrivilege userPrivilege = privilegeMapper.selectOne(new LambdaQueryWrapper<UserPrivilege>()
                .eq(UserPrivilege::getUserId, userId)
                .eq(UserPrivilege::getSubjectType, CodeEnum.ROLE.getCode())
        );
        if (null != userPrivilege) {
            privilegeMapper.deleteById(userPrivilege.getPkPrivilegeId());
        }
        UserPrivilege privilege = new UserPrivilege();
        privilege.setUserId(userId);
        privilege.setSubjectId(roleId);
        privilege.setSubjectType(CodeEnum.ROLE.getCode());
        int count = privilegeMapper.insert(privilege);
        Assert.isFalse(count == Constant.ZERO);
        return count;
    }

    /**
     * 批量新增用户与角色的关联
     *
     * @param addForm
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchAddUserRoleRel(AddUserRoleRelForm addForm) {
        log.info("service: 批量新增用户角色关联开始");
        Assert.notNull(addForm.getUserId());
        List<UserPrivilege> toBeAddUserRoleRel = this.getToBeAddUserRoleRel(addForm);
        int count = 0;
        if (toBeAddUserRoleRel != null && !toBeAddUserRoleRel.isEmpty()) {
            count = privilegeMapper.batchAddUserRoleRel(toBeAddUserRoleRel);
            Assert.isFalse(count == Constant.ZERO);
        }
        return count;
    }

    /**
     * 查询用户信息
     *
     * @param userName user name
     */
    @Override
    public User queryUserInfo(String userName) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserName,userName)
        );
    }

    private List<UserPrivilege> getToBeAddUserRoleRel(AddUserRoleRelForm addUserRoleRelForm) {
        String userId = addUserRoleRelForm.getUserId();
        List<String> roleIds = addUserRoleRelForm.getRoleIds();
        List<UserPrivilege> userRoleRelList = privilegeMapper.selectList(new LambdaQueryWrapper<UserPrivilege>()
                .eq(UserPrivilege::getUserId, userId)
                .eq(UserPrivilege::getSubjectType, CodeEnum.ROLE.getCode())
        );
        this.handleResult(roleIds, userRoleRelList);

        if (roleIds == null || roleIds.isEmpty()) {
            return null;
        }
        List<UserPrivilege> toBeAddUserRoleRelList = Lists.newArrayList();
        roleIds.stream().forEach(roleId ->{
            UserPrivilege userRoleRel = new UserPrivilege();
            userRoleRel.setUserId(userId);
            userRoleRel.setSubjectId(roleId);
            userRoleRel.setSubjectType(CodeEnum.ROLE.getCode());
            toBeAddUserRoleRelList.add(userRoleRel);
        });
        return toBeAddUserRoleRelList;
    }

    /**
     * 将需要绑定的目标参数做处理
     *
     * @param targetIds
     * @param userPrivilegeList
     */
    private void handleResult(List<String> targetIds, List<UserPrivilege> userPrivilegeList) {
        if (userPrivilegeList != null && !userPrivilegeList.isEmpty()) {
            Map<String, String> pkIdAndTargetIdMap = userPrivilegeList.stream()
                    .collect(Collectors.toMap(UserPrivilege::getPkPrivilegeId, UserPrivilege::getSubjectId));
            // 接收需要删除的关联主键id
            List<String> toDelPkIds = DataHandle.handleToDelAndTargetIds(targetIds, pkIdAndTargetIdMap);

            if (toDelPkIds != null && !toDelPkIds.isEmpty()) {
                int count = privilegeMapper.deleteBatchIds(toDelPkIds);
                Assert.isFalse(count == Constant.ZERO);
            }
        }
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
            tree.setParentId(menuView.getParentId());
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
}
