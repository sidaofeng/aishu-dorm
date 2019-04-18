package com.waken.dorm.dao.school;

import java.util.List;
import java.util.Map;

import com.waken.dorm.common.entity.school.SchoolClass;
import com.waken.dorm.common.entity.school.SchoolClassExample;
import com.waken.dorm.common.entity.student.Student;
import com.waken.dorm.common.form.school.SchoolClassForm;
import com.waken.dorm.common.view.school.SchoolClassView;
import org.apache.ibatis.annotations.Param;

public interface SchoolClassMapper {
    int batchUpdateStatus(Map<String,Object> param);

    List<SchoolClassView> listSchoolClasses(SchoolClassForm schoolClassForm);

    List<SchoolClass> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询

    int countByExample(SchoolClassExample example);

    int deleteByExample(SchoolClassExample example);

    int deleteByPrimaryKey(String pkSchoolClassId);

    int insert(SchoolClass record);

    int insertSelective(SchoolClass record);

    List<SchoolClass> selectByExample(SchoolClassExample example);

    SchoolClass selectByPrimaryKey(String pkSchoolClassId);

    int updateByExampleSelective(@Param("record") SchoolClass record, @Param("example") SchoolClassExample example);

    int updateByExample(@Param("record") SchoolClass record, @Param("example") SchoolClassExample example);

    int updateByPrimaryKeySelective(SchoolClass record);

    int updateByPrimaryKey(SchoolClass record);
}