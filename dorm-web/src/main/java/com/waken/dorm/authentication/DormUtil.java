package com.waken.dorm.authentication;

import com.waken.dorm.common.authentication.JWTUtil;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.function.CacheSelector;
import com.waken.dorm.common.utils.SpringUtils;
import com.waken.dorm.service.cache.CacheService;
import com.waken.dorm.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;

import java.util.function.Supplier;
import java.util.stream.IntStream;

@Slf4j
public class DormUtil {

    /**
     * 缓存查询模板
     *
     * @param cacheSelector    查询缓存的方法
     * @param databaseSelector 数据库查询方法
     * @return T
     */
    public static <T> T selectCacheByTemplate(CacheSelector<T> cacheSelector, Supplier<T> databaseSelector) {
        try {
            log.debug("query data from redis ······");
            // 先查 Redis缓存
            T t = cacheSelector.select();
            if (t == null) {
                // 没有记录再查询数据库
                return databaseSelector.get();
            } else {
                return t;
            }
        } catch (Exception e) {
            // 缓存查询出错，则去数据库查询
            log.error("redis error：", e);
            log.debug("query data from database ······");
            return databaseSelector.get();
        }
    }

    /**
     * 获取当前操作用户
     *
     * @return 用户信息
     */
    public static User getCurrentUser() {
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        String username = JWTUtil.getUsername(token);
        UserService userService = SpringUtils.getBean(UserService.class);
        CacheService cacheService = SpringUtils.getBean(CacheService.class);

        return selectCacheByTemplate(() -> cacheService.getUser(username), () -> userService.queryUserInfo(username));
    }

    /**
     * 驼峰转下划线
     *
     * @param value 待转换值
     * @return 结果
     */
    public static String camelToUnderscore(String value) {
        if (StringUtils.isBlank(value))
            return value;
        String[] arr = StringUtils.splitByCharacterTypeCamelCase(value);
        if (arr.length == 0)
            return value;
        StringBuilder result = new StringBuilder();
        IntStream.range(0, arr.length).forEach(i -> {
            if (i != arr.length - 1)
                result.append(arr[i]).append(Constant.SPLIT_UNDERLINE);
            else
                result.append(arr[i]);
        });
        return StringUtils.lowerCase(result.toString());
    }

}
