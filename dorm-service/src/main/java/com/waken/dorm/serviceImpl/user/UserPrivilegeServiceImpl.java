package com.waken.dorm.serviceImpl.user;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.resource.Resource;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.entity.user.UserPrivilege;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.role.UserRoleRelForm;
import com.waken.dorm.common.form.user.AddUserResourcesForm;
import com.waken.dorm.common.form.user.AddUserRoleRelForm;
import com.waken.dorm.common.utils.Assert;
import com.waken.dorm.common.utils.DateUtils;
import com.waken.dorm.common.sequence.UUIDSequence;
import com.waken.dorm.common.utils.TreeUtil;
import com.waken.dorm.common.view.base.Tree;
import com.waken.dorm.common.view.resource.UserMenuView;
import com.waken.dorm.dao.resource.ResourceMapper;
import com.waken.dorm.dao.user.UserMapper;
import com.waken.dorm.dao.user.UserPrivilegeMapper;
import com.waken.dorm.manager.UserManager;
import com.waken.dorm.service.user.UserPrivilegeService;
import com.waken.dorm.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
public class UserPrivilegeServiceImpl implements UserPrivilegeService {
    @Autowired
    UserPrivilegeMapper privilegeMapper;
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ResourceMapper resourceMapper;
    @Autowired
    TreeUtil treeUtil;

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

        return treeUtil.toTree(var5,Constant.ROOT);
    }

    /**
     * 添加单个用户与角色关联
     *
     * @param userRoleRelForm
     */
    @Transactional
    @Override
    public void addUserRoleRel(UserRoleRelForm userRoleRelForm) {
        String userId = userRoleRelForm.getUserId();
        String roleId = userRoleRelForm.getRoleId();
        Assert.notNull(userId);
        Assert.notNull(roleId);
        List<UserPrivilege> privileges = privilegeMapper.selectList(new EntityWrapper<UserPrivilege>()
                .eq("user_id", userId)
                .eq("subject_type", CodeEnum.ROLE.getCode())
        );
        if (null != privileges && !privileges.isEmpty()) {
            privilegeMapper.deleteById(privileges.get(0).getPkPrivilegeId());
        }
        UserPrivilege privilege = new UserPrivilege();
        String pkId = UUIDSequence.next();
        Date curTime = DateUtils.getCurrentDate();
        privilege.setPkPrivilegeId(pkId);
        privilege.setUserId(userId);
        privilege.setSubjectId(roleId);
        privilege.setSubjectType(CodeEnum.ROLE.getCode());
        privilege.setCreateTime(curTime);
        privilege.setCreateUserId(UserManager.getCurrentUserId());
        int count = privilegeMapper.insert(privilege);
        Assert.isTrue(count == Constant.ZERO);
    }

    @Transactional // 事务控制
    @Override
    public void batchAddUserRoleRel(AddUserRoleRelForm addForm) {
        log.info("service: 批量新增用户角色关联开始");
        Assert.notNull(addForm.getUserId());
        if (addForm.getRoleIds().isEmpty()) {
            throw new ServerException("参数为空！");
        }
        List<UserPrivilege> toBeAddUserRoleRel = this.getToBeAddUserRoleRel(addForm);
        if (!toBeAddUserRoleRel.isEmpty()) {
            int count = privilegeMapper.batchAddUserRoleRel(toBeAddUserRoleRel);
            Assert.isTrue(count == Constant.ZERO);

        }
    }

    private List<UserPrivilege> getToBeAddUserRoleRel(AddUserRoleRelForm addUserRoleRelForm) {
        String userId = addUserRoleRelForm.getUserId();
        this.checkSuperAdmin(userId);
        List<String> roleIds = addUserRoleRelForm.getRoleIds();
        List<UserPrivilege> userRoleRelList = privilegeMapper.selectList(new EntityWrapper<UserPrivilege>()
                .eq("user_id", userId)
                .eq("subject_type", CodeEnum.ROLE.getCode())
        );
        if (!userRoleRelList.isEmpty()) {
            List<String> existIds = Lists.newArrayList();// 接收已经存在关联的角色id
            List<String> toDelPkIds = Lists.newArrayList();//接收需要删除的关联主键id
            for (UserPrivilege userRoleRel : userRoleRelList) {
                if (roleIds.contains(userRoleRel.getSubjectId())) {
                    existIds.add(userRoleRel.getSubjectId());
                } else {
                    toDelPkIds.add(userRoleRel.getPkPrivilegeId());
                }
            }
            roleIds.removeAll(existIds);
            if (!toDelPkIds.isEmpty()) {
                int count = privilegeMapper.deleteBatchIds(toDelPkIds);
                Assert.isTrue(count == Constant.ZERO);
            }

        }
        if (!roleIds.isEmpty()) {
            List<UserPrivilege> toBeAddUserRoleRelList = new ArrayList<>();
            for (String roleId : roleIds) {
                String pkId = UUIDSequence.next();
                String curUserId = UserManager.getCurrentUserId();
                Date curDate = DateUtils.getCurrentDate();
                UserPrivilege userRoleRel = new UserPrivilege();
                userRoleRel.setPkPrivilegeId(pkId);
                userRoleRel.setUserId(userId);
                userRoleRel.setSubjectId(roleId);
                userRoleRel.setSubjectType(CodeEnum.ROLE.getCode());
                userRoleRel.setCreateTime(curDate);
                userRoleRel.setCreateUserId(curUserId);
                toBeAddUserRoleRelList.add(userRoleRel);
            }
            return toBeAddUserRoleRelList;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 批量新增用户与资源的关联
     *
     * @param addForm
     */
    @Override
    public void batchAddUserResourceRel(AddUserResourcesForm addForm) {
        log.info("service: 批量新增用户资源关联开始");
        Assert.notNull(addForm.getUserId());
        if (null == addForm.getResourceIds() && addForm.getResourceIds().isEmpty()) {
            throw new ServerException("参数为空！");
        }
        List<UserPrivilege> toBeAddUserRoleRel = this.getToBeAddUserResources(addForm);
        if (!toBeAddUserRoleRel.isEmpty()) {
            int count = this.privilegeMapper.batchAddUserResources(toBeAddUserRoleRel);
            Assert.isTrue(count == Constant.ZERO);
        }
    }

    private List<UserPrivilege> getToBeAddUserResources(AddUserResourcesForm addUserRoleRelForm) {
        String userId = addUserRoleRelForm.getUserId();
        this.checkSuperAdmin(userId);
        List<String> resourceIds = addUserRoleRelForm.getResourceIds();
        List<UserPrivilege> userRoleRelList = privilegeMapper.selectList(new EntityWrapper<UserPrivilege>()
                .eq("user_id", userId)
                .andNew()
                .eq("subject_type", CodeEnum.MENU.getCode())
                .or()
                .eq("subject_type", CodeEnum.BUTTON.getCode())
        );
        if (!userRoleRelList.isEmpty()) {
            List<String> existIds = new ArrayList<>();// 接收已经存在关联的资源id
            List<String> toDelPkIds = new ArrayList<>();// 接收需要删除的关联主键id
            for (UserPrivilege userRoleRel : userRoleRelList) {
                if (resourceIds.contains(userRoleRel.getSubjectId())) {
                    existIds.add(userRoleRel.getSubjectId());
                } else {
                    toDelPkIds.add(userRoleRel.getPkPrivilegeId());
                }
            }
            resourceIds.removeAll(existIds);
            if (!toDelPkIds.isEmpty()) {
                int count = privilegeMapper.deleteBatchIds(toDelPkIds);
                Assert.isTrue(count == Constant.ZERO);
            }

        }
        List<Resource> resourceList = resourceMapper.selectBatchIds(resourceIds);
        Map<String, Integer> typeMap = resourceList.stream().collect(Collectors.toMap(Resource::getPkResourceId, Resource::getResourceType));
        if (!resourceIds.isEmpty()) {
            List<UserPrivilege> toBeAddUserRoleRelList = new ArrayList<>();
            for (String resourceId : resourceIds) {
                String pkId = UUIDSequence.next();
                String curUserId = UserManager.getCurrentUserId();
                Date curDate = DateUtils.getCurrentDate();
                UserPrivilege userRoleRel = new UserPrivilege();
                userRoleRel.setPkPrivilegeId(pkId);
                userRoleRel.setUserId(userId);
                userRoleRel.setSubjectId(resourceId);
                userRoleRel.setSubjectType(typeMap.get(resourceId));
                userRoleRel.setCreateTime(curDate);
                userRoleRel.setCreateUserId(curUserId);
                toBeAddUserRoleRelList.add(userRoleRel);
            }
            return toBeAddUserRoleRelList;
        } else {
            return new ArrayList<>();
        }
    }

    private void checkSuperAdmin(String userId){
        User user = userMapper.selectById(userId);
        Assert.notNull(user);
        List<String> roles = privilegeMapper.selectUserRoles(user.getUserId());
        Assert.isTrue(roles.contains(Constant.SuperAdmin),"不能操作超级管理员!");

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
