package com.waken.dorm.serviceImpl.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.entity.user.UserPrivilege;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final UserPrivilegeMapper userPrivilegeMapper;
    private final AliyunOSSUtil aliyunOSSUtil;
    private final CacheService cacheService;

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
        if (StringUtils.isEmpty(editUserForm.getUserId())) {
            log.info("service: 新增用户开始");
            String userId = UUIDSequence.next();
            //shiro加密 密码 密码默认"000000" 使用用户id作为盐
            String encodePassword = PasswordEncode.shiroEncode(userId, Constant.DEFAULT_PASSWORD);
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
            userMapper.updateById(user);
            user.setPassword(Constant.NULL_STRING);
            return user;
        }

    }

    @Override
    public User queryUserInfo(String userName) {

        return userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserName,userName)
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)// 事务控制
    public void deleteUser(DeleteForm deleteForm) {
        log.info("service: 删除用户开始");
        List<String> userIds = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        // 物理删除
        if (CodeEnum.YES.getCode().equals(delStatus)) {
            this.checkUser(userIds);
            //直接删除用户与资源或者角色的资源
            this.userPrivilegeMapper.deleteByUsers(userIds);
            userMapper.deleteBatchIds(userIds);
        } else if (CodeEnum.NO.getCode().equals(delStatus)) {
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
    public IPage<UserView> listUsers(UserForm userForm) {
        log.info("service: 用户信息分页查询开始");
        if (userForm.getStartTime() != null) {
            userForm.setStartTime(DateUtils.formatDate2DateTimeStart(userForm.getStartTime()));
        }
        if (userForm.getEndTime() != null) {
            userForm.setEndTime(DateUtils.formatDate2DateTimeEnd(userForm.getEndTime()));
        }

        Page page = new Page(userForm.getPageNum(),userForm.getPageSize());

        IPage<UserView> userPage = this.userMapper.listUsers(page, userForm);

        Map<String,List<String>> userRoleMap = this.getUsersRoles();

        userPage.getRecords().stream().forEach(userView->{
            if (userRoleMap.containsKey(userView.getUserId())){
                userView.setRoleIds(userRoleMap.get(userView.getUserId()));
            }
        });


        return userPage;
    }

    /**
     * 获取所有用户对应的角色信息
     * @return
     */
    public Map<String,List<String>> getUsersRoles(){
        List<UserPrivilege> userPrivilegeList = userPrivilegeMapper.selectList(new LambdaQueryWrapper<UserPrivilege>()
                .eq(UserPrivilege::getSubjectType, CodeEnum.ROLE.getCode())
        );

        if (userPrivilegeList == null && userPrivilegeList.isEmpty()){
            return null;
        }

        Map<String,List<String>> userRoles = new HashMap<>(16);
        userPrivilegeList.stream().forEach(userPrivilege -> {
            if (!userRoles.containsKey(userPrivilege.getUserId())){
                userRoles.put(userPrivilege.getUserId(),new ArrayList<>());
            }
            userRoles.get(userPrivilege.getUserId()).add(userPrivilege.getSubjectId());

        });
        return userRoles;
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
        user.setLastLoginTime(DateUtils.getCurrentDate());
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
     * 根据原密码修改新密码
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     */
    @Override
    public int updatePassword(String userId, String oldPassword, String newPassword) {
        if (StringUtils.equals(oldPassword,newPassword)){
            throw new ServerException("原密码与新密码相同!");
        }
        User user = this.userMapper.selectById(userId);
        if (user == null){
            throw new ServerException("用户ID不正确！");
        }
        String encodePassword = PasswordEncode.shiroEncode(user.getUserId(), oldPassword);
        if (!StringUtils.equals(encodePassword,user.getPassword())){
            throw new ServerException("原密码错误！");
        }

        user.setPassword(PasswordEncode.shiroEncode(user.getUserId(), newPassword));

        int count = this.userMapper.updateById(user);
        if (1 != count){
            throw new ServerException("更新密码失败");
        }
        try {
            this.cacheService.deleteUser(user.getUserName());
            this.cacheService.deleteRoles(user.getUserName());
            this.cacheService.deletePermissions(user.getUserName());
        }catch (Exception e){
            log.error("删除用户缓存失败！");
        }
        return count;
    }


    /**
     * 重置密码
     *
     * @param userId
     * @return
     */
    @Override
    public void resetPassword(String userId) {
        Date curDate = DateUtils.getCurrentDate();
        String curUserId = UserManager.getCurrentUserId();

        if (StringUtils.isBlank(userId)) {//重置所有密码
            List<User> userList = this.userMapper.selectList(null);
            userList.forEach(user -> {
                user.setPassword(PasswordEncode.shiroEncode(user.getUserId(), Constant.DEFAULT_PASSWORD));
                user.setLastModifyTime(curDate);
                user.setLastModifyUserId(curUserId);
            });
            this.updateBatchById(userList, 500);

        } else {
            User user = this.userMapper.selectById(userId);
            if (user == null) {
                throw new ServerException("用户ID不正确！");
            }
            user.setPassword(PasswordEncode.shiroEncode(user.getUserId(), Constant.DEFAULT_PASSWORD));
            user.setLastModifyTime(curDate);
            user.setLastModifyUserId(curUserId);
            this.userMapper.updateById(user);
        }
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
            Assert.notNull(userForm.getMobile(),"手机号码为空！");
            Assert.isTrue(CheckUtils.isPhoneLegality(userForm.getMobile()),"请输入正确的手机号码！");
            Assert.notNull(userForm.getEmail(),"邮箱为空！");
            Assert.isTrue(CheckUtils.isEmailLegality(userForm.getEmail()),"请输入正确的邮箱！");
            User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, userForm.getUserName()));
            Assert.isNull(user,"用户名已存在！");
        } else {//修改验证
            if (StringUtils.isNotEmpty(userForm.getUserName())) {
                User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, userForm.getUserName()));
                if (null != user) {
                    if (!StringUtils.equals(user.getUserId(), userForm.getUserId())) {
                        throw new ServerException("用户名已存在！");
                    }
                }
            }
        }

    }
}
