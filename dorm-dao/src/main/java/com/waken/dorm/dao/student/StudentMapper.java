package com.waken.dorm.dao.student;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.waken.dorm.common.entity.student.Student;
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
public interface StudentMapper extends BaseMapper<Student> {
    int batchUpdateStatus(Map<String, Object> param);

    List<StudentView> listStudents(StudentForm studentForm);

    List<Student> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询

    int batchAddStudent(List<Student> studentList);

    StudentInfo studentLogin(Student student);
}
