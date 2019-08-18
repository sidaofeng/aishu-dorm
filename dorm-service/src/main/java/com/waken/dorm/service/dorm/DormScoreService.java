package com.waken.dorm.service.dorm;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.entity.dorm.DormScore;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormScoreForm;
import com.waken.dorm.common.form.dorm.ListDormScoreForm;
import com.waken.dorm.common.view.dorm.AppDormScoreView;
import com.waken.dorm.common.view.dorm.DormScoreView;

import java.util.List;

/**
 * @Author zhaoRong
 * @Date 2019/3/31 19:25
 **/
public interface DormScoreService {
    /**
     * 批量导入宿舍评分记录（excel）
     *
     * @param dormScoreList
     */
    public void batchAddDormScore(List<DormScore> dormScoreList);

    /**
     * 删除评分记录
     *
     * @param deleteForm
     */
    public void deleteDormScore(DeleteForm deleteForm);

    /**
     * app端通过学生id查询对应宿舍的评分记录
     *
     * @param listDormScoreForm
     * @return
     */
    public PageInfo<AppDormScoreView> appListDormScoreViews(ListDormScoreForm listDormScoreForm);

    /**
     * 分页查询宿舍评分
     *
     * @param listDormScoreForm
     * @return
     */
    public PageInfo<DormScoreView> listDormScores(ListDormScoreForm listDormScoreForm);

    /**
     * 修改评分
     *
     * @param dormScoreForm
     * @return
     */
    public DormScore updateDormScore(DormScoreForm dormScoreForm);
}
