package com.waken.dorm.service.user;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.role.ListAddedRoleForm;
import com.waken.dorm.common.form.user.AddUserRoleRelForm;
import com.waken.dorm.common.form.user.EditUserForm;
import com.waken.dorm.common.form.user.UserForm;
import com.waken.dorm.common.view.base.ImgView;
import com.waken.dorm.common.view.user.UserRolesView;
import com.waken.dorm.common.view.user.UserView;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
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
    PageInfo<UserView> listUsers(UserForm userForm);

    /**
     * 批量新增用户与角色的关联
     *
     * @param addUserRoleRelForm
     */
    void batchAddUserRoleRel(AddUserRoleRelForm addUserRoleRelForm);

    /**
     * 查询用户已经存在关联的角色 与 与用户没有的关联的角色
     *
     * @param listAddedRoleForm
     * @return
     */
    UserRolesView listUserRoles(ListAddedRoleForm listAddedRoleForm);

    /**
     * 上传头像
     *
     * @param file
     * @return
     */
    ImgView uploadUserImg(MultipartFile file);

    /**
     * 更新登录时间
     *
     * @param user
     */
    void updateLoginTime(User user);

    /**
     * 查询出所有的用户信息
     *
     * @return
     */
    List<User> selectList();
}
