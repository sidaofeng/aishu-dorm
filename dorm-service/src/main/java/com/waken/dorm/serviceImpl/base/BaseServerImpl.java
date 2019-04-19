package com.waken.dorm.serviceImpl.base;

import com.waken.dorm.common.base.UpdateStatusEntity;
import com.waken.dorm.common.exception.DormException;
import com.waken.dorm.common.utils.DateUtils;
import com.waken.dorm.common.utils.ShiroUtils;
import com.waken.dorm.service.base.BaseServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @ClassName BaseServerImpl
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/15 11:05
 **/
public class BaseServerImpl implements BaseServer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 通过id得到需要更新的map对象
     * @param status
     * @param pkIds
     */
    public Map<String, Object> getToUpdateStatusMap(List<String> pkIds, Integer status){
        if (pkIds.isEmpty()){
            throw new DormException("pkIds为空！");
        }
        if (status == null){
            throw new DormException("状态编码为空！");
        }
        Map<String, Object> param = new HashMap<>();
        String curUserId = ShiroUtils.getUserId();
        Date curDate = DateUtils.getCurrentDate();
        List<UpdateStatusEntity> updateList = new ArrayList<>();// 接收需要修改的id和状态码
        for (String pkId : pkIds) {
            UpdateStatusEntity statusEntity = new UpdateStatusEntity();
            statusEntity.setPkId(pkId);
            statusEntity.setStatus(status);
            statusEntity.setLastModifyTime(curDate);
            statusEntity.setLastModifyUserId(curUserId);
            updateList.add(statusEntity);
        }
        param.put("list", updateList);
        return param;
    }
}
