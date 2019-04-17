package com.waken.dorm.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.waken.dorm.common.view.resource.ResourceMenuView;
import com.waken.dorm.service.resource.ResourceService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DormShiroRealm
 * @Description ShiroConfig
 * @Author zhaoRong
 * @Date 2019/3/21 19:45
 **/
@Configuration
public class ShiroConfig {
    @Autowired
    private ResourceService resourceService;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     * 
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        System.out.println("ShiroConfig: shirFilter开始");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 没有登陆的用户只能访问登陆页面，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/waken/dorm/unAuth");
//        // 登录成功后要跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("/waken/dorm/user/successPage");
//         未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/waken/dorm/unAuth");
        //自定义拦截器
//        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
//        //限制同一帐号同时在线的个数。
//        filtersMap.put("kickout", kickoutSessionControlFilter());
//        filtersMap.put("authc", crosFilter());
//        shiroFilterFactoryBean.setFilters(filtersMap);
        // 拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/waken/dorm/user/login", "anon");
        filterChainDefinitionMap.put("/waken/dorm/unAuth", "anon");
//        filterChainDefinitionMap.put("/css/**", "anon");
//        filterChainDefinitionMap.put("/js/**", "anon");
//        filterChainDefinitionMap.put("/img/**", "anon");
//        filterChainDefinitionMap.put("/font-awesome/**", "anon");
        List<ResourceMenuView> resourcesList = resourceService.listAllResourcesForShiro();
        if (!resourcesList.isEmpty()) {
            for (ResourceMenuView resource : resourcesList) {
                if (StringUtils.isNotEmpty(resource.getResourceUrl())) {
                    String permission = "perms[" + resource.getResourceUrl() + "]";
                    filterChainDefinitionMap.put(resource.getResourceUrl(), permission);
                }
            }
        }
        //app端可以匿名访问
        filterChainDefinitionMap.put("/waken/dorm/app/**", "anon");
        //web端需认证
        filterChainDefinitionMap.put("/waken/dorm/**", "anon");
//        //此处需要添加一个kickout，上面添加的自定义拦截器才能生效
//        filterChainDefinitionMap.put("/waken/dorm/**", "authc,kickout");// 表示需要认证才可以访问
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        //构建SecurityManager环境
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(myShiroRealm());
        // 自定义缓存实现 使用redis
        // securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public DormShiroRealm myShiroRealm() {
        DormShiroRealm dormShiroRealm = new DormShiroRealm();
        dormShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return dormShiroRealm;
    }

    /**
     * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码; ）
     * 
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于
                                                        // md5(md5(""));

        return hashedCredentialsMatcher;
    }

    /**
     * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
     * 
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 配置shiro redisManager 使用的是shiro-redis开源插件
     * 
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setExpire(1800);// 配置缓存过期时间
        redisManager.setTimeout(timeout);
        redisManager.setPassword(password);
        return redisManager;
    }
//    /**
//     * 限制同一账号登录同时登录人数控制
//     *
//     * @return
//     */
//    @Bean
//    public SessionControlFilter kickoutSessionControlFilter() {
//        SessionControlFilter kickoutSessionControlFilter = new SessionControlFilter();
//        kickoutSessionControlFilter.setCache(cacheManager());
//        kickoutSessionControlFilter.setSessionManager(sessionManager());
//        kickoutSessionControlFilter.setKickoutAfter(false);
//        kickoutSessionControlFilter.setMaxSession(1);
//        kickoutSessionControlFilter.setKickoutUrl("/waken/dorm/kickout");
//        return kickoutSessionControlFilter;
//    }
//
//    @Bean
//    public CrossFilter crosFilter(){
//        CrossFilter crosFilter = new CrossFilter();
//        return crosFilter;
//    }

    /**
     * cacheManager 缓存 redis实现 使用的是shiro-redis开源插件
     * 
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * shiro session的管理
     */
    @Bean
    public SessionManager sessionManager() {
        DormSessionManager dormSessionManager = new DormSessionManager();
        dormSessionManager.setSessionDAO(redisSessionDAO());
        return dormSessionManager;
    }
}
