package com.waken.dorm.common.cache;

import com.waken.dorm.common.entity.auth.User;

import java.util.Map;
import java.util.Set;

public interface CacheService {

    /**
     * 测试 Redis是否连接成功
     */
    void testConnect() throws Exception;

    /**
     * 从缓存中获取用户
     *
     * @param username 用户名
     * @return User
     */
    User getUser(String username) throws Exception;

    /**
     * 从缓存中获取用户角色
     *
     * @param username 用户名
     * @return 角色集
     */
    Set<String> getRoles(String username) throws Exception;

    /**
     * 从缓存中获取用户权限
     *
     * @param username 用户名
     * @return 权限集
     */
    Set<String> getPermissions(String username) throws Exception;

    /**
     * 获取权限或者角色的Map对象
     *
     * @param name
     * @return
     * @throws Exception
     */
    Map<String, String> getPermsOrRoleMap(String name) throws Exception;

    /**
     * 缓存用户信息，只有当用户信息是查询出来的，完整的，才应该调用这个方法
     * 否则需要调用下面这个重载方法
     *
     * @param user 用户信息
     */
    void saveUser(User user) throws Exception;

    /**
     * 缓存用户信息
     *
     * @param username 用户名
     */
    void saveUser(String username) throws Exception;

    /**
     * 缓存用户角色信息
     *
     * @param user 用户
     */
    Set<String> saveRoles(User user) throws Exception;

    /**
     * 缓存用户权限信息
     *
     * @param user 用户名
     */
    Set<String> savePermissions(User user) throws Exception;

    /**
     * 保存所有的权限以及角色信息
     */
    void savePermsAndRole();

    /**
     * 删除用户信息
     *
     * @param username 用户名
     */
    void deleteUser(String username) throws Exception;

    /**
     * 删除用户角色信息
     *
     * @param username 用户名
     */
    void deleteRoles(String username) throws Exception;

    /**
     * 删除用户权限信息
     *
     * @param username 用户名
     */
    void deletePermissions(String username) throws Exception;

}
