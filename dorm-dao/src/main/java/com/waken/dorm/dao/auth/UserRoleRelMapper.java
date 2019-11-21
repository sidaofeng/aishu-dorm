package com.waken.dorm.dao.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.waken.dorm.common.entity.auth.UserRoleRel;
import com.waken.dorm.common.view.resource.UserMenuView;
import com.waken.dorm.common.view.user.UserRoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhaoRong
 * @since 2019-08-08
 */
public interface UserRoleRelMapper extends BaseMapper<UserRoleRel> {

    /**
     * 查询用户拥有的资源
     * @param userId
     * @return
     */
    List<UserMenuView> selectUserResources(@Param("userId") String userId,@Param("resourceType") Integer resourceType);

    /**
     * 查询角色拥有的资源
     *
     * @param roleId
     * @return
     */
    List<UserMenuView> selectRoleResources(@Param("roleId") String roleId);

    /**
     * 查询用户的权限
     *
     * @param userId
     * @return
     */
    List<String> selectUserPerms(@Param("userId") String userId);

    /**
     * 查询用户的角色
     *
     * @param userId
     * @return
     */
    List<String> selectUserRoles(@Param("userId") String userId);


    /**
     * 通过用户id批量删除用户关联的资源，角色
     *
     * @param Ids
     * @return
     */
    int deleteByUsers(@Param("Ids") List<String> Ids);


    /**
     * 批量新增用户与角色的关联
     *
     * @param userRoleList
     * @return
     */
    int batchAddUserRoleRel(List<UserRoleRel> userRoleList);


    /**
     * 通过用户id批量查询用户关联角色信息
     *
     * @param Ids
     * @return
     */
    List<UserRoleResource> selectByUsers(@Param("Ids") List<String> Ids);

    /**
     * 通过角色id批量查询
     *
     * @param Ids
     * @return
     */
    List<UserRoleResource> selectByRoles(@Param("Ids") List<String> Ids);

}
