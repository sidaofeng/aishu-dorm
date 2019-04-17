package com.waken.dorm.dao.user;

import com.waken.dorm.common.entity.role.RoleResourceRel;
import com.waken.dorm.common.entity.user.UserRoleRel;
import com.waken.dorm.common.entity.user.UserRoleRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleRelMapper {
    List<UserRoleRel> selectByRoleIdList(@Param("Ids") List<String> Ids);//通过批量角色Ids批量查询

    int batchAddUserRoleRel(List<UserRoleRel> userRoleRelList);

    int countByExample(UserRoleRelExample example);

    int deleteByExample(UserRoleRelExample example);

    int deleteByPrimaryKey(String pkUserRoleId);

    int insert(UserRoleRel record);

    int insertSelective(UserRoleRel record);

    List<UserRoleRel> selectByExample(UserRoleRelExample example);

    UserRoleRel selectByPrimaryKey(String pkUserRoleId);

    int updateByExampleSelective(@Param("record") UserRoleRel record, @Param("example") UserRoleRelExample example);

    int updateByExample(@Param("record") UserRoleRel record, @Param("example") UserRoleRelExample example);

    int updateByPrimaryKeySelective(UserRoleRel record);

    int updateByPrimaryKey(UserRoleRel record);
}