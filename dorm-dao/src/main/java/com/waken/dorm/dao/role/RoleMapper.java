package com.waken.dorm.dao.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.waken.dorm.common.entity.role.Role;
import com.waken.dorm.common.form.role.ListAddedRoleForm;
import com.waken.dorm.common.view.role.UserRoleView;
import com.waken.dorm.common.view.user.UserRoleRelView;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role> {
    int batchUpdateStatus(Map<String, Object> param);

    List<UserRoleView> listUserRole();

    List<UserRoleRelView> listAddedRoles(ListAddedRoleForm listAddedRoleForm);

    List<UserRoleRelView> listSuperAdminRoles(String userId);
}