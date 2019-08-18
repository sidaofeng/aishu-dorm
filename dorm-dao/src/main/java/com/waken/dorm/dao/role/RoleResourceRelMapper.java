package com.waken.dorm.dao.role;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.waken.dorm.common.entity.role.RoleResourceRel;
import com.waken.dorm.common.form.role.ListAddedRoleForm;
import com.waken.dorm.common.view.user.UserRoleRelView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleResourceRelMapper extends BaseMapper<RoleResourceRel> {
    List<RoleResourceRel> selectByRoleIds(@Param("Ids") List<String> Ids);//通过批量角色Ids批量查询

    List<RoleResourceRel> selectByResourceIds(@Param("Ids") List<String> Ids);//通过批量资源Ids批量查询

    int batchAddRoleResourceRel(List<RoleResourceRel> roleResourceRelList);

    List<UserRoleRelView> listAddedRoles(ListAddedRoleForm listAddedRoleForm);

    /**
     * 通过用户id与资源类型（1菜单，2按钮）查询
     *
     * @param userId       用户id
     * @param resourceType 资源类型（1菜单，2按钮）
     * @return
     */
    List<RoleResourceRel> selectByUserAndResourceType(@Param("userId") String userId, @Param("resourceType") int resourceType);
}