package com.waken.dorm.dao.student;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    IPage<StudentView> listStudents(Page page,@Param("form") StudentForm studentForm);

    int batchAddStudent(List<Student> studentList);

    StudentInfo studentLogin(Student student);
}
