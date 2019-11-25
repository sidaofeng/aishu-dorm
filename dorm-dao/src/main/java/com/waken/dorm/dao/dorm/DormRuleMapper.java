package com.waken.dorm.dao.dorm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waken.dorm.common.entity.dorm.DormRule;
import com.waken.dorm.common.form.dorm.DormRuleForm;
import com.waken.dorm.common.view.dorm.DormRuleView;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface DormRuleMapper extends BaseMapper<DormRule> {
    int batchUpdateStatus(Map<String, Object> param);

    IPage<DormRuleView> listDormRules(Page page,@Param("form") DormRuleForm dormRuleForm);
}