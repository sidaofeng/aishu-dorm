package com.waken.dorm.serviceImpl.role;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import com.waken.dorm.common.sequence.UUIDSequence;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.role.UserRoleView;
import com.waken.dorm.dao.role.RoleMapper;
import com.waken.dorm.dao.role.RoleResourceRelMapper;
import com.waken.dorm.dao.user.UserPrivilegeMapper;
import com.waken.dorm.manager.UserManager;
import com.waken.dorm.service.role.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName RoleServiceImpl
 * @Description 角色业务层
 * @Author zhaoRong
 * @Date 2019/3/25 14:04
 **/
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@Service
public class RoleServiceImpl implements RoleService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RoleResourceRelMapper roleResourceRelMapper;
    @Autowired
    UserPrivilegeMapper userPrivilegeMapper;

    @Transactional
    @Override
    public Role saveRole(EditRoleForm editRoleForm) {
        this.editRoleValidate(editRoleForm);
        String userId = UserManager.getCurrentUserId();
        Date curDate = DateUtils.getCurrentDate();
        Role role = new Role();
        BeanMapper.copy(editRoleForm, role);
        role.setLastModifyTime(curDate);
        role.setLastModifyUserId(userId);
        if (StringUtils.isEmpty(editRoleForm.getPkRoleId())) {//新增
            logger.info("service: 新增角色开始");
            String pkRoleId = UUIDSequence.next();
            role.setPkRoleId(pkRoleId);
            role.setStatus(CodeEnum.ENABLE.getCode());
            role.setCreateTime(curDate);
            role.setCreateUserId(userId);
            int count = roleMapper.insert(role);
            if (count == Constant.ZERO) {
                throw new ServerException("新增角色失败");
            }
            return role;
        } else {//修改
            logger.info("service: 更新角色信息开始");
            roleMapper.updateById(role);
            return role;
        }

    }

    @Transactional // 事务控制
    @Override
    public void deleteRole(DeleteForm deleteForm) {
        logger.info("service: 删除角色开始");
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
    public PageInfo<Role> listRoles(QueryRoleForm queryRoleForm) {
        logger.info("service: 查询角色开始");
        EntityWrapper wrapper = new EntityWrapper<Role>();
        if (StringUtils.isNotEmpty(queryRoleForm.getPkRoleId())) {
            wrapper.eq("pk_role_id", queryRoleForm.getPkRoleId());
        }
        if (StringUtils.isNotEmpty(queryRoleForm.getRoleName())) {
            wrapper.like("role_name", queryRoleForm.getRoleName());
        }
        if (queryRoleForm.getStatus() != null) {
            wrapper.eq("status", queryRoleForm.getStatus());
        }
        wrapper.orderBy(true, "last_modify_time", false);
        PageHelper.startPage(queryRoleForm.getPageNum(), queryRoleForm.getPageSize());
        List<Role> roles = roleMapper.selectList(wrapper);
        return new PageInfo<>(roles);
    }

    /**
     * 删除角色与资源、用户的关联
     *
     * @param roleIds
     */
    private void delRoleRel(List<String> roleIds) {
        List<Role> roles = roleMapper.selectBatchIds(roleIds);
        roles.stream().forEach(role -> {
            if (StringUtils.equals(role.getRoleName(),Constant.SuperAdmin)){
                throw new ServerException("不能操作超级管理员角色！");
            }
        });
        //删除资源与用户的所有关联
        List<UserPrivilege> privileges = userPrivilegeMapper.selectList(new EntityWrapper<UserPrivilege>()
                .eq("subject_type", CodeEnum.ROLE.getCode())
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
        if (null != roleList && !roleList.isEmpty()){
            roleList.stream().forEach(rel->{
                if (roleIds.contains(rel.getRoleId())){
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
        List<UserPrivilege> privileges = userPrivilegeMapper.selectList(new EntityWrapper<UserPrivilege>()
                .eq("user_id", userId)
                .eq("subject_type", CodeEnum.ROLE.getCode())
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
    public void batchAddRoleResourceRel(AddRoleResourceRelForm addRoleResourceRelForm) {
        logger.info("service: 批量新增角色资源关联开始");
        this.batchAddRelValidate(addRoleResourceRelForm);
        List<RoleResourceRel> toBeAddRoleResourceRel = this.getToBeAddRoleResourceRel(addRoleResourceRelForm);
        if (!toBeAddRoleResourceRel.isEmpty()) {
            int count = roleResourceRelMapper.batchAddRoleResourceRel(toBeAddRoleResourceRel);
            if (count == Constant.ZERO) {
                throw new ServerException("批量新增角色资源关联失败");
            }

        }
    }

    private void batchAddRelValidate(AddRoleResourceRelForm addForm) {
        Assert.notNull(addForm.getPkRoleId());
        if (addForm.getPkResourceIds().isEmpty()) {
            throw new ServerException("参数为空！");
        }
        Role role = roleMapper.selectById(addForm.getPkRoleId());
        if (StringUtils.equals(Constant.SuperAdmin, role.getRoleName())) {
            throw new ServerException("不能操作超级管理员角色！");
        }
    }

    /**
     * 得到需要新增的角色资源关联集合
     *
     * @param addRoleResourceRelForm
     * @return
     */
    private List<RoleResourceRel> getToBeAddRoleResourceRel(AddRoleResourceRelForm addRoleResourceRelForm) {
        String roleId = addRoleResourceRelForm.getPkRoleId();
        List<String> resourceIds = addRoleResourceRelForm.getPkResourceIds();
        List<RoleResourceRel> roleResourceRelList = roleResourceRelMapper.selectList(new EntityWrapper<RoleResourceRel>()
                .eq("role_id", roleId)
        );
        if (!roleResourceRelList.isEmpty()) {
            List<String> existIds = new ArrayList<>();// 接收已经存在关联的资源id
            List<String> toDelPkIds = new ArrayList<>();// 接收需要删除的关联主键id
            for (RoleResourceRel rel : roleResourceRelList) {
                if (resourceIds.contains(rel.getResourceId())) {
                    existIds.add(rel.getResourceId());
                } else {
                    toDelPkIds.add(rel.getPkRoleResourceId());
                }
            }
            resourceIds.removeAll(existIds);
            if (!toDelPkIds.isEmpty()) {
                roleResourceRelMapper.deleteBatchIds(toDelPkIds);
            }
        }
        if (resourceIds.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<RoleResourceRel> toBeAddRoleResourceRelList = new ArrayList<>();
            for (String resourceId : resourceIds) {
                String pkRoleResourceId = UUIDSequence.next();
                String userId = UserManager.getCurrentUserId();
                Date curDate = DateUtils.getCurrentDate();
                RoleResourceRel roleResourceRel = new RoleResourceRel();
                roleResourceRel.setPkRoleResourceId(pkRoleResourceId);
                roleResourceRel.setRoleId(roleId);
                roleResourceRel.setResourceId(resourceId);
                roleResourceRel.setCreateTime(curDate);
                roleResourceRel.setCreateUserId(userId);
                toBeAddRoleResourceRelList.add(roleResourceRel);
            }
            return toBeAddRoleResourceRelList;
        }
    }

    private void editRoleValidate(EditRoleForm editForm) {
        if (StringUtils.isEmpty(editForm.getPkRoleId())) {//新增验证
            Assert.notNull(editForm.getRoleName());
            List<Role> roles = roleMapper.selectList(new EntityWrapper<Role>()
                    .eq("role_name", editForm.getRoleName())
            );
            if (!roles.isEmpty()) {
                throw new ServerException("角色名称已存在！");
            }
        } else {//修改验证
            Role role = roleMapper.selectById(editForm.getPkRoleId());
            Assert.notNull(role,"参数错误");
            if (StringUtils.isNotEmpty(editForm.getRoleName())) {
                List<Role> roles = roleMapper.selectList(new EntityWrapper<Role>()
                        .eq("role_name", editForm.getRoleName())
                );
                if (!roles.isEmpty()) {
                    if (!StringUtils.equals(roles.get(Constant.ZERO).getPkRoleId(), editForm.getPkRoleId())) {
                        throw new ServerException("角色名称已存在！");
                    }
                }
            }
        }

    }
}
