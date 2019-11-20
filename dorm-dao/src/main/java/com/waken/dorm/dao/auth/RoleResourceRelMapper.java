package com.waken.dorm.dao.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.waken.dorm.common.entity.role.RoleResourceRel;
import com.waken.dorm.common.view.user.UserRoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleResourceRelMapper extends BaseMapper<RoleResourceRel> {
    /**
     * 批量新增角色资源关联
     * @param roleResourceRelList
     * @return
     */
    int batchAddRoleResourceRel(List<RoleResourceRel> roleResourceRelList);

    /**
     * 通过资源id批量删除与角色的关联
     *
     * @param Ids
     * @return
     */
    int deleteByResources(@Param("Ids") List<String> Ids);

    /**
     * 通过角色id批量删除与资源的关联
     *
     * @param Ids
     * @return
     */
    int deleteByRoles(@Param("Ids") List<String> Ids);

    /**
     * 通过批量角色Ids批量查询资源名称
     *
     * @param Ids
     * @return
     */
    List<UserRoleResource> selectByRoles(@Param("Ids") List<String> Ids);

    /**
     * 通过批量资源Ids批量查询角色名称
     *
     * @param Ids
     * @return
     */
    List<UserRoleResource> selectByResources(@Param("Ids") List<String> Ids);

    /**
     * 查询用户关联的角色所关联的资源id
     * @param userId
     * @return
     */
    List<RoleResourceRel> selectRoleResourceIds(@Param("userId") String userId);

}