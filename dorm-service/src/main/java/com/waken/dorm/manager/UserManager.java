package com.waken.dorm.manager;

import com.waken.dorm.common.authentication.JWTToken;
import com.waken.dorm.common.authentication.JWTUtil;
import com.waken.dorm.common.constant.CacheConstant;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.utils.HttpContextUtils;
import com.waken.dorm.common.utils.SpringUtils;
import com.waken.dorm.common.utils.TokenUtils;
import com.waken.dorm.service.cache.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/8/16 23:08
 **/
@Slf4j
@Service
public class UserManager {
    /**
     * 获取当前登录用户的信息
     *
     * @return
     */
    public static User getCurrentUser() {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String token = request.getHeader(CacheConstant.USER_TOKEN);
        JWTToken jwtToken = new JWTToken(TokenUtils.decryptToken(token));
        String username = JWTUtil.getUsername(jwtToken.getToken());

        CacheService cacheService = (CacheService) SpringUtils.getBean("cacheService");
        User user = new User();
        try {
            user = cacheService.getUser(username);
        } catch (Exception e) {
            log.error("获取用户缓存信息错误" + e.getMessage());
        }
        if (user == null) {
            throw new ServerException(ResultEnum.TIME_OUT);
        }
        return user;
    }

    /**
     * 获取当前登录用户的id
     *
     * @return
     */
    public static String getCurrentUserId() {
        return getCurrentUser().getUserId();
    }
}
