package com.waken.dorm.service.school;

import com.waken.dorm.common.entity.school.SchoolClass;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.school.EditSchoolClassForm;
import com.waken.dorm.common.form.school.SchoolClassForm;
import com.waken.dorm.common.form.school.SchoolClassTreeForm;
import com.waken.dorm.common.view.base.TreeView;
import com.waken.dorm.common.view.school.SchoolClassTreeView;
import com.waken.dorm.common.view.school.SchoolClassView;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName SchoolClassService
 * @Description SchoolClassService
 * @Author zhaoRong
 * @Date 2019/4/1 18:30
 **/
public interface SchoolClassService {
    /**
     * （保存/修改）学校类别
     * @param editSchoolClassForm
     * @return
     */
    public SchoolClass saveSchoolClass(EditSchoolClassForm editSchoolClassForm);

    /**
     * 删除学校类别
     * @param deleteForm
     */
    public void deleteSchoolClass(DeleteForm deleteForm);

    /**
     * 查询学校类别集合
     * @param schoolClassForm
     * @return
     */
    public List<SchoolClassView> listSchoolClasses(SchoolClassForm schoolClassForm);
    /**
     * 查询学校类别树集合
     * @param schoolClassTreeForm
     * @return
     */
    public List<TreeView> listSchoolClassTree(SchoolClassTreeForm schoolClassTreeForm);
    /**
     * 通过学生id获取学校id
     * @param studentId
     * @return
     */
    public String getSchoolIdByStudentId(String studentId);

    /**
     * 通过学习类别id批量导入学生（excel）
     * @param multipartFile
     * @param classId
     */
    public void batchImportStudentByClassId(MultipartFile multipartFile,String classId);

    /**
     * 通过主键id查询学校类别信息
     * @param schoolClassTreeForm
     * @return
     */
    public SchoolClass selectByPkId(SchoolClassTreeForm schoolClassTreeForm);
}
