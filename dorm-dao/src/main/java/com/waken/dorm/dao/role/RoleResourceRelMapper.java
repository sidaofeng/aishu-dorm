package com.waken.dorm.dao.role;

import com.waken.dorm.common.entity.role.RoleResourceRel;
import com.waken.dorm.common.entity.role.RoleResourceRelExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RoleResourceRelMapper {
    List<RoleResourceRel> selectByRoleIds(@Param("Ids") List<String> Ids);//通过批量角色Ids批量查询

    List<RoleResourceRel> selectByResourceIds(@Param("Ids") List<String> Ids);//通过批量资源Ids批量查询

    int batchAddRoleResourceRel(List<RoleResourceRel> roleResourceRelList);

    int countByExample(RoleResourceRelExample example);

    int deleteByExample(RoleResourceRelExample example);

    int deleteByPrimaryKey(String pkRoleResourceId);

    int insert(RoleResourceRel record);

    int insertSelective(RoleResourceRel record);

    List<RoleResourceRel> selectByExample(RoleResourceRelExample example);

    RoleResourceRel selectByPrimaryKey(String pkRoleResourceId);

    int updateByExampleSelective(@Param("record") RoleResourceRel record, @Param("example") RoleResourceRelExample example);

    int updateByExample(@Param("record") RoleResourceRel record, @Param("example") RoleResourceRelExample example);

    int updateByPrimaryKeySelective(RoleResourceRel record);

    int updateByPrimaryKey(RoleResourceRel record);
}