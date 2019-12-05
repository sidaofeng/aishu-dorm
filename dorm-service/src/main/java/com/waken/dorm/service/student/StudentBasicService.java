package com.waken.dorm.service.student;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.entity.student.StudentBasic;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.student.StudentBasicForm;
import com.waken.dorm.common.view.base.ImgView;
import com.waken.dorm.common.view.student.StudentBasicView;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 学生基本数据表 服务类
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
public interface StudentBasicService extends IService<StudentBasic> {
    /**
     * 新增
     *
     * @param basic
     * @return
     */
    int insert(StudentBasic basic);

    /**
     * 删除
     *
     * @param deleteForm
     */
    void delete(DeleteForm deleteForm);

    /**
     * 更新
     *
     * @param basic
     * @return
     */
    int update(StudentBasic basic);

    /**
     * 通过id获取信息
     *
     * @param id
     * @return
     */
    StudentBasic get(String id);

    /**
     * 分页查询宿舍维修记录
     *
     * @param basicForm
     * @return
     */
    IPage<StudentBasicView> findPage(StudentBasicForm basicForm);

    /**
     * 批量新增
     *
     * @param basicList
     */
    void batchInsert(List<StudentBasic> basicList);

    /**
     * 学生登陆
     *
     * @param studentCode
     * @param password
     * @return
     */
    AjaxResponse studentLogin(String studentCode, String password);

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
     * @param newPassword
     */
    void updatePasswordByNew(String newPassword);
}
