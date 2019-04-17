package com.waken.dorm.dao.student;

import com.waken.dorm.common.entity.student.Student;
import com.waken.dorm.common.entity.student.StudentExample;
import com.waken.dorm.common.entity.student.StudentInfo;
import com.waken.dorm.common.form.student.StudentForm;
import com.waken.dorm.common.view.student.StudentView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName StudentMapper
 * @Description StudentMapper
 * @Author zhaoRong
 * @Date 2019/3/29 21:12
 **/
public interface StudentMapper {
    int batchUpdateStatus(Map<String,Object> param);

    List<StudentView> listStudents(StudentForm studentForm);

    List<Student> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询

    int batchAddStudent(List<Student> studentList);

    StudentInfo studentLogin(Student student);

    int countByExample(StudentExample example);

    int deleteByExample(StudentExample example);

    int deleteByPrimaryKey(String pkStudentId);

    int insert(Student record);

    int insertSelective(Student record);

    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKey(String pkStudentId);

    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}
