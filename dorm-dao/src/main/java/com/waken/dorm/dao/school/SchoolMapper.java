package com.waken.dorm.dao.school;

import java.util.List;
import java.util.Map;

import com.waken.dorm.common.entity.school.School;
import com.waken.dorm.common.entity.school.SchoolExample;
import com.waken.dorm.common.form.school.SchoolForm;
import com.waken.dorm.common.view.school.SchoolView;
import org.apache.ibatis.annotations.Param;

public interface SchoolMapper {
    int batchUpdateStatus(Map<String,Object> param);

    List<SchoolView> listSchoolView(SchoolForm schoolForm);

    List<School> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询

    int countByExample(SchoolExample example);

    int deleteByExample(SchoolExample example);

    int deleteByPrimaryKey(String pkSchoolId);

    int insert(School record);

    int insertSelective(School record);

    List<School> selectByExample(SchoolExample example);

    School selectByPrimaryKey(String pkSchoolId);

    int updateByExampleSelective(@Param("record") School record, @Param("example") SchoolExample example);

    int updateByExample(@Param("record") School record, @Param("example") SchoolExample example);

    int updateByPrimaryKeySelective(School record);

    int updateByPrimaryKey(School record);
}