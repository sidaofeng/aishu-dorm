package com.waken.dorm.dao.resource;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.waken.dorm.common.entity.resource.Resource;
import com.waken.dorm.common.view.resource.ResourceView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResourceMapper extends BaseMapper<Resource> {
    /**
     * 查询所有的资源视图信息
     * @param status
     * @return
     */
    List<ResourceView> selectResourceView(@Param("status") Integer status);

    /**
     * 批量更新资源状态
     * @param param
     * @return
     */
    int batchUpdateStatus(Map<String, Object> param);
}