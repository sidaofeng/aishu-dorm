package com.waken.dorm.service.dorm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.waken.dorm.common.entity.dorm.DormScore;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.ListDormScoreForm;
import com.waken.dorm.common.view.dorm.DormScoreView;

import java.util.List;

/**
 * @Author zhaoRong
 * @Date 2019/3/31 19:25
 **/
public interface DormScoreService extends IService<DormScore> {
    /**
     * 新增
     *
     * @param score
     * @return
     */
    int insert(DormScore score);

    /**
     * 删除
     *
     * @param deleteForm
     */
    void delete(DeleteForm deleteForm);

    /**
     * 更新
     *
     * @param score
     * @return
     */
    int update(DormScore score);

    /**
     * 通过id获取评分信息
     *
     * @param id
     * @return
     */
    DormScore get(String id);

    /**
     * 分页查询宿舍评分
     *
     * @param listDormScoreForm
     * @return
     */
    IPage<DormScoreView> findPage(ListDormScoreForm listDormScoreForm);

    /**
     * 批量导入宿舍评分记录（excel）
     *
     * @param dormScoreList
     */
    void batchInsert(List<DormScore> dormScoreList);
}
