package com.waken.dorm.dao.dorm;

import com.waken.dorm.common.entity.dorm.DormRepair;
import com.waken.dorm.common.entity.dorm.DormRule;
import com.waken.dorm.common.entity.dorm.DormRuleExample;
import java.util.List;
import java.util.Map;

import com.waken.dorm.common.form.dorm.DormRuleForm;
import com.waken.dorm.common.view.dorm.DormRuleView;
import org.apache.ibatis.annotations.Param;

public interface DormRuleMapper {
    int batchUpdateStatus(Map<String,Object> param);

    List<DormRuleView> listDormRules(DormRuleForm dormRuleForm);

    List<DormRule> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询

    int countByExample(DormRuleExample example);

    int deleteByExample(DormRuleExample example);

    int deleteByPrimaryKey(String pkDormRuleId);

    int insert(DormRule record);

    int insertSelective(DormRule record);

    List<DormRule> selectByExample(DormRuleExample example);

    DormRule selectByPrimaryKey(String pkDormRuleId);

    int updateByExampleSelective(@Param("record") DormRule record, @Param("example") DormRuleExample example);

    int updateByExample(@Param("record") DormRule record, @Param("example") DormRuleExample example);

    int updateByPrimaryKeySelective(DormRule record);

    int updateByPrimaryKey(DormRule record);
}