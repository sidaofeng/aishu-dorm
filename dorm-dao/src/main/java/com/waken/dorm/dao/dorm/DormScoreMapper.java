package com.waken.dorm.dao.dorm;

import com.waken.dorm.common.entity.dorm.DormScore;

import java.util.List;
import java.util.Map;

import com.waken.dorm.common.entity.dorm.DormScoreExample;
import com.waken.dorm.common.form.dorm.ListDormScoreForm;
import com.waken.dorm.common.view.dorm.AppDormScoreView;
import com.waken.dorm.common.view.dorm.DormScoreView;
import org.apache.ibatis.annotations.Param;

public interface DormScoreMapper {
    int batchUpdateStatus(Map<String,Object> param);

    List<DormScoreView> listDormScores(ListDormScoreForm listDormScoreForm);

    List<AppDormScoreView> appListDormScoreView(ListDormScoreForm listDormScoreForm);

    List<DormScore> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询

    int batchAddDormScore(List<DormScore> dormScoreList);

    int countByExample(DormScoreExample example);

    int deleteByExample(DormScoreExample example);

    int deleteByPrimaryKey(String pkDormScoreId);

    int insert(DormScore record);

    int insertSelective(DormScore record);

    List<DormScore> selectByExample(DormScoreExample example);

    DormScore selectByPrimaryKey(String pkDormScoreId);

    int updateByExampleSelective(@Param("record") DormScore record, @Param("example") DormScoreExample example);

    int updateByExample(@Param("record") DormScore record, @Param("example") DormScoreExample example);

    int updateByPrimaryKeySelective(DormScore record);

    int updateByPrimaryKey(DormScore record);
}