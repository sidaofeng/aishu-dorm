package com.waken.dorm.dao.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waken.dorm.common.entity.auth.Role;
import com.waken.dorm.common.form.role.ListAddedRoleForm;
import com.waken.dorm.common.form.role.QueryRoleForm;
import com.waken.dorm.common.view.role.RoleView;
import com.waken.dorm.common.view.role.UserRoleView;
import com.waken.dorm.common.view.user.UserRoleRelView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role> {
    IPage<RoleView> rolePage(Page<RoleView> page, @Param("form") QueryRoleForm queryRoleForm);

    int batchUpdateStatus(Map<String, Object> param);

    List<UserRoleView> listUserRole();

    List<UserRoleRelView> listAddedRoles(ListAddedRoleForm listAddedRoleForm);

    List<UserRoleRelView> listSuperAdminRoles(String userId);
}