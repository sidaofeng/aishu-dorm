package com.waken.dorm.dao.resource;

import com.waken.dorm.common.entity.resource.Resource;
import com.waken.dorm.common.entity.resource.ResourceExample;
import java.util.List;
import java.util.Map;

import com.waken.dorm.common.form.resource.ResourceMenuForm;
import com.waken.dorm.common.form.resource.ResourceTreeForm;
import com.waken.dorm.common.view.resource.ResourceMenuView;
import com.waken.dorm.common.view.resource.ResourceView;
import org.apache.ibatis.annotations.Param;

public interface ResourceMapper {
    int batchUpdateStatus(Map<String,Object> param);

    ResourceView queryResourceByPkId(String pkResourceId);

    List<ResourceMenuView> listAllResourcesByMenuForm(ResourceMenuForm resourceMenuForm);

    List<ResourceMenuView> listAllResources();

    List<Resource> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询

    int countByExample(ResourceExample example);

    int deleteByExample(ResourceExample example);

    int deleteByPrimaryKey(String pkResourceId);

    int insert(Resource record);

    int insertSelective(Resource record);

    List<Resource> selectByExample(ResourceExample example);

    Resource selectByPrimaryKey(String pkResourceId);

    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByExample(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
}