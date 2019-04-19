package com.waken.dorm.serviceImpl.role;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.role.Role;
import com.waken.dorm.common.entity.role.RoleExample;
import com.waken.dorm.common.entity.role.RoleResourceRel;
import com.waken.dorm.common.entity.role.RoleResourceRelExample;
import com.waken.dorm.common.entity.user.UserRoleRel;
import com.waken.dorm.common.entity.user.UserRoleRelExample;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.DormException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.role.AddRoleResourceRelForm;
import com.waken.dorm.common.form.role.EditRoleForm;
import com.waken.dorm.common.form.role.QueryRoleForm;
import com.waken.dorm.common.form.role.UserRoleRelForm;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.role.UserRoleView;
import com.waken.dorm.dao.role.RoleMapper;
import com.waken.dorm.dao.role.RoleResourceRelMapper;
import com.waken.dorm.dao.user.UserRoleRelMapper;
import com.waken.dorm.service.role.RoleService;
import com.waken.dorm.serviceImpl.base.BaseServerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    UserRoleRelMapper userRoleRelMapper;
    @Autowired
    RoleResourceRelMapper roleResourceRelMapper;

    @Transactional
    @Override
    public Role saveRole(EditRoleForm editRoleForm) {
        this.editRoleValidate(editRoleForm);
        String userId = ShiroUtils.getUserId();
        Date curDate = DateUtils.getCurrentDate();
        Role role = new Role();
        BeanMapper.copy(editRoleForm,role);
        role.setLastModifyTime(curDate);
        role.setLastModifyUserId(userId);
        if (StringUtils.isEmpty(editRoleForm.getPkRoleId())){//新增
            logger.info("service: 新增角色开始");
            String pkRoleId = UUIDUtils.getPkUUID();
            role.setPkRoleId(pkRoleId);
            role.setStatus(CodeEnum.ENABLE.getCode());
            role.setCreateTime(curDate);
            role.setCreateUserId(userId);
            int count = Constant.ZERO;
            count = roleMapper.insertSelective(role);
            if (count == Constant.ZERO){
                throw new DormException("新增角色失败");
            }
            return role;
        }else {//修改
            logger.info("service: 更新角色信息开始");
            roleMapper.updateByPrimaryKeySelective(role);
            return roleMapper.selectByPrimaryKey(editRoleForm.getPkRoleId());
        }

    }

    @Transactional // 事务控制
    @Override
    public void deleteRole(DeleteForm deleteForm) {
        logger.info("service: 删除角色开始");
        List<String> roleIds = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count = Constant.ZERO;
        if (CodeEnum.YES.getCode() == delStatus){ // 物理删除
            // 删除角色与用户的关联
            List<UserRoleRel> UserRoleRelList = userRoleRelMapper.selectByRoleIdList(roleIds);
            if (!UserRoleRelList.isEmpty()){
                StringBuffer sb = new StringBuffer();
                for (UserRoleRel userRoleRel : UserRoleRelList){
                    if (CodeEnum.ENABLE.getCode() == userRoleRel.getStatus()){
                        sb.append(userRoleRel.getRoleId());
                    }
                }
                if (StringUtils.isEmpty(sb.toString())){//删除角色与用户关联
                    UserRoleRelExample userRoleRelExample = new UserRoleRelExample();
                    UserRoleRelExample.Criteria userRoleRelCriteria = userRoleRelExample.createCriteria();
                    userRoleRelCriteria.andRoleIdIn(roleIds);
                    userRoleRelMapper.deleteByExample(userRoleRelExample);
                }else {
                    throw new DormException("删除角色失败，原因是以下角色与用户关联生效中："+sb.toString());
                }
            }
            // 删除角色与资源的关联
            List<RoleResourceRel> roleResourceRelList = roleResourceRelMapper.selectByRoleIds(roleIds);
            if (!roleResourceRelList.isEmpty()){
                StringBuffer sb = new StringBuffer();
                for (RoleResourceRel roleResourceRel : roleResourceRelList){
                    if (CodeEnum.ENABLE.getCode() == roleResourceRel.getStatus()){
                        sb.append(roleResourceRel.getRoleId());
                    }
                }
                if (StringUtils.isEmpty(sb.toString())){//删除角色与资源关联
                    RoleResourceRelExample example = new RoleResourceRelExample();
                    RoleResourceRelExample.Criteria criteria = example.createCriteria();
                    criteria.andRoleIdIn(roleIds);
                    roleResourceRelMapper.deleteByExample(example);
                }else {
                    throw new DormException("删除角色失败，原因是以下角色与资源关联生效中："+sb.toString());
                }
            }
            // 删除角色本身
            RoleExample roleExample = new RoleExample();
            RoleExample.Criteria criteria = roleExample.createCriteria();
            criteria.andPkRoleIdIn(roleIds);
            count = roleMapper.deleteByExample(roleExample);
            if (count == Constant.ZERO){
                throw new DormException("状态删除失败");
            }
        }else if(CodeEnum.NO.getCode() == delStatus){
            count = roleMapper.batchUpdateStatus(getToUpdateStatusMap(roleIds,CodeEnum.DELETE.getCode()));
            if (count == Constant.ZERO){
                throw new DormException("状态删除失败");
            }
            //删除用户角色关联
            UserRoleRelExample userRoleRelExample = new UserRoleRelExample();
            UserRoleRelExample.Criteria userRoleRelCriteria = userRoleRelExample.createCriteria();
            userRoleRelCriteria.andRoleIdIn(roleIds);
            userRoleRelMapper.deleteByExample(userRoleRelExample);
            //删除角色资源关联
            RoleResourceRelExample example = new RoleResourceRelExample();
            RoleResourceRelExample.Criteria criteria = example.createCriteria();
            criteria.andRoleIdIn(roleIds);
            roleResourceRelMapper.deleteByExample(example);
        }else {
            throw new DormException("删除状态码错误！");
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
        if (StringUtils.isEmpty(userId)){
            throw new DormException("用户id为空");
        }
        List<UserRoleView> userRoleViews = roleMapper.listUserRole();
        UserRoleRelExample example = new UserRoleRelExample();
        UserRoleRelExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<UserRoleRel> userRoleRels = userRoleRelMapper.selectByExample(example);
        if (!userRoleRels.isEmpty()){
            for(UserRoleView userRoleView : userRoleViews){
                if (StringUtils.equals(userRoleView.getRoleId(),userRoleRels.get(Constant.ZERO).getRoleId())){
                    userRoleView.setIsSelect(CodeEnum.YES.getCode());
                }
            }
        }
        return userRoleViews;
    }

    @Override
    public PageInfo<Role> listRoles(QueryRoleForm queryRoleForm) {
        logger.info("service: 查询角色开始");
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(queryRoleForm.getPkRoleId())) {
            criteria.andPkRoleIdEqualTo(queryRoleForm.getPkRoleId());
        }
        if (StringUtils.isNotEmpty(queryRoleForm.getRoleName())) {
            criteria.andRoleNameLike(queryRoleForm.getRoleName());
        }
        if (queryRoleForm.getStatus() != null) {
            criteria.andStatusEqualTo(queryRoleForm.getStatus());
        }
        example.setOrderByClause("last_modify_time desc");
        PageHelper.startPage(queryRoleForm.getPageNum(), queryRoleForm.getPageSize());
        List<Role> roles = roleMapper.selectByExample(example);
        return new PageInfo<Role>(roles);
    }

    /**
     * 添加单个用户与角色关联
     *
     * @param userRoleRelForm
     */
    @Transactional
    @Override
    public void addUserRoleRel(UserRoleRelForm userRoleRelForm) {
        if (StringUtils.isEmpty(userRoleRelForm.getUserId())){
            throw new DormException("用户id为空");
        }
        UserRoleRelExample example = new UserRoleRelExample();
        UserRoleRelExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userRoleRelForm.getUserId());
        List<UserRoleRel> userRoleRels = userRoleRelMapper.selectByExample(example);
        if (!userRoleRels.isEmpty()){//删除已存在的关联
            userRoleRelMapper.deleteByPrimaryKey(userRoleRels.get(Constant.ZERO).getPkUserRoleId());
        }
        UserRoleRel userRoleRel = new UserRoleRel();
        String pkUserRoleId = UUIDUtils.getPkUUID();
        String userId = ShiroUtils.getUserId();
        Date curDate = DateUtils.getCurrentDate();
        userRoleRel.setPkUserRoleId(pkUserRoleId);
        userRoleRel.setUserId(userRoleRelForm.getUserId());
        userRoleRel.setRoleId(userRoleRelForm.getRoleId());
        userRoleRel.setStatus(CodeEnum.ENABLE.getCode());
        userRoleRel.setCreateTime(curDate);
        userRoleRel.setCreateUserId(userId);
        userRoleRel.setLastModifyTime(curDate);
        userRoleRel.setLastModifyUserId(userId);
        int count = Constant.ZERO;
        count = userRoleRelMapper.insertSelective(userRoleRel);
        if (count == Constant.ZERO){
            throw new DormException("新增单个用户与角色关联失败！");
        }
    }

    @Transactional// 事务控制
    @Override
    public void batchAddRoleResourceRel(AddRoleResourceRelForm addRoleResourceRelForm) {
        logger.info("service: 批量新增角色资源关联开始");
        this.batchAddRelValidate(addRoleResourceRelForm);
        List<RoleResourceRel> toBeAddRoleResourceRel = this.getToBeAddRoleResourceRel(addRoleResourceRelForm);
        if (!toBeAddRoleResourceRel.isEmpty()){
            int count = Constant.ZERO;
            count = roleResourceRelMapper.batchAddRoleResourceRel(toBeAddRoleResourceRel);
            if (count == Constant.ZERO){
                throw new DormException("批量新增角色资源关联失败");
            }

        }
    }
    private void batchAddRelValidate(AddRoleResourceRelForm addRoleResourceRelForm){
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isEmpty(addRoleResourceRelForm.getPkRoleId())){
            sb.append("角色id为空！");
        }
        if (addRoleResourceRelForm.getPkResourceIds().isEmpty()){
            sb.append("资源id集合为空！");
        }
        if (!StringUtils.isEmpty(sb.toString())){
            throw new DormException("批量新增角色资源关联失败，原因："+sb.toString());
        }
        Role role = roleMapper.selectByPrimaryKey(addRoleResourceRelForm.getPkRoleId());
        if (StringUtils.equals(Constant.SuperAdmin,role.getRoleName())){
            throw new DormException("不能修改超级管理员的资源！");
        }
    }
    /**
     * 得到需要新增的角色资源关联集合
     * @param addRoleResourceRelForm
     * @return
     */
    @Transactional
    private List<RoleResourceRel> getToBeAddRoleResourceRel(AddRoleResourceRelForm addRoleResourceRelForm){
        String roleId = addRoleResourceRelForm.getPkRoleId();
        List<String> resourceIds = addRoleResourceRelForm.getPkResourceIds();
        RoleResourceRelExample example = new RoleResourceRelExample();
        RoleResourceRelExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        List<RoleResourceRel> roleResourceRelList = roleResourceRelMapper.selectByExample(example);
        if (!roleResourceRelList.isEmpty()){
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
                RoleResourceRelExample delExample = new RoleResourceRelExample();
                RoleResourceRelExample.Criteria delCriteria = delExample.createCriteria();
                delCriteria.andRoleIdEqualTo(roleId);
                delCriteria.andResourceIdIn(toDelIds);
                roleResourceRelMapper.deleteByExample(delExample);
            }
        }
        if (resourceIds.isEmpty()){
            return new ArrayList<RoleResourceRel>();
        }else {
            List<RoleResourceRel> toBeAddRoleResourceRelList = new ArrayList<RoleResourceRel>();
            for (String resourceId : resourceIds) {
                String pkRoleResourceId = UUIDUtils.getPkUUID();
                String userId = ShiroUtils.getUserId();
                Date curDate = DateUtils.getCurrentDate();
                RoleResourceRel roleResourceRel = new RoleResourceRel();
                roleResourceRel.setPkRoleResourceId(pkRoleResourceId);
                roleResourceRel.setRoleId(roleId);
                roleResourceRel.setResourceId(resourceId);
                roleResourceRel.setStatus(CodeEnum.ENABLE.getCode());
                roleResourceRel.setCreateTime(curDate);
                roleResourceRel.setCreateUserId(userId);
                roleResourceRel.setLastModifyTime(curDate);
                roleResourceRel.setLastModifyUserId(userId);
                toBeAddRoleResourceRelList.add(roleResourceRel);
            }
            return toBeAddRoleResourceRelList;
        }
    }

    private void editRoleValidate(EditRoleForm editRoleForm){
        if (StringUtils.isEmpty(editRoleForm.getPkRoleId())){//新增验证
            if (StringUtils.isEmpty(editRoleForm.getRoleName())){
                throw new DormException("角色名称不能为空！");
            }
            RoleExample example = new RoleExample();
            RoleExample.Criteria criteria = example.createCriteria();
            criteria.andRoleNameEqualTo(editRoleForm.getRoleName());
            List<Role> roles = roleMapper.selectByExample(example);
            if (!roles.isEmpty()){
                throw new DormException("角色名称已存在！");
            }
        }else {//修改验证
            Role role = roleMapper.selectByPrimaryKey(editRoleForm.getPkRoleId());
            if (role == null){
                throw new DormException("角色id无效!");
            }
            if (StringUtils.isNotEmpty(editRoleForm.getRoleName())){
            RoleExample example = new RoleExample();
            RoleExample.Criteria criteria = example.createCriteria();
            criteria.andRoleNameEqualTo(editRoleForm.getRoleName());
            List<Role> roles = roleMapper.selectByExample(example);
            if (!roles.isEmpty()){
                if (!StringUtils.equals(roles.get(Constant.ZERO).getPkRoleId(),editRoleForm.getPkRoleId())){
                    throw new DormException("角色名称已存在！");
                }
              }
            }
        }

    }
}
