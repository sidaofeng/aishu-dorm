package com.waken.dorm.dao.user;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.waken.dorm.common.entity.user.UserPrivilege;
import com.waken.dorm.common.view.resource.UserMenuView;
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
public interface UserPrivilegeMapper extends BaseMapper<UserPrivilege> {


    /**
     * 通过用户id批量删除用户关联的资源，角色
     *
     * @param ids
     * @return
     */
    int deleteBatchUserIds(@Param("ids") List<String> ids);

    /**
     * 通过用户id批量查询用户关联的资源，角色
     *
     * @param ids
     * @return
     */
    List<UserPrivilege> selectBatchUserIds(@Param("ids") List<String> ids);
    /**
     * 查询用户的菜单
     * @param userId
     * @return
     */
    List<UserMenuView> selectUserMenu(@Param("userId") String userId);
    /**
     * 查询用户的权限
     * @param userId
     * @return
     */
    List<String> selectUserPerms(@Param("userId") String userId);
    /**
     * 查询用户的角色
     * @param userId
     * @return
     */
    List<String> selectUserRoles(@Param("userId") String userId);
    /**
     * 查询角色拥有的资源
     * @param roleId
     * @return
     */
    List<UserMenuView> selectRoleResources(@Param("roleId") String roleId);
}
