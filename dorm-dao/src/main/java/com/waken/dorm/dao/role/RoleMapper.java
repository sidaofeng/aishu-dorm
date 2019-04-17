package com.waken.dorm.dao.role;

import com.waken.dorm.common.entity.role.Role;
import com.waken.dorm.common.entity.role.RoleExample;
import java.util.List;
import java.util.Map;

import com.waken.dorm.common.form.role.ListAddedRoleForm;
import com.waken.dorm.common.view.role.UserRoleView;
import com.waken.dorm.common.view.user.UserRoleRelView;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int batchUpdateStatus(Map<String,Object> param);

    List<UserRoleView> listUserRole();

    List<UserRoleRelView> listUserRoleInfo(String curUserId);

    List<UserRoleRelView> listAddedRoles(ListAddedRoleForm listAddedRoleForm);

    List<UserRoleRelView> listSuperAdminRoles(String userId);

    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(String pkRoleId);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(String pkRoleId);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}