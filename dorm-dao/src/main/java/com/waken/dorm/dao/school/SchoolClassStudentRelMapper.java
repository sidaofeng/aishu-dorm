package com.waken.dorm.dao.school;

import com.waken.dorm.common.entity.school.SchoolClassStudentRel;
import com.waken.dorm.common.entity.school.SchoolClassStudentRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolClassStudentRelMapper {
    int batchImportStudentClassRel(List<SchoolClassStudentRel> schoolClassStudentRels);

    int countByExample(SchoolClassStudentRelExample example);

    int deleteByExample(SchoolClassStudentRelExample example);

    int deleteByPrimaryKey(String pkSchoolClassStudentId);

    int insert(SchoolClassStudentRel record);

    int insertSelective(SchoolClassStudentRel record);

    List<SchoolClassStudentRel> selectByExample(SchoolClassStudentRelExample example);

    SchoolClassStudentRel selectByPrimaryKey(String pkSchoolClassStudentId);

    int updateByExampleSelective(@Param("record") SchoolClassStudentRel record, @Param("example") SchoolClassStudentRelExample example);

    int updateByExample(@Param("record") SchoolClassStudentRel record, @Param("example") SchoolClassStudentRelExample example);

    int updateByPrimaryKeySelective(SchoolClassStudentRel record);

    int updateByPrimaryKey(SchoolClassStudentRel record);
}