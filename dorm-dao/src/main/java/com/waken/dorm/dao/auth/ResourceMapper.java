package com.waken.dorm.dao.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.waken.dorm.common.entity.resource.Resource;
import com.waken.dorm.common.form.resource.ButtonResources;
import com.waken.dorm.common.view.resource.ResourceView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResourceMapper extends BaseMapper<Resource> {
    /**
     * 查询所有的资源视图信息
     *
     * @return
     */
    List<ResourceView> selectResourceView(@Param("resourceType") Integer resourceType);

    /**
     * 通过资源id查询对应的按钮权限
     * @param resourceId
     * @return
     */
    List<ButtonResources> selectButtonByResourceId(@Param("resourceId") String resourceId);

    /**
     * 批量更新资源状态
     *
     * @param param
     * @return
     */
    int batchUpdateStatus(Map<String, Object> param);




}