package com.waken.dorm.service.dorm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.waken.dorm.common.entity.dorm.DormViolation;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormViolationForm;
import com.waken.dorm.common.view.dorm.DormViolationView;

/**
 * @ClassName DormViolationService
 * @Description DormViolationService
 * @Author zhaoRong
 * @Date 2019/4/2 12:48
 **/
public interface DormViolationService extends IService<DormViolation> {
    /**
     * 新增
     *
     * @param violation
     * @return
     */
    int insert(DormViolation violation);

    /**
     * 删除
     *
     * @param deleteForm
     */
    void delete(DeleteForm deleteForm);

    /**
     * 更新
     *
     * @param violation
     * @return
     */
    int update(DormViolation violation);

    /**
     * 通过id获取楼层信息
     *
     * @param id
     * @return
     */
    DormViolation get(String id);

    /**
     * 分页查询宿舍违规记录
     *
     * @param dormViolationForm
     * @return
     */
    IPage<DormViolationView> page(DormViolationForm dormViolationForm);
}
