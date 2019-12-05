package com.waken.dorm.service.basic;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormForm;
import com.waken.dorm.common.view.dorm.AppDormView;
import com.waken.dorm.common.view.dorm.DormView;

import java.util.List;

public interface DormService extends IService<Dorm> {
    /**
     * 新增
     * @param dorm
     * @return
     */
    int insert(Dorm dorm);

    /**
     * 删除
     * @param deleteForm
     */
    void delete(DeleteForm deleteForm);

    /**
     * 更新
     *
     * @param dorm
     * @return
     */
    int update(Dorm dorm);

    /**
     * 通过id获取宿舍信息
     *
     * @param id
     * @return
     */
    Dorm get(String id);

    /**
     * 分页查询宿舍信息
     *
     * @param dormForm
     * @return
     */
    IPage<DormView> page(DormForm dormForm);


    /**
     * app端查询宿舍视图
     *
     * @param studentId
     * @return
     */
    AppDormView queryAppDormView(String studentId);

    List<Dorm> listByFloor(String floorId);
}
