package com.waken.dorm.common.utils;

import com.waken.dorm.common.base.ShiroAccoutInfo;
import com.waken.dorm.common.config.Config;
import com.waken.dorm.common.exception.DormException;
import com.waken.dorm.common.utils.redis.RedisCacheManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * @ClassName ShiroUtils
 * @Description ShiroUtils
 * @Author zhaoRong
 * @Date 2019/3/21 20:10
 **/
@Component
public class ShiroUtils {
    private static final Logger logger = LoggerFactory.getLogger(ShiroUtils.class);
    @Autowired
    RedisCacheManager redisCacheManager;
    public static ShiroAccoutInfo getAccount() {
        try {
            Session session = SecurityUtils.getSubject().getSession();
            ShiroAccoutInfo account = new ShiroAccoutInfo();
            Object user = session.getAttribute("userSession");
            if(user==null){
                user = new ShiroAccoutInfo();
            }
            BeanMapper.copy(user, account);
            return account == null ? new ShiroAccoutInfo() : account;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void clearUserSession() {
        Session session = SecurityUtils.getSubject().getSession();
        session.removeAttribute("userSession");
        session.removeAttribute("userSessionId");
    }

    public static Config getConfigObj(HttpHeaders headers) {
        Config cfgObj = new Config();
        String configStr = headers.getFirst("config");
        logger.info("app端请求参数config = "+configStr);
        JsonMapper jsonMapper = new JsonMapper();
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(configStr)) {
            cfgObj = jsonMapper.fromJson(configStr, Config.class);
            if (cfgObj == null) {
                cfgObj = new Config();
            }
        }
        return cfgObj;
    }

    /**
     * 获取后台登录用户userId
     *
     * @return
     */
    public static String getUserId() {
        ShiroAccoutInfo accoutInfo = getAccount();
        if (accoutInfo.getUserId() == null){
            logger.info("shiro 没有获取到当前登录用户id！");
            throw new DormException("请你重新登录！");
        }else{
            logger.info("shiro 成功获取到当前登录id为："+accoutInfo.getUserId());
            return accoutInfo.getUserId();
        }
    }
}
