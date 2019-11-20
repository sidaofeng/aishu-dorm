package com.waken.dorm.serviceImpl.auth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.role.Role;
import com.waken.dorm.common.entity.role.RoleResourceRel;
import com.waken.dorm.common.entity.user.UserPrivilege;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.role.AddRoleResourceRelForm;
import com.waken.dorm.common.form.role.EditRoleForm;
import com.waken.dorm.common.form.role.QueryRoleForm;
import com.waken.dorm.common.manager.UserManager;
import com.waken.dorm.common.utils.Assert;
import com.waken.dorm.common.utils.BeanMapper;
import com.waken.dorm.common.utils.DormUtil;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.view.role.RoleView;
import com.waken.dorm.common.view.role.UserRoleView;
import com.waken.dorm.dao.auth.RoleMapper;
import com.waken.dorm.dao.auth.RoleResourceRelMapper;
import com.waken.dorm.dao.auth.UserPrivilegeMapper;
import com.waken.dorm.handle.DataHandle;
import com.waken.dorm.service.auth.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName RoleServiceImpl
 * @Description 角色业务层
 * @Author zhaoRong
 * @Date 2019/3/25 14:04
 **/
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;
    private final RoleResourceRelMapper roleResourceRelMapper;
    private final UserPrivilegeMapper userPrivilegeMapper;

    @Transactional
    @Override
    public Role saveRole(EditRoleForm editRoleForm) {
        this.editRoleValidate(editRoleForm);
        Role role = new Role();
        BeanMapper.copy(editRoleForm, role);
        if (StringUtils.isEmpty(editRoleForm.getPkRoleId())) {//新增
            log.info("service: 新增角色开始");
            role.setStatus(CodeEnum.ENABLE.getCode());
            int count = roleMapper.insert(role);
            if (count == Constant.ZERO) {
                throw new ServerException("新增角色失败");
            }
            return role;
        } else {//修改
            log.info("service: 更新角色信息开始");
            roleMapper.updateById(role);
            return role;
        }

    }

    @Transactional // 事务控制
    @Override
    public void deleteRole(DeleteForm deleteForm) {
        log.info("service: 删除角色开始");
        List<String> roleIds = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count;
        if (CodeEnum.YES.getCode() == delStatus) { // 物理删除
            this.delRoleRel(roleIds);
            count = roleMapper.deleteBatchIds(roleIds);
            if (count == Constant.ZERO) {
                throw new ServerException("物理删除失败");
            }
        } else if (CodeEnum.NO.getCode() == delStatus) {
            this.delRoleRel(roleIds);
            Map updateStatusMap = DormUtil.getToUpdateStatusMap(roleIds, UserManager.getCurrentUserId());
            count = roleMapper.batchUpdateStatus(updateStatusMap);
            if (count == Constant.ZERO) {
                throw new ServerException("状态删除失败");
            }
        } else {
            throw new ServerException("删除状态码错误！");
        }
    }

    /**
     * 分页查询
     *
     * @param queryRoleForm
     * @return
     */
    @Override
    public IPage<RoleView> page(QueryRoleForm queryRoleForm) {
        log.info("service: 查询角色开始");

        return roleMapper.rolePage(DataHandle.getWrapperPage(queryRoleForm), queryRoleForm);
    }

    /**
     * 删除角色与资源、用户的关联
     *
     * @param roleIds
     */
    private void delRoleRel(List<String> roleIds) {
        List<Role> roles = roleMapper.selectBatchIds(roleIds);
        roles.stream().forEach(role -> {
            if (StringUtils.equals(role.getRoleName(), Constant.SuperAdmin)) {
                throw new ServerException("不能操作超级管理员角色！");
            }
        });
        //删除资源与用户的所有关联
        List<UserPrivilege> privileges = userPrivilegeMapper.selectList(new LambdaQueryWrapper<UserPrivilege>()
                .eq(UserPrivilege::getSubjectType, CodeEnum.ROLE.getCode())
        );
        List<String> toDelPrivilegeId = Lists.newArrayList();
        if (null != privileges && !privileges.isEmpty()) {
            privileges.stream().forEach(userPrivilege -> {
                if (roleIds.contains(userPrivilege.getSubjectId())) {
                    toDelPrivilegeId.add(userPrivilege.getPkPrivilegeId());
                }
            });
        }
        if (null != toDelPrivilegeId && !toDelPrivilegeId.isEmpty()) {
            userPrivilegeMapper.deleteBatchIds(toDelPrivilegeId);
        }
        //删除资源与角色的所有关联
        List<String> toDelRelId = Lists.newArrayList();
        List<RoleResourceRel> roleList = roleResourceRelMapper.selectList(null);
        if (null != roleList && !roleList.isEmpty()) {
            roleList.stream().forEach(rel -> {
                if (roleIds.contains(rel.getRoleId())) {
                    toDelRelId.add(rel.getPkRoleResourceId());
                }
            });
        }
        if (null != toDelRelId && !toDelRelId.isEmpty()) {
            roleResourceRelMapper.deleteBatchIds(toDelRelId);
        }
    }

    /**
     * 通过用户id获取角色信息 将已存在关联的角色标记为选中
     *
     * @param userId
     * @return
     */
    @Override
    public List<UserRoleView> listRolesByUser(String userId) {
        List<UserRoleView> userRoleViews = roleMapper.listUserRole();
        List<UserPrivilege> privileges = userPrivilegeMapper.selectList(new LambdaQueryWrapper<UserPrivilege>()
                .eq(UserPrivilege::getUserId, userId)
                .eq(UserPrivilege::getSubjectType, CodeEnum.ROLE.getCode())
        );
        Map roleMap = privileges.stream().collect(Collectors.toMap(UserPrivilege::getSubjectId, UserPrivilege::getUserId));
        if (null != userRoleViews && !userRoleViews.isEmpty()) {
            userRoleViews.stream().forEach(userRoleView -> {
                if (roleMap.containsKey(userRoleView.getRoleId())) {
                    userRoleView.setSelect(true);
                }
            });
        }
        return userRoleViews;
    }

    @Transactional// 事务控制
    @Override
    public void batchAddRoleResourceRel(AddRoleResourceRelForm addForm) {
        log.info("service: 批量新增角色资源关联开始");
        Assert.notNull(addForm.getRoleId());
        List<RoleResourceRel> toBeAddRoleResourceRel = this.getToBeAddRoleResourceRel(addForm);
        if (toBeAddRoleResourceRel != null && !toBeAddRoleResourceRel.isEmpty()) {
            int count = roleResourceRelMapper.batchAddRoleResourceRel(toBeAddRoleResourceRel);
            if (count == Constant.ZERO) {
                throw new ServerException("批量新增角色资源关联失败");
            }

        }
    }

    /**
     * 获取所有的角色List
     *
     * @return
     */
    @Override
    public List<Map<String, String>> getRoleList() {
        List<Map<String, String>> roles = new ArrayList<>();

        List<Role> roleList = roleMapper.selectList(new LambdaQueryWrapper<Role>()
                .eq(Role::getStatus, CodeEnum.ENABLE.getCode())
        );
        if (roleList == null && roleList.isEmpty()){
            return null;
        }
        roleList.stream().forEach(role -> {
            Map<String, String> map = new HashMap<>(4);
            map.put("roleId", role.getPkRoleId());
            map.put("roleName", role.getRoleName());
            roles.add(map);
        });
        return roles;
    }


    /**
     * 得到需要新增的角色资源关联集合
     *
     * @param addRoleResourceRelForm
     * @return
     */
    private List<RoleResourceRel> getToBeAddRoleResourceRel(AddRoleResourceRelForm addRoleResourceRelForm) {
        String roleId = addRoleResourceRelForm.getRoleId();
        List<String> resourceIds = addRoleResourceRelForm.getResourceIds();
        List<RoleResourceRel> roleResourceRelList = this.roleResourceRelMapper.selectList(new LambdaQueryWrapper<RoleResourceRel>()
                .eq(RoleResourceRel::getRoleId, roleId)
        );
        if (roleResourceRelList != null && !roleResourceRelList.isEmpty()) {
            Map<String, String> pkIdAndTargetIdMap = roleResourceRelList.stream()
                    .collect(Collectors.toMap(RoleResourceRel::getPkRoleResourceId, RoleResourceRel::getResourceId));
            // 接收需要删除的关联主键id
            List<String> toDelPkIds = DataHandle.handleToDelAndTargetIds(resourceIds, pkIdAndTargetIdMap);
            if (toDelPkIds != null && !toDelPkIds.isEmpty()) {
                this.roleResourceRelMapper.deleteBatchIds(toDelPkIds);
            }
        }
        if (resourceIds == null || resourceIds.isEmpty()) {
            return null;
        }

        List<RoleResourceRel> toBeAddRoleResourceRelList = Lists.newArrayList();
        resourceIds.forEach(resourceId -> {
            RoleResourceRel roleResourceRel = new RoleResourceRel();
            roleResourceRel.setRoleId(roleId);
            roleResourceRel.setResourceId(resourceId);
            toBeAddRoleResourceRelList.add(roleResourceRel);
        });
        return toBeAddRoleResourceRelList;

    }

    private void editRoleValidate(EditRoleForm editForm) {
        if (StringUtils.isEmpty(editForm.getPkRoleId())) {//新增验证
            Assert.notNull(editForm.getRoleName());
            Role role = roleMapper.selectOne(new LambdaQueryWrapper<Role>()
                    .eq(Role::getRoleName, editForm.getRoleName())
            );
            Assert.isNull(role, "角色名称已存在！");
        } else {//修改验证
            Assert.notNull(roleMapper.selectById(editForm.getPkRoleId()), "参数错误");
            if (StringUtils.isNotEmpty(editForm.getRoleName())) {
                Role role = roleMapper.selectOne(new LambdaQueryWrapper<Role>()
                        .eq(Role::getRoleName, editForm.getRoleName())
                );
                if (null != role) {
                    if (!StringUtils.equals(role.getPkRoleId(), editForm.getPkRoleId())) {
                        throw new ServerException("角色名称已存在！");
                    }
                }
            }
        }

    }
}
