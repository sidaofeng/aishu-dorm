package com.waken.dorm.service.dorm;

import com.github.pagehelper.PageInfo;
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
     * @param editDormRuleForm
     * @return
     */
    public DormRule saveDormRule(EditDormRuleForm editDormRuleForm);

    /**
     * 删除宿舍规则
     * @param deleteForm
     */
    public void deleteDormRule(DeleteForm deleteForm);

    /**
     * 分页查询宿舍规则
     * @param dormRuleForm
     * @return
     */
    public PageInfo<DormRuleView> listDormRules(DormRuleForm dormRuleForm);
}
