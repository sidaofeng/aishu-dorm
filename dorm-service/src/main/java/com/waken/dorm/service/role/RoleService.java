package com.waken.dorm.service.role;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.entity.role.Role;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.role.AddRoleResourceRelForm;
import com.waken.dorm.common.form.role.EditRoleForm;
import com.waken.dorm.common.form.role.QueryRoleForm;
import com.waken.dorm.common.view.role.UserRoleView;

import java.util.List;

public interface RoleService {
    /**
     * 保存或修改角色信息
     *
     * @param editRoleForm
     * @return
     */
    Role saveRole(EditRoleForm editRoleForm);

    /**
     * 删除角色
     */
    void deleteRole(DeleteForm deleteForm);

    /**
     * 通过用户id 获取角色信息 将已存在关联的角色标记为选中
     *
     * @param userId
     * @return
     */
    List<UserRoleView> listRolesByUser(String userId);

    /**
     * 分页查询角色
     *
     * @param queryRoleForm
     * @return
     */
    PageInfo<Role> listRoles(QueryRoleForm queryRoleForm);

    /**
     * 新增角色与资源的关联
     *
     * @param addRoleResourceRelForm
     * @return
     */
    void batchAddRoleResourceRel(AddRoleResourceRelForm addRoleResourceRelForm);
}
