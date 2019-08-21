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
import com.waken.dorm.common.utils.DateUtils;
import com.waken.dorm.common.utils.UUIDUtils;
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
     * 添加单个用户与角色关联
     *
     * @param userRoleRelForm
     */
    @Transactional
    @Override
    public void addUserRoleRel(UserRoleRelForm userRoleRelForm) {
        String userId = userRoleRelForm.getUserId();
        String roleId = userRoleRelForm.getRoleId();
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(roleId)) {
            throw new ServerException("入参为空！");
        }
        List<UserPrivilege> privileges = privilegeMapper.selectList(new EntityWrapper<UserPrivilege>()
                .eq("user_id", userId)
                .eq("subject_type", CodeEnum.ROLE.getCode())
        );
        if (null != privileges && !privileges.isEmpty()) {
            privilegeMapper.deleteById(privileges.get(0).getPkPrivilegeId());
        }
        UserPrivilege privilege = new UserPrivilege();
        String pkId = UUIDUtils.getPkUUID();
        Date curTime = DateUtils.getCurrentDate();
        privilege.setPkPrivilegeId(pkId);
        privilege.setUserId(userId);
        privilege.setSubjectId(roleId);
        privilege.setSubjectType(CodeEnum.ROLE.getCode());
        privilege.setCreateTime(curTime);
        privilege.setCreateUserId(UserManager.getCurrentUserId());
        int count = privilegeMapper.insert(privilege);
        if (count == Constant.ZERO) {
            throw new ServerException("单个新增用户与角色关联失败！");
        }
    }

    @Transactional // 事务控制
    @Override
    public void batchAddUserRoleRel(AddUserRoleRelForm addUserRoleRelForm) {
        log.info("service: 批量新增用户角色关联开始");
        StringBuffer sb = new StringBuffer();
        if (com.waken.dorm.common.utils.StringUtils.isEmpty(addUserRoleRelForm.getUserId())) {
            sb.append("用户 id为空！");
        }
        if (addUserRoleRelForm.getRoleIds().isEmpty()) {
            sb.append("角色 id集合为空！");
        }
        if (!StringUtils.isEmpty(sb.toString())) {
            throw new ServerException("批量新增用户角色关联失败，原因：" + sb.toString());
        }
        List<UserPrivilege> toBeAddUserRoleRel = this.getToBeAddUserRoleRel(addUserRoleRelForm);
        if (!toBeAddUserRoleRel.isEmpty()) {
            int count = privilegeMapper.batchAddUserRoleRel(toBeAddUserRoleRel);
            if (count == Constant.ZERO) {
                throw new ServerException("批量新增用户角色关联失败");
            }

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
                if (count == Constant.ZERO) {
                    throw new ServerException("删除用户角色关联失败");
                }
            }

        }
        if (!roleIds.isEmpty()) {
            List<UserPrivilege> toBeAddUserRoleRelList = new ArrayList<>();
            for (String roleId : roleIds) {
                String pkId = UUIDUtils.getPkUUID();
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
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isEmpty(addForm.getUserId())) {
            sb.append("用户 id为空！");
        }
        if (null == addForm.getResourceIds() && addForm.getResourceIds().isEmpty()) {
            sb.append("资源 id集合为空！");
        }
        if (!StringUtils.isEmpty(sb.toString())) {
            throw new ServerException("批量新增用户角色关联失败，原因：" + sb.toString());
        }
        List<UserPrivilege> toBeAddUserRoleRel = this.getToBeAddUserResources(addForm);
        if (!toBeAddUserRoleRel.isEmpty()) {
            int count = this.privilegeMapper.batchAddUserResources(toBeAddUserRoleRel);
            if (count == Constant.ZERO) {
                throw new ServerException("批量新增用户角色关联失败");
            }

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
                if (count == Constant.ZERO) {
                    throw new ServerException("删除用户角色关联失败");
                }
            }

        }
        List<Resource> resourceList = resourceMapper.selectBatchIds(resourceIds);
        Map<String, Integer> typeMap = resourceList.stream().collect(Collectors.toMap(Resource::getPkResourceId, Resource::getResourceType));
        if (!resourceIds.isEmpty()) {
            List<UserPrivilege> toBeAddUserRoleRelList = new ArrayList<>();
            for (String resourceId : resourceIds) {
                String pkId = UUIDUtils.getPkUUID();
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
        if (null == user){
            throw new ServerException("参数错误！");
        }
        List<String> roles = privilegeMapper.selectUserRoles(user.getUserId());
        if (roles.contains(Constant.SuperAdmin)){
            throw new ServerException("不能操作超级管理员!");
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
