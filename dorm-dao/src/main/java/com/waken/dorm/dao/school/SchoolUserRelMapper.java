package com.waken.dorm.dao.school;

import com.waken.dorm.common.entity.school.SchoolUserRel;
import com.waken.dorm.common.entity.school.SchoolUserRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolUserRelMapper {
    int countByExample(SchoolUserRelExample example);

    int deleteByExample(SchoolUserRelExample example);

    int deleteByPrimaryKey(String pkSchoolUserId);

    int insert(SchoolUserRel record);

    int insertSelective(SchoolUserRel record);

    List<SchoolUserRel> selectByExample(SchoolUserRelExample example);

    SchoolUserRel selectByPrimaryKey(String pkSchoolUserId);

    int updateByExampleSelective(@Param("record") SchoolUserRel record, @Param("example") SchoolUserRelExample example);

    int updateByExample(@Param("record") SchoolUserRel record, @Param("example") SchoolUserRelExample example);

    int updateByPrimaryKeySelective(SchoolUserRel record);

    int updateByPrimaryKey(SchoolUserRel record);
}