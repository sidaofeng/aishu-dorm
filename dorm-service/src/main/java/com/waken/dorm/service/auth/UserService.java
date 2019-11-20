package com.waken.dorm.service.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.user.EditUserForm;
import com.waken.dorm.common.form.user.UserForm;
import com.waken.dorm.common.view.base.ImgView;
import com.waken.dorm.common.view.user.UserRolesView;
import com.waken.dorm.common.view.user.UserView;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService extends IService<User> {
    /**
     * 保存或修改用户信息
     *
     * @param editUserForm user form表单
     */
    User saveUser(EditUserForm editUserForm);

    /**
     * 查询用户信息
     *
     * @param userName user name
     */
    User queryUserInfo(String userName);

    /**
     * 删除用户
     */
    void deleteUser(DeleteForm deleteForm);

    /**
     * 分页查询用户信息
     *
     * @param userForm
     * @return
     */
    IPage<UserView> listUsers(UserForm userForm);

    /**
     * 查询用户已经存在关联的角色 与 与用户没有的关联的角色
     *
     * @param userId
     * @return
     */
    UserRolesView listUserRoles(String userId);

    /**
     * 上传头像
     *
     * @param file
     * @return
     */
    ImgView uploadUserImg(MultipartFile file);

    /**
     * 更新登录时间,并保存登陆日志
     *
     * @param user
     * @param ip
     */
    void updateLoginTime(User user,String ip);

    /**
     * 查询出所有的用户信息
     *
     * @return
     */
    List<User> selectList();

    /**
     * 根据原密码修改新密码
     * @param userId
     * @param oldPassword
     * @param newPassword
     */
    int updatePassword(String userId,String oldPassword,String newPassword);

    /**
     * 重置密码
     *
     * @param userId
     * @return
     */
    void resetPassword(String userId);
}
