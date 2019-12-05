package com.waken.dorm.service.basic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.waken.dorm.common.entity.dorm.SchoolCampus;
import com.waken.dorm.common.form.base.DeleteForm;

import java.util.List;

/**
 * <p>
 * 校区基本数据表 服务类
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
public interface SchoolCampusService extends IService<SchoolCampus> {

    /**
     * 新增
     *
     * @param schoolCampus
     * @return
     */
    int insert(SchoolCampus schoolCampus);

    /**
     * 删除
     *
     * @param deleteForm
     */
    void delete(DeleteForm deleteForm);

    /**
     * 更新
     *
     * @param schoolCampus
     * @return
     */
    int update(SchoolCampus schoolCampus);

    /**
     * 通过id获取校区信息
     *
     * @param id
     * @return
     */
    SchoolCampus get(String id);

    /**
     * 查询校区集合
     *
     * @return
     */
    List<SchoolCampus> list();
}
