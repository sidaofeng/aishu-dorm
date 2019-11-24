package com.waken.dorm.serviceImpl.auth;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waken.dorm.common.cache.CacheService;
import com.waken.dorm.common.cache.RedisService;
import com.waken.dorm.common.constant.CacheConstant;
import com.waken.dorm.common.entity.auth.User;
import com.waken.dorm.service.auth.UserPrivilegeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Slf4j
@Service("cacheService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CacheServiceImpl implements CacheService {

    private final RedisService redisService;
    private final UserPrivilegeService userPrivilegeService;
    private final ObjectMapper mapper;

    @Override
    public void testConnect() throws Exception {
        this.redisService.exists("test");
    }

    @Override
    public User getUser(String username) throws Exception {
        String userString = this.redisService.get(CacheConstant.USER_CACHE_PREFIX + username);
        if (StringUtils.isBlank(userString))
            return null;
        else
            return this.mapper.readValue(userString, User.class);
    }

    @Override
    public Set<String> getRoles(String username) throws Exception {
        String roleListString = this.redisService.get(CacheConstant.USER_ROLE_CACHE_PREFIX + username);
        if (StringUtils.isBlank(roleListString)) {
            return null;
        } else {
            JavaType type = mapper.getTypeFactory().constructParametricType(Set.class, String.class);
            return this.mapper.readValue(roleListString, type);
        }
    }

    @Override
    public Set<String> getPermissions(String username) throws Exception {
        String permissionListString = this.redisService.get(CacheConstant.USER_PERMISSION_CACHE_PREFIX + username);
        if (StringUtils.isBlank(permissionListString)) {
            return null;
        } else {
            JavaType type = mapper.getTypeFactory().constructParametricType(Set.class, String.class);
            return this.mapper.readValue(permissionListString, type);
        }
    }

    /**
     * 获取权限或者角色的Map对象
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, String> getPermsOrRoleMap(String name) throws Exception {
        String permsOrRoleMapString = this.redisService.get(name);
        if (StringUtils.isNotEmpty(permsOrRoleMapString)) {
            JavaType type = mapper.getTypeFactory().constructParametricType(Map.class, String.class, String.class);
            return this.mapper.readValue(permsOrRoleMapString, type);
        } else {
            if (StringUtils.equals("permsMap", name)) {
                return this.userPrivilegeService.getPermsMap();
            } else if (StringUtils.equals("rolesMap", name)) {
                return this.userPrivilegeService.getRoleMap();
            }
        }
        return null;
    }

    @Override
    public void saveUser(User user) throws Exception {
        String username = user.getUserName();
        this.deleteUser(username);
        redisService.set(CacheConstant.USER_CACHE_PREFIX + username, mapper.writeValueAsString(user));
    }

    @Override
    public void saveUser(String username) throws Exception {
        User user = userPrivilegeService.queryUserInfo(username);
        this.deleteUser(username);
        redisService.set(CacheConstant.USER_CACHE_PREFIX + username, mapper.writeValueAsString(user));
    }

    @Override
    public Set<String> saveRoles(User user) throws Exception {
        Set<String> userRoles = userPrivilegeService.getUserRoles(user.getUserId());
        this.deleteRoles(user.getUserName());
        redisService.set(CacheConstant.USER_ROLE_CACHE_PREFIX + user.getUserName(), mapper.writeValueAsString(userRoles));
        return userRoles;
    }

    @Override
    public Set<String> savePermissions(User user) throws Exception {
        Set<String> perms = userPrivilegeService.getUserPrivileges(user.getUserId());
        this.deletePermissions(user.getUserName());
        redisService.set(CacheConstant.USER_PERMISSION_CACHE_PREFIX + user.getUserName(), mapper.writeValueAsString(perms));
        return perms;
    }

    @Override
    public void savePermsAndRole() {
        Map<String, String> permsMap = this.userPrivilegeService.getPermsMap();
        Map<String, String> rolesMap = this.userPrivilegeService.getRoleMap();
        try {
            if (permsMap != null && !permsMap.isEmpty()) {
                this.redisService.set("permsMap", mapper.writeValueAsString(permsMap));
            }
            if (rolesMap != null && !rolesMap.isEmpty()) {
                this.redisService.set("rolesMap", mapper.writeValueAsString(rolesMap));
            }
        } catch (Exception e) {
            log.error("缓存异常：", e);
        }

    }

    @Override
    public void deleteUser(String username) throws Exception {
        username = username.toLowerCase();
        redisService.del(CacheConstant.USER_CACHE_PREFIX + username);
    }

    @Override
    public void deleteRoles(String username) throws Exception {
        username = username.toLowerCase();
        redisService.del(CacheConstant.USER_ROLE_CACHE_PREFIX + username);
    }

    @Override
    public void deletePermissions(String username) throws Exception {
        username = username.toLowerCase();
        redisService.del(CacheConstant.USER_PERMISSION_CACHE_PREFIX + username);
    }
}
