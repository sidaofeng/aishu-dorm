package com.waken.dorm.serviceImpl.user;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.role.ListAddedRoleForm;
import com.waken.dorm.common.form.user.EditUserForm;
import com.waken.dorm.common.form.user.UserForm;
import com.waken.dorm.common.sequence.UUIDSequence;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.base.ImgView;
import com.waken.dorm.common.view.user.UserRoleRelView;
import com.waken.dorm.common.view.user.UserRoleResource;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

        BeanMapper.copy(editUserForm, user);

        user.setLastModifyTime(curDate);
        user.setLastModifyUserId(curUserId);
        if (StringUtils.isEmpty(editUserForm.getUserId())) {//新增
            log.info("service: 新增用户开始");
            String userId = UUIDSequence.next();
            String encodePassword = PasswordEncode.shiroEncode(editUserForm.getUserName(), editUserForm.getPassword());//shiro加密 密码
            user.setPassword(encodePassword);
            user.setUserId(userId);
            user.setStatus(CodeEnum.ENABLE.getCode());
            user.setCreateTime(curDate);
            user.setCreateUserId(curUserId);
            count = userMapper.insert(user);
            if (count == Constant.ZERO) {
                throw new ServerException("新增用户失败");
            }
            user.setPassword(Constant.NULL_STRING);
            return user;
        } else {//修改
            log.info("service: 更新用户信息开始");
            if (StringUtils.isNotBlank(editUserForm.getPassword())){
                String encodePassword = PasswordEncode.shiroEncode(editUserForm.getUserName(), editUserForm.getPassword());//shiro加密 密码
                user.setPassword(encodePassword);
            }
            userMapper.updateById(user);
            user.setPassword(Constant.NULL_STRING);
            return user;
        }

    }

    @Override
    public User queryUserInfo(String userName) {
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
            this.checkUser(userIds);
            //直接删除用户与资源或者角色的资源
            this.userPrivilegeMapper.deleteByUsers(userIds);
            userMapper.deleteBatchIds(userIds);
        } else if (CodeEnum.NO.getCode() == delStatus) {
            this.checkUser(userIds);
            this.userPrivilegeMapper.deleteByUsers(userIds);
            Map toUpdateStatusMap = DormUtil.getToUpdateStatusMap(userIds, UserManager.getCurrentUserId());
            int count = userMapper.batchUpdateStatus(toUpdateStatusMap);
            Assert.isFalse(count == Constant.ZERO);
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
    private void checkUser(List<String> userIds) {
//        List<User> userList = userMapper.selectBatchIds(userIds);
//        StringBuffer sb = new StringBuffer();
//        for (User user : userList) {
//            if (CodeEnum.YES.getCode() == user.getStatus()) {
//                sb.append(user.getUserName());
//            }
//        }
//        if (!StringUtils.isEmpty(sb.toString())) {
//            throw new ServerException("删除失败，原因以下用户处于启用中：" + sb.toString());
//        }
        List<UserRoleResource> userRoles = userPrivilegeMapper.selectByUsers(userIds);
        userRoles.stream().forEach(userRole -> {
            if (StringUtils.equals(userRole.getRoleName(), Constant.SuperAdmin)) {
                throw new ServerException("删除失败，用户" + userRole.getUserName() + "是超级管理员，不能删除！");
            }
        });
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

    @Override
    public UserRolesView listUserRoles(String userId) {
        Assert.notNull(userId);
        ListAddedRoleForm listAddedRoleForm = new ListAddedRoleForm();
        listAddedRoleForm.setUserId(userId);
        String curUserId = UserManager.getCurrentUserId();
        List<UserRoleRelView> userRoleRelViewList;
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
        Assert.notNull(fileName);
        try {
            User user = userMapper.selectById(userId);
            if (!StringUtils.isEmpty(user.getImgUrl())) {
                aliyunOSSUtil.deleteFile(user.getImgUrl());// 删除已经存在的头像
            }
            File toFile = FileUtils.multipartFileToFile(file);
            // 上传到OSS
            String headImgUrl = aliyunOSSUtil.upLoad(toFile, folderName);
            Assert.notNull(headImgUrl,"上传头像失败!");
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
        List<String> roles = userPrivilegeMapper.selectUserRoles(curUserId);
        if (roles.contains(Constant.SuperAdmin)) {
            return true;
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
            Assert.notNull(userForm.getUserName(),"用户名为空！");
            Assert.notNull(userForm.getPassword(),"密码为空！");
            Assert.notNull(userForm.getMobile(),"手机号码为空！");
            Assert.isTrue(CheckUtils.isPhoneLegality(userForm.getMobile()),"请输入正确的手机号码！");
            Assert.notNull(userForm.getEmail(),"邮箱为空！");
            Assert.isTrue(CheckUtils.isPhoneLegality(userForm.getEmail()),"请输入正确的邮箱！");
            List<User> userList = userMapper.selectList(new EntityWrapper<User>()
                    .eq("user_name", userForm.getUserName())
            );
            Assert.isNull(userList,userList.isEmpty(),"用户名已存在！");
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
}
