package com.waken.dorm.serviceImpl.user;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.base.UpdateStatusEntity;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.entity.user.UserPrivilege;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.role.ListAddedRoleForm;
import com.waken.dorm.common.form.user.AddUserRoleRelForm;
import com.waken.dorm.common.form.user.EditUserForm;
import com.waken.dorm.common.form.user.UserForm;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.base.ImgView;
import com.waken.dorm.common.view.user.UserRoleRelView;
import com.waken.dorm.common.view.user.UserRolesView;
import com.waken.dorm.common.view.user.UserView;
import com.waken.dorm.dao.role.RoleMapper;
import com.waken.dorm.dao.user.UserMapper;
import com.waken.dorm.dao.user.UserPrivilegeMapper;
import com.waken.dorm.manager.UserManager;
import com.waken.dorm.service.cache.CacheService;
import com.waken.dorm.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserPrivilegeMapper userPrivilegeMapper;
    @Autowired
    AliyunOSSUtil aliyunOSSUtil;
    @Autowired
    CacheService cacheService;

    @Override
    @Transactional
    public User saveUser(EditUserForm editUserForm) {
        this.editUserValidate(editUserForm);
        String curUserId = UserManager.getCurrentUserId();

        Date curDate = DateUtils.getCurrentDate();
        int count;
        User user = new User();
        String encodePassword = PasswordEncode.shiroEncode(editUserForm.getUserName(), editUserForm.getPassword());//shiro加密 密码
        BeanMapper.copy(editUserForm, user);
        user.setPassword(encodePassword);
        user.setLastModifyTime(curDate);
        user.setLastModifyUserId("guest");
        if (StringUtils.isEmpty(editUserForm.getUserId())) {//新增
            log.info("service: 新增用户开始");
            String userId = UUIDUtils.getPkUUID();
            user.setUserId(userId);
            user.setStatus(CodeEnum.ENABLE.getCode());
            user.setCreateTime(curDate);
            user.setCreateUserId("guest");
            count = userMapper.insert(user);
            if (count == Constant.ZERO) {
                throw new ServerException("新增用户失败");
            }
            user.setPassword(Constant.NULL_STRING);
            return user;
        } else {//修改
            log.info("service: 更新用户信息开始");
            userMapper.updateById(user);
            user.setPassword(Constant.NULL_STRING);
            return user;
        }

    }

    @Override
    public User queryUserInfo(String userName) {
        log.info("service: 查询用户开始");
        List<User> userList = userMapper.selectList(new EntityWrapper<User>()
                .eq("user_name", userName)
        );
        if (userList.isEmpty()) {
            return null;
        }
        return userList.get(Constant.ZERO);
    }

    @Override
    @Transactional// 事务控制
    public void deleteUser(DeleteForm deleteForm) {
        log.info("service: 删除用户开始");
        List<String> userIds = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        if (CodeEnum.YES.getCode() == delStatus) { // 物理删除
            this.checkUserRef(userIds);
            List<User> userList = userMapper.selectBatchIds(userIds);
            StringBuffer sb = new StringBuffer();
            for (User user : userList) {
                if (CodeEnum.YES.getCode() == user.getStatus()) {
                    sb.append(user.getUserName());
                }
            }
            if (StringUtils.isEmpty(sb.toString())) {
                userMapper.deleteBatchIds(userIds);
            } else {
                throw new ServerException("删除失败，原因以下用户处于启用中：" + sb.toString());
            }
        } else if (CodeEnum.NO.getCode() == delStatus) {
            this.checkUserRef(userIds);
            int count = userMapper.batchUpdateStatus(getToUpdateStatusMap(userIds, CodeEnum.DELETE.getCode()));
            if (count == Constant.ZERO) {
                throw new ServerException("状态删除失败");
            }
        } else {
            throw new ServerException("删除状态码错误！");
        }
        this.deleteUserCache(userIds);
    }

    //删除用户缓存
    private void deleteUserCache(List<String> userIds) {
        List<User> userList = userMapper.selectBatchIds(userIds);
        try {
            for (User user : userList) {
                cacheService.deleteUser(user.getUserName());
                cacheService.deleteRoles(user.getUserName());
                cacheService.deletePermissions(user.getUserName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 检验用户与角色、资源时候存在关联，如果存在则不能删除
     *
     * @param userIds
     */
    private void checkUserRef(List<String> userIds) {
        List<UserPrivilege> privileges = userPrivilegeMapper.selectBatchUserIds(userIds);
        if (null != privileges && !privileges.isEmpty()) {
            throw new ServerException("删除失败，原因是用户与角色或者资源存在关联，请解除关联后重试！");
        }
    }

    @Override
    public PageInfo<UserView> listUsers(UserForm userForm) {
        log.info("service: 用户信息分页查询开始");
        if (userForm.getStartTime() != null) {
            userForm.setStartTime(DateUtils.formatDate2DateTimeStart(userForm.getStartTime()));
        }
        if (userForm.getEndTime() != null) {
            userForm.setEndTime(DateUtils.formatDate2DateTimeEnd(userForm.getEndTime()));
        }
        PageHelper.startPage(userForm.getPageNum(), userForm.getPageSize());
        List<UserView> userViews = userMapper.listUsers(userForm);
        return new PageInfo<>(userViews);
    }

    @Transactional // 事务控制
    @Override
    public void batchAddUserRoleRel(AddUserRoleRelForm addUserRoleRelForm) {
        log.info("service: 批量新增用户角色关联开始");
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isEmpty(addUserRoleRelForm.getUserId())) {
            sb.append("用户 id为空！");
        }
        if (addUserRoleRelForm.getRoleIds().isEmpty()) {
            sb.append("角色 id集合为空！");
        }
        if (StringUtils.isEmpty(sb.toString())) {
            throw new ServerException("批量新增用户角色关联失败，原因：" + sb.toString());
        }
//        List<UserRoleRel> toBeAddUserRoleRel = this.getToBeAddUserRoleRel(addUserRoleRelForm);
//        if (!toBeAddUserRoleRel.isEmpty()){
//            int count = Constant.ZERO;
//            count = userRoleRelMapper.batchAddUserRoleRel(toBeAddUserRoleRel);
//            if (count == Constant.ZERO){
//                throw new DormException("批量新增用户角色关联失败");
//            }
//
//        }
    }

    //    @Transactional
//    private List<UserRoleRel> getToBeAddUserRoleRel(AddUserRoleRelForm addUserRoleRelForm){
//        //TODO
////        String userId = addUserRoleRelForm.getUserId();
////        List<String> roleIds = addUserRoleRelForm.getRoleIds();
////        UserRoleRelExample example = new UserRoleRelExample();
////        UserRoleRelExample.Criteria criteria = example.createCriteria();
////        criteria.andUserIdEqualTo(userId);
////        List<UserRoleRel> userRoleRelList = userRoleRelMapper.selectByExample(example);
////        if (!userRoleRelList.isEmpty()){
////            List<String> existIds = new ArrayList<>();// 接收已经存在关联的角色id
////            List<String> toDelIds = new ArrayList<>();// 接收需要删除的角色id
////            List<String> oldIds = new ArrayList<>();// 接收所有已经绑定的角色id
////            for (UserRoleRel userRoleRel : userRoleRelList) {
////                oldIds.add(userRoleRel.getRoleId());
////            }
////            for (String oldId : oldIds) {
////                if (roleIds.contains(oldId)) {
////                    existIds.add(oldId);
////                } else {
////                    toDelIds.add(oldId);
////                }
////            }
////            roleIds.removeAll(existIds);
////            if (!toDelIds.isEmpty()) {
////                UserRoleRelExample delExample = new UserRoleRelExample();
////                UserRoleRelExample.Criteria delCriteria = delExample.createCriteria();
////                delCriteria.andUserIdEqualTo(userId);
////                delCriteria.andRoleIdIn(roleIds);
////                int count = Constant.ZERO;
////                count = userRoleRelMapper.delete(new EntityWrapper<UserRoleRel>()
////                     .eq("user_id",userId)
////                     .allEq()
////                );
////                if (count == Constant.ZERO){
////                    throw new DormException("删除用户角色关联失败");
////                }
////            }
////
////        }
////        if (roleIds.isEmpty()){
////            List<UserRoleRel> toBeAddUserRoleRelList = new ArrayList<UserRoleRel>();
////            for (String roleId : roleIds) {
////                String pkUserRoleId = UUIDUtils.getPkUUID();
////                String curUserId = ShiroUtils.getUserId();
////                Date curDate = DateUtils.getCurrentDate();
////                UserRoleRel userRoleRel = new UserRoleRel();
////                userRoleRel.setPkUserRoleId(pkUserRoleId);
////                userRoleRel.setUserId(userId);
////                userRoleRel.setRoleId(roleId);
////                userRoleRel.setStatus(CodeEnum.ENABLE.getCode());
////                userRoleRel.setCreateTime(curDate);
////                userRoleRel.setCreateUserId(curUserId);
////                userRoleRel.setLastModifyTime(curDate);
////                userRoleRel.setLastModifyUserId(curUserId);
////                toBeAddUserRoleRelList.add(userRoleRel);
////            }
////            return toBeAddUserRoleRelList;
////        }else {
////            return new ArrayList<UserRoleRel>();
////        }
//        return null;
//   }
    @Override
    public UserRolesView listUserRoles(ListAddedRoleForm listAddedRoleForm) {
        String userId = listAddedRoleForm.getUserId();
        String curUserId = UserManager.getCurrentUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new ServerException("用户id为空");
        }
        List<UserRoleRelView> userRoleRelViewList = new ArrayList<>();
        if (isSuperAdmin(curUserId)) {//超级管理员可以管理所有角色
            userRoleRelViewList = roleMapper.listSuperAdminRoles(curUserId);
        } else {//其他管理员只能管理自己拥有的角色
            listAddedRoleForm.setCurUserId(curUserId);
            userRoleRelViewList = roleMapper.listAddedRoles(listAddedRoleForm);
        }
        if (!userRoleRelViewList.isEmpty()) {
            List<UserRoleRelView> addedList = new ArrayList<>();//用于接收已经存在关联的角色信息
            List<UserRoleRelView> unAddedList = new ArrayList<>();//用于接收没有关联的角色信息
            for (UserRoleRelView userRoleRelView : userRoleRelViewList) {
                if (StringUtils.isEmpty(userRoleRelView.getPkUserRoleId())) {//关联id为空，表示是未关联的角色
                    unAddedList.add(userRoleRelView);
                } else {
                    addedList.add(userRoleRelView);
                }
            }
            UserRolesView userRolesView = new UserRolesView();
            userRolesView.setAddedList(addedList);
            userRolesView.setToBeAddList(unAddedList);
            return userRolesView;
        }
        return new UserRolesView();
    }

    /**
     * 上传头像
     *
     * @param file
     * @return
     */
    @Transactional
    @Override
    public ImgView uploadUserImg(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String userId = UserManager.getCurrentUserId();
        String folderName = Constant.STUDENT;
        if (StringUtils.isEmpty(fileName)) {
            throw new ServerException("您上传的头像图片文件为空！");
        }
        try {
            User user = userMapper.selectById(userId);
            if (!StringUtils.isEmpty(user.getImgUrl())) {
                aliyunOSSUtil.deleteFile(user.getImgUrl());// 删除已经存在的头像
                log.info("删除已经存在的用户头像成功！");
            }
            File toFile = FileUtils.multipartFileToFile(file);
            // 上传到OSS
            String headImgUrl = aliyunOSSUtil.upLoad(toFile, folderName);
            if (StringUtils.isEmpty(headImgUrl)) {
                throw new ServerException("上传学生头像失败！");
            }
            user.setImgUrl(headImgUrl);
            userMapper.updateById(user);
            log.info("用户头像访问路径：" + headImgUrl);
            ImgView imgView = new ImgView();
            imgView.setImgUrl(headImgUrl);
            return imgView;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("用户头像上传失败，原因是：" + e.getMessage());
        }
        return null;
    }

    /**
     * 更新登录时间
     *
     * @param user
     */
    @Override
    public void updateLoginTime(User user) {
        user.setLastLoginTime(new Date());
        userMapper.updateById(user);
        // 重新将用户信息加载到 redis中
        try {
            cacheService.saveUser(user.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询出所有的用户信息
     *
     * @return
     */
    @Override
    public List<User> selectList() {
        return userMapper.selectList(null);
    }

    /**
     * 判断当前操作用户是否有超级管理员角色
     *
     * @return
     */
    private boolean isSuperAdmin(String curUserId) {
        List<UserRoleRelView> roleList = roleMapper.listUserRoleInfo(curUserId);
        if (!roleList.isEmpty()) {
            for (UserRoleRelView role : roleList) {
                if (Constant.SuperAdmin.equals(role.getRoleName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 验证
     *
     * @param userForm
     */
    private void editUserValidate(EditUserForm userForm) {
        if (StringUtils.isEmpty(userForm.getUserId())) {//新增验证
            if (StringUtils.isEmpty(userForm.getUserName()) || StringUtils.isEmpty(userForm.getPassword())) {
                throw new ServerException("用户名或密码为空！");
            }
            if (StringUtils.isEmpty(userForm.getMobile())) {
                throw new ServerException("电话不能为空！");
            } else {
                if (!CheckUtils.isPhoneLegality(userForm.getMobile())) {
                    throw new ServerException("请输入正确的手机号码！");
                }
            }
            if (StringUtils.isEmpty(userForm.getEmail())) {
                throw new ServerException("电子邮箱不能为空！");
            } else {
                if (!CheckUtils.isEmailLegality(userForm.getEmail())) {
                    throw new ServerException("请输入正确的电子邮箱！");
                }
            }
            List<User> userList = userMapper.selectList(new EntityWrapper<User>()
                    .eq("user_name", userForm.getUserName())
            );
            if (!userList.isEmpty()) {
                throw new ServerException("用户名已存在！");
            }
        } else {//修改验证
            if (StringUtils.isNotEmpty(userForm.getUserName())) {
                List<User> userList = userMapper.selectList(new EntityWrapper<User>()
                        .eq("user_name", userForm.getUserName())
                );
                if (!userList.isEmpty()) {
                    if (!StringUtils.equals(userList.get(Constant.ZERO).getUserId(), userForm.getUserId())) {
                        throw new ServerException("用户名已存在！");
                    }
                }
            }
        }

    }

    public Map<String, Object> getToUpdateStatusMap(List<String> pkIds, Integer status) {
        if (pkIds.isEmpty()) {
            throw new ServerException("pkIds为空！");
        }
        if (status == null) {
            throw new ServerException("状态编码为空！");
        }
        Map<String, Object> param = new HashMap<>();
        String curUserId = UserManager.getCurrentUserId();
        Date curDate = DateUtils.getCurrentDate();
        List<UpdateStatusEntity> updateList = new ArrayList<>();// 接收需要修改的id和状态码
        for (String pkId : pkIds) {
            UpdateStatusEntity statusEntity = new UpdateStatusEntity();
            statusEntity.setPkId(pkId);
            statusEntity.setStatus(status);
            statusEntity.setLastModifyTime(curDate);
            statusEntity.setLastModifyUserId(curUserId);
            updateList.add(statusEntity);
        }
        param.put("list", updateList);
        return param;
    }
}
