package com.waken.dorm.dao.student;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waken.dorm.common.entity.student.StudentBasic;
import com.waken.dorm.common.entity.student.StudentInfo;
import com.waken.dorm.common.form.student.StudentBasicForm;
import com.waken.dorm.common.view.student.StudentBasicView;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 学生基本数据表 Mapper 接口
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
public interface StudentBasicMapper extends BaseMapper<StudentBasic> {
    /**
     * 分页
     *
     * @param page
     * @param basicForm
     * @return
     */
    IPage<StudentBasicView> findPage(Page page, @Param("form") StudentBasicForm basicForm);

    /**
     * 登陆查询
     *
     * @param code     学号
     * @param password 密码
     * @return
     */
    StudentInfo studentLogin(String code, String password);
}
