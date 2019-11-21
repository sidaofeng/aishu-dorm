package com.waken.dorm.authentication;

import com.waken.dorm.common.authentication.JWTToken;
import com.waken.dorm.common.authentication.JWTUtil;
import com.waken.dorm.common.cache.CacheService;
import com.waken.dorm.common.cache.RedisService;
import com.waken.dorm.common.constant.CacheConstant;
import com.waken.dorm.common.entity.auth.User;
import com.waken.dorm.common.utils.HttpContextUtils;
import com.waken.dorm.common.utils.IPUtils;
import com.waken.dorm.common.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * 自定义实现 ShiroRealm，包含认证和授权两大模块
 *
 * @author aishu
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    RedisService redisService;
    @Autowired
    CacheService cacheService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * `
     * 授权模块，获取用户角色和权限
     *
     * @param token token
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        String username = JWTUtil.getUsername(token.toString());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        try {
            // 获取用户角色集
            Set<String> roleSet = cacheService.getRoles(username);
            simpleAuthorizationInfo.setRoles(roleSet);
            // 获取用户权限集
            Set<String> permissionSet = cacheService.getPermissions(username);
            simpleAuthorizationInfo.setStringPermissions(permissionSet);
        } catch (Exception e) {
            log.info("获取用户缓存权限信息错误");
        }

        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     *
     * @param authenticationToken 身份认证 token
     * @return AuthenticationInfo 身份认证信息
     * @throws AuthenticationException 认证相关异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 这里的 token是从 JWTFilter 的 executeLogin 方法传递过来的，已经经过了解密
        String token = (String) authenticationToken.getCredentials();

        // 从 redis里获取这个 token
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String ip = IPUtils.getIpAddr(request);

        String encryptToken = TokenUtils.encryptToken(token);
        String encryptTokenInRedis = null;
        try {
            encryptTokenInRedis = redisService.get(CacheConstant.TOKEN_CACHE_PREFIX + encryptToken + "." + ip);
        } catch (Exception ignore) {
        }
        // 如果找不到，说明已经失效
        if (StringUtils.isBlank(encryptTokenInRedis))
            throw new AuthenticationException("token已经过期");

        String username = JWTUtil.getUsername(token);

        if (StringUtils.isBlank(username))
            throw new AuthenticationException("token校验不通过");

        // 通过用户名查询用户信息
        try {
            User user = cacheService.getUser(username);
            if (user == null)
                throw new AuthenticationException("用户名或密码错误");
            if (!JWTUtil.verify(token, username, user.getPassword()))
                throw new AuthenticationException("token校验不通过");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("获取用户信息错误");
        }
        return new SimpleAuthenticationInfo(token, token, "dorm_shiro_realm");
    }
}
