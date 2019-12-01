package com.waken.dorm.dao.dorm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.form.dorm.DormForm;
import com.waken.dorm.common.view.dorm.AppDormView;
import com.waken.dorm.common.view.dorm.DormView;
import org.apache.ibatis.annotations.Param;

public interface DormMapper extends BaseMapper<Dorm> {

    /**
     * 分页
     *
     * @param page
     * @param dormForm
     * @return
     */
    IPage<DormView> findPage(Page page, @Param("form") DormForm dormForm);

    /**
     * TODO
     *
     * @param studentId
     * @return
     */
    AppDormView queryAppDormView(String studentId);
}