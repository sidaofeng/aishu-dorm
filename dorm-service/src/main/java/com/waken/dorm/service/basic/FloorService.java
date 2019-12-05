package com.waken.dorm.service.basic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.waken.dorm.common.entity.dorm.BuildingFloor;
import com.waken.dorm.common.form.base.DeleteForm;

import java.util.List;

/**
 * <p>
 * 建筑物楼层 服务类
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
public interface FloorService extends IService<BuildingFloor> {
    /**
     * 新增
     *
     * @param floor
     * @return
     */
    int insert(BuildingFloor floor);

    /**
     * 删除
     *
     * @param deleteForm
     */
    void delete(DeleteForm deleteForm);

    /**
     * 更新
     *
     * @param floor
     * @return
     */
    int update(BuildingFloor floor);

    /**
     * 通过id获取楼层信息
     *
     * @param id
     * @return
     */
    BuildingFloor get(String id);

    /**
     * 查询楼层集合
     *
     * @param buildId
     * @return
     */
    List<BuildingFloor> list(String buildId);
}
