package com.waken.dorm.service.student;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.entity.student.Student;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.student.EditStudentForm;
import com.waken.dorm.common.form.student.StudentForm;
import com.waken.dorm.common.view.base.ImgView;
import com.waken.dorm.common.view.student.StudentView;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName StudentService
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/3/29 21:13
 **/
public interface StudentService {
    /**
     * 批量新增学生
     */
    List<String> batchAddStudent(List<Student> studentList);

    /**
     * （保存或修改）单个学生信息
     *
     * @param editStudentForm
     * @return
     */
    Student saveStudent(EditStudentForm editStudentForm);

    /**
     * 删除学生信息
     *
     * @param deleteForm
     */
    void deleteStudent(DeleteForm deleteForm);

    /**
     * 分页查询学生信息
     *
     * @param studentForm
     * @return
     */
    PageInfo<StudentView> listStudents(StudentForm studentForm);

    /**
     * 学生登陆
     *
     * @param studentNum
     * @param password
     * @return
     */
    ResultView studentLogin(Integer studentNum, String password);

    /**
     * 上传头像
     *
     * @param file
     * @param studentId
     * @return
     */
    ImgView uploadHeadImg(MultipartFile file, String studentId);

    /**
     * 第一次登陆后必须先设置新密码
     *
     * @param studentId
     * @param newPassword
     */
    void updatePasswordByNew(String studentId, String newPassword);
}
