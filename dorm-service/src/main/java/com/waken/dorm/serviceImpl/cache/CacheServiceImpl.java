package com.waken.dorm.serviceImpl.cache;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.properties.DormProperties;
import com.waken.dorm.dao.user.UserMapper;
import com.waken.dorm.service.cache.CacheService;
import com.waken.dorm.service.cache.RedisService;
import com.waken.dorm.service.role.RoleService;
import com.waken.dorm.service.user.UserPrivilegeService;
import com.waken.dorm.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserPrivilegeService userPrivilegeService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private DormProperties properties;

    @Override
    public void testConnect() throws Exception {
        this.redisService.exists("test");
    }

    @Override
    public User getUser(String username) throws Exception {
        String userString = this.redisService.get(Constant.USER_CACHE_PREFIX + username);
        if (StringUtils.isBlank(userString))
            throw new ServerException(ResultEnum.UN_AUTH);
        else
            return this.mapper.readValue(userString, User.class);
    }

    @Override
    public Set<String> getRoles(String username) throws Exception {
        String roleListString = this.redisService.get(Constant.USER_ROLE_CACHE_PREFIX + username);
        if (StringUtils.isBlank(roleListString)) {
            throw new ServerException(ResultEnum.UN_AUTH);
        } else {
            JavaType type = mapper.getTypeFactory().constructParametricType(Set.class, String.class);
            return this.mapper.readValue(roleListString, type);
        }
    }

    @Override
    public Set<String> getPermissions(String username) throws Exception {
        String permissionListString = this.redisService.get(Constant.USER_PERMISSION_CACHE_PREFIX + username);
        if (StringUtils.isBlank(permissionListString)) {
            throw new ServerException(ResultEnum.UN_AUTH);
        } else {
            JavaType type = mapper.getTypeFactory().constructParametricType(Set.class, String.class);
            return this.mapper.readValue(permissionListString, type);
        }
    }

    @Override
    public void saveUser(User user) throws Exception {
        String username = user.getUserName();
        this.deleteUser(username);
        redisService.set(Constant.USER_CACHE_PREFIX + username, mapper.writeValueAsString(user));
    }

    @Override
    public void saveUser(String username) throws Exception {
        User user = userService.queryUserInfo(username);
        this.deleteUser(username);
        redisService.set(Constant.USER_CACHE_PREFIX + username, mapper.writeValueAsString(user));
    }

    @Override
    public Set<String> saveRoles(String username) throws Exception {
        Set<String> userRoles = userPrivilegeService.getUserRoles(username);
        this.deleteRoles(username);
        redisService.set(Constant.USER_ROLE_CACHE_PREFIX + username, mapper.writeValueAsString(userRoles));
        return userRoles;
    }

    @Override
    public Set<String> savePermissions(String username) throws Exception {
        Set<String> perms = userPrivilegeService.getUserPrivileges(username);
        this.deletePermissions(username);
        redisService.set(Constant.USER_PERMISSION_CACHE_PREFIX + username, mapper.writeValueAsString(perms));
        return perms;
    }

    @Override
    public void deleteUser(String username) throws Exception {
        username = username.toLowerCase();
        redisService.del(Constant.USER_CACHE_PREFIX + username);
    }

    @Override
    public void deleteRoles(String username) throws Exception {
        username = username.toLowerCase();
        redisService.del(Constant.USER_ROLE_CACHE_PREFIX + username);
    }

    @Override
    public void deletePermissions(String username) throws Exception {
        username = username.toLowerCase();
        redisService.del(Constant.USER_PERMISSION_CACHE_PREFIX + username);
    }
}
