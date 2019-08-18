package com.waken.dorm.common.utils;

import com.waken.dorm.common.function.CacheSelector;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/8/15 1:20
 **/
@Slf4j
public class CacheUtils {
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
}
