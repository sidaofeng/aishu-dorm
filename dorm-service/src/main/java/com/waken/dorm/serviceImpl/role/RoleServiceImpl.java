package com.waken.dorm.serviceImpl.role;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import com.waken.dorm.common.form.role.UserRoleRelForm;
import com.waken.dorm.common.utils.BeanMapper;
import com.waken.dorm.common.utils.DateUtils;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.utils.UUIDUtils;
import com.waken.dorm.common.view.role.UserRoleView;
import com.waken.dorm.dao.role.RoleMapper;
import com.waken.dorm.dao.role.RoleResourceRelMapper;
import com.waken.dorm.dao.user.UserPrivilegeMapper;
import com.waken.dorm.manager.UserManager;
import com.waken.dorm.service.role.RoleService;
import com.waken.dorm.serviceImpl.base.BaseServerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName RoleServiceImpl
 * @Description 角色业务层
 * @Author zhaoRong
 * @Date 2019/3/25 14:04
 **/
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@Service
public class RoleServiceImpl extends BaseServerImpl implements RoleService {
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
            String pkRoleId = UUIDUtils.getPkUUID();
            role.setPkRoleId(pkRoleId);
            role.setStatus(CodeEnum.ENABLE.getCode());
            role.setCreateTime(curDate);
            role.setCreateUserId(userId);
            int count = Constant.ZERO;
            count = roleMapper.insert(role);
            if (count == Constant.ZERO) {
                throw new ServerException("新增角色失败");
            }
            return role;
        } else {//修改
            logger.info("service: 更新角色信息开始");
            roleMapper.updateById(role);
            return roleMapper.selectById(editRoleForm.getPkRoleId());
        }

    }

    @Transactional // 事务控制
    @Override
    public void deleteRole(DeleteForm deleteForm) {
        logger.info("service: 删除角色开始");
        List<String> roleIds = deleteForm.getDelIds();
        if (roleIds.isEmpty()) {
            throw new ServerException("参数为空！");
        }
        Integer delStatus = deleteForm.getDelStatus();
        int count;
        if (CodeEnum.YES.getCode() == delStatus) { // 物理删除
            this.checkRoleRef(roleIds);
            count = roleMapper.deleteBatchIds(roleIds);
            if (count == Constant.ZERO) {
                throw new ServerException("物理删除失败");
            }
        } else if (CodeEnum.NO.getCode() == delStatus) {
            this.checkRoleRef(roleIds);
            count = roleMapper.batchUpdateStatus(getToUpdateStatusMap(roleIds, CodeEnum.DELETE.getCode()));
            if (count == Constant.ZERO) {
                throw new ServerException("状态删除失败");
            }
        } else {
            throw new ServerException("删除状态码错误！");
        }
    }

    /**
     * 检验角色是否与用户或者资源存在关联，若有关联则不能删除
     *
     * @param roleIds
     */
    private void checkRoleRef(List<String> roleIds) {
        List<UserPrivilege> privileges = userPrivilegeMapper.selectList(new EntityWrapper<UserPrivilege>()
                .eq("subject_type", CodeEnum.ROLE.getCode())
        );
        Map<String, String> map = new HashMap<>(16);
        for (String roleId : roleIds) {
            map.put(roleId, roleId);
        }
        for (UserPrivilege privilege : privileges) {
            if (map.containsKey(privilege.getSubjectId())) {
                throw new ServerException("删除失败，原因是角色与用户存在关联，请解除关联后重试！");
            }
        }

        List<RoleResourceRel> roleList = roleResourceRelMapper.selectList(new EntityWrapper<>());
        for (RoleResourceRel roleRel : roleList) {
            if (map.containsKey(roleRel.getRoleId())) {
                throw new ServerException("删除失败，原因是角色与资源存在关联，请解除关联后重试！");
            }
        }
    }

    /**
     * 通过用户id 获取角色信息 将已存在关联的角色标记为选中
     *
     * @param userId
     * @return
     */
    @Override
    public List<UserRoleView> listUserRoleByUserId(String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new ServerException("用户id为空");
        }
        List<UserRoleView> userRoleViews = roleMapper.listUserRole();
//        List<UserRoleRel> userRoleRels = userRoleRelMapper.selectList(new EntityWrapper<UserRoleRel>()
//                .eq("user_id",userId)
//        );
//        if (!userRoleRels.isEmpty()){
//            for(UserRoleView userRoleView : userRoleViews){
//                if (StringUtils.equals(userRoleView.getRoleId(),userRoleRels.get(Constant.ZERO).getRoleId())){
//                    userRoleView.setIsSelect(CodeEnum.YES.getCode());
//                }
//            }
//        }
        return userRoleViews;
    }

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
     * 添加单个用户与角色关联
     *
     * @param userRoleRelForm
     */
    @Transactional
    @Override
    public void addUserRoleRel(UserRoleRelForm userRoleRelForm) {
        if (StringUtils.isEmpty(userRoleRelForm.getUserId())) {
            throw new ServerException("用户id为空");
        }
//        List<UserRoleRel> userRoleRels = userRoleRelMapper.selectList(new EntityWrapper<UserRoleRel>()
//                .eq("user_id",userRoleRelForm.getUserId())
//        );
//        if (!userRoleRels.isEmpty()){//删除已存在的关联
//            userRoleRelMapper.deleteById(userRoleRels.get(Constant.ZERO).getPkUserRoleId());
//        }
//        UserRoleRel userRoleRel = new UserRoleRel();
//        String pkUserRoleId = UUIDUtils.getPkUUID();
//        String userId = ShiroUtils.getUserId();
//        Date curDate = DateUtils.getCurrentDate();
//        userRoleRel.setPkUserRoleId(pkUserRoleId);
//        userRoleRel.setUserId(userRoleRelForm.getUserId());
//        userRoleRel.setRoleId(userRoleRelForm.getRoleId());
//        userRoleRel.setStatus(CodeEnum.ENABLE.getCode());
//        userRoleRel.setCreateTime(curDate);
//        userRoleRel.setCreateUserId(userId);
//        userRoleRel.setLastModifyTime(curDate);
//        userRoleRel.setLastModifyUserId(userId);
//        int count = Constant.ZERO;
//        count = userRoleRelMapper.insert(userRoleRel);
//        if (count == Constant.ZERO){
//            throw new DormException("新增单个用户与角色关联失败！");
//        }
    }

    @Transactional// 事务控制
    @Override
    public void batchAddRoleResourceRel(AddRoleResourceRelForm addRoleResourceRelForm) {
        logger.info("service: 批量新增角色资源关联开始");
        this.batchAddRelValidate(addRoleResourceRelForm);
        List<RoleResourceRel> toBeAddRoleResourceRel = this.getToBeAddRoleResourceRel(addRoleResourceRelForm);
        if (!toBeAddRoleResourceRel.isEmpty()) {
            int count = Constant.ZERO;
            count = roleResourceRelMapper.batchAddRoleResourceRel(toBeAddRoleResourceRel);
            if (count == Constant.ZERO) {
                throw new ServerException("批量新增角色资源关联失败");
            }

        }
    }

    private void batchAddRelValidate(AddRoleResourceRelForm addRoleResourceRelForm) {
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isEmpty(addRoleResourceRelForm.getPkRoleId())) {
            sb.append("角色id为空！");
        }
        if (addRoleResourceRelForm.getPkResourceIds().isEmpty()) {
            sb.append("资源id集合为空！");
        }
        if (!StringUtils.isEmpty(sb.toString())) {
            throw new ServerException("批量新增角色资源关联失败，原因：" + sb.toString());
        }
        Role role = roleMapper.selectById(addRoleResourceRelForm.getPkRoleId());
        if (StringUtils.equals(Constant.SuperAdmin, role.getRoleName())) {
            throw new ServerException("不能修改超级管理员的资源！");
        }
    }

    /**
     * 得到需要新增的角色资源关联集合
     *
     * @param addRoleResourceRelForm
     * @return
     */
    @Transactional
    private List<RoleResourceRel> getToBeAddRoleResourceRel(AddRoleResourceRelForm addRoleResourceRelForm) {
        String roleId = addRoleResourceRelForm.getPkRoleId();
        List<String> resourceIds = addRoleResourceRelForm.getPkResourceIds();
        List<RoleResourceRel> roleResourceRelList = roleResourceRelMapper.selectList(new EntityWrapper<RoleResourceRel>()
                .eq("role_id", roleId)
        );
        if (!roleResourceRelList.isEmpty()) {
            List<String> existIds = new ArrayList<>();// 接收已经存在关联的资源id
            List<String> toDelIds = new ArrayList<>();// 接收需要删除的资源id
            List<String> oldIds = new ArrayList<>();// 接收所有已经绑定的资源id
            for (RoleResourceRel roleResourceRel : roleResourceRelList) {
                oldIds.add(roleResourceRel.getResourceId());
            }
            for (String oldId : oldIds) {
                if (resourceIds.contains(oldId)) {
                    existIds.add(oldId);
                } else {
                    toDelIds.add(oldId);
                }
            }
            resourceIds.removeAll(existIds);
            if (!toDelIds.isEmpty()) {
                //TODO
//                RoleResourceRelExample delExample = new RoleResourceRelExample();
//                RoleResourceRelExample.Criteria delCriteria = delExample.createCriteria();
//                delCriteria.andRoleIdEqualTo(roleId);
//                delCriteria.andResourceIdIn(toDelIds);
            }
        }
        if (resourceIds.isEmpty()) {
            return new ArrayList<RoleResourceRel>();
        } else {
            List<RoleResourceRel> toBeAddRoleResourceRelList = new ArrayList<>();
            for (String resourceId : resourceIds) {
                String pkRoleResourceId = UUIDUtils.getPkUUID();
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

    private void editRoleValidate(EditRoleForm editRoleForm) {
        if (StringUtils.isEmpty(editRoleForm.getPkRoleId())) {//新增验证
            if (StringUtils.isEmpty(editRoleForm.getRoleName())) {
                throw new ServerException("角色名称不能为空！");
            }
            List<Role> roles = roleMapper.selectList(new EntityWrapper<Role>()
                    .eq("role_name", editRoleForm.getRoleName())
            );
            if (!roles.isEmpty()) {
                throw new ServerException("角色名称已存在！");
            }
        } else {//修改验证
            Role role = roleMapper.selectById(editRoleForm.getPkRoleId());
            if (role == null) {
                throw new ServerException("角色id无效!");
            }
            if (StringUtils.isNotEmpty(editRoleForm.getRoleName())) {
                List<Role> roles = roleMapper.selectList(new EntityWrapper<Role>()
                        .eq("role_name", editRoleForm.getRoleName())
                );
                if (!roles.isEmpty()) {
                    if (!StringUtils.equals(roles.get(Constant.ZERO).getPkRoleId(), editRoleForm.getPkRoleId())) {
                        throw new ServerException("角色名称已存在！");
                    }
                }
            }
        }

    }
}
