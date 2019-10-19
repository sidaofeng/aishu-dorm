package com.waken.dorm.service.dorm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.waken.dorm.common.entity.dorm.DormRule;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormRuleForm;
import com.waken.dorm.common.form.dorm.EditDormRuleForm;
import com.waken.dorm.common.view.dorm.DormRuleView;

/**
 * @Description DormRuleService
 * @Author zhaoRong
 * @Date 2019/4/2 10:33
 **/
public interface DormRuleService {
    /**
     * (修改/保存)宿舍规则
     *
     * @param editDormRuleForm
     * @return
     */
    DormRule saveDormRule(EditDormRuleForm editDormRuleForm);

    /**
     * 删除宿舍规则
     *
     * @param deleteForm
     */
    void deleteDormRule(DeleteForm deleteForm);

    /**
     * 分页查询宿舍规则
     *
     * @param dormRuleForm
     * @return
     */
    IPage<DormRuleView> listDormRules(DormRuleForm dormRuleForm);
}
