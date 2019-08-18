package com.waken.dorm.dao.dorm;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.waken.dorm.common.entity.dorm.DormRule;
import com.waken.dorm.common.form.dorm.DormRuleForm;
import com.waken.dorm.common.view.dorm.DormRuleView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DormRuleMapper extends BaseMapper<DormRule> {
    int batchUpdateStatus(Map<String, Object> param);

    List<DormRuleView> listDormRules(DormRuleForm dormRuleForm);

    List<DormRule> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询
}