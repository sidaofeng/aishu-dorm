package com.waken.dorm.service.basic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.waken.dorm.common.entity.dorm.DormBed;
import com.waken.dorm.common.form.base.DeleteForm;

import java.util.List;

/**
 * <p>
 * 宿舍床位 服务类
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
public interface BedService extends IService<DormBed> {
    /**
     * 新增
     *
     * @param bed
     * @return
     */
    int insert(DormBed bed);

    /**
     * 删除
     *
     * @param deleteForm
     */
    void delete(DeleteForm deleteForm);

    /**
     * 更新
     *
     * @param bed
     * @return
     */
    int update(DormBed bed);

    /**
     * 通过id获取楼层信息
     *
     * @param id
     * @return
     */
    DormBed get(String id);

    /**
     * 通过宿舍ID查询床位集合
     *
     * @param dormId
     * @return
     */
    List<DormBed> list(String dormId);
}
