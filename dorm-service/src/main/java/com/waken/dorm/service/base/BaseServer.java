package com.waken.dorm.service.base;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseServer
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/15 11:01
 **/
public interface BaseServer {
    /**
     * 通过id得到需要更新的map对象
     * @param status
     * @param pkIds
     */
    public Map<String, Object> getToUpdateStatusMap(List<String> pkIds, Integer status);
}
