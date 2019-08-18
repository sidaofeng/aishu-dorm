package com.waken.dorm.dao.dorm;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.waken.dorm.common.entity.dorm.DormScore;
import com.waken.dorm.common.form.dorm.ListDormScoreForm;
import com.waken.dorm.common.view.dorm.AppDormScoreView;
import com.waken.dorm.common.view.dorm.DormScoreView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DormScoreMapper extends BaseMapper<DormScore> {
    int batchUpdateStatus(Map<String, Object> param);

    List<DormScoreView> listDormScores(ListDormScoreForm listDormScoreForm);

    List<AppDormScoreView> appListDormScoreView(ListDormScoreForm listDormScoreForm);

    List<DormScore> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询

    int batchAddDormScore(List<DormScore> dormScoreList);
}