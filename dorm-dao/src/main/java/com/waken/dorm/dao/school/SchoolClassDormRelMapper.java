package com.waken.dorm.dao.school;

import com.waken.dorm.common.entity.school.SchoolClassDormRel;
import com.waken.dorm.common.entity.school.SchoolClassDormRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolClassDormRelMapper {
    int countByExample(SchoolClassDormRelExample example);

    int deleteByExample(SchoolClassDormRelExample example);

    int deleteByPrimaryKey(String pkSchoolClassDormId);

    int insert(SchoolClassDormRel record);

    int insertSelective(SchoolClassDormRel record);

    List<SchoolClassDormRel> selectByExample(SchoolClassDormRelExample example);

    SchoolClassDormRel selectByPrimaryKey(String pkSchoolClassDormId);

    int updateByExampleSelective(@Param("record") SchoolClassDormRel record, @Param("example") SchoolClassDormRelExample example);

    int updateByExample(@Param("record") SchoolClassDormRel record, @Param("example") SchoolClassDormRelExample example);

    int updateByPrimaryKeySelective(SchoolClassDormRel record);

    int updateByPrimaryKey(SchoolClassDormRel record);
}