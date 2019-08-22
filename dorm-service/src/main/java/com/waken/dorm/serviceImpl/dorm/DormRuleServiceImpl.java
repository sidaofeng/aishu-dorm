package com.waken.dorm.serviceImpl.dorm;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dorm.DormRule;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormRuleForm;
import com.waken.dorm.common.form.dorm.EditDormRuleForm;
import com.waken.dorm.common.sequence.UUIDSequence;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.dorm.DormRuleView;
import com.waken.dorm.dao.dorm.DormRuleMapper;
import com.waken.dorm.manager.UserManager;
import com.waken.dorm.service.dorm.DormRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName DormRuleServiceImpl
 * @Description DormRuleServiceImpl
 * @Author zhaoRong
 * @Date 2019/4/2 10:42
 **/
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@Service
public class DormRuleServiceImpl implements DormRuleService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DormRuleMapper dormRuleMapper;

    /**
     * 保存/修改宿舍规则信息
     *
     * @param editRuleForm
     * @return
     */
    @Transactional
    @Override
    public DormRule saveDormRule(EditDormRuleForm editRuleForm) {
        this.editRuleValidate(editRuleForm);
        String userId = UserManager.getCurrentUserId();
        Date curDate = DateUtils.getCurrentDate();
        int count;
        DormRule dormRule = new DormRule();
        BeanMapper.copy(editRuleForm, dormRule);
        dormRule.setLastModifyTime(curDate);
        dormRule.setLastModifyUserId(userId);
        if (StringUtils.isEmpty(editRuleForm.getPkDormRuleId())) {//新增
            logger.info("service: 开始进入新增宿舍规则信息");
            String pkDormRuleId = UUIDSequence.next();
            dormRule.setPkDormRuleId(pkDormRuleId);
            dormRule.setStatus(CodeEnum.ENABLE.getCode());
            dormRule.setCreateTime(curDate);
            dormRule.setCreateUserId(userId);
            count = dormRuleMapper.insert(dormRule);
            if (count == Constant.ZERO) {
                throw new ServerException("新增个数为 0 条");
            }
            return dormRule;
        } else {//更新宿舍规则信息
            logger.info("service: 开始进入更新宿舍规则信息");
            dormRuleMapper.updateById(dormRule);
            return dormRuleMapper.selectById(editRuleForm.getPkDormRuleId());
        }
    }

    /**
     * 删除宿舍规则
     *
     * @param deleteForm
     */
    @Transactional
    @Override
    public void deleteDormRule(DeleteForm deleteForm) {
        logger.info("service: 删除宿舍规则开始");
        List<String> ids = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count;
        if (CodeEnum.YES.getCode() == delStatus) { // 物理删除
            List<DormRule> dormRuleList = dormRuleMapper.selectByIds(ids);
            StringBuffer sb = new StringBuffer();
            for (DormRule dormRule : dormRuleList) {
                if (CodeEnum.ENABLE.getCode() == dormRule.getStatus()) {
                    sb.append(dormRule.getRuleName());
                }
            }
            if (StringUtils.isNotEmpty(sb.toString())) {
                throw new ServerException("以下宿舍规则处于生效中：" + sb.toString());
            } else {//删除宿舍
                count = dormRuleMapper.deleteBatchIds(ids);
                if (count == Constant.ZERO) {
                    throw new ServerException("删除宿舍规则个数为 0 条");
                }
            }

        } else if (CodeEnum.NO.getCode() == delStatus) {
            count = dormRuleMapper.batchUpdateStatus(DormUtil.getToUpdateStatusMap(ids,UserManager.getCurrentUserId()));
            if (count == Constant.ZERO) {
                throw new ServerException("状态删除失败");
            }
        } else {
            throw new ServerException("删除状态码错误！");
        }
    }

    /**
     * 分页查询宿舍规则信息
     *
     * @param dormRuleForm
     * @return
     */
    @Override
    public PageInfo<DormRuleView> listDormRules(DormRuleForm dormRuleForm) {
        logger.info("service: 分页查询宿舍规则信息开始");
        if (dormRuleForm.getStartTime() != null) {
            dormRuleForm.setStartTime(DateUtils.formatDate2DateTimeStart(dormRuleForm.getStartTime()));
        }
        if (dormRuleForm.getEndTime() != null) {
            dormRuleForm.setEndTime(DateUtils.formatDate2DateTimeEnd(dormRuleForm.getEndTime()));
        }
        PageHelper.startPage(dormRuleForm.getPageNum(), dormRuleForm.getPageSize());
        List<DormRuleView> dormRuleList = dormRuleMapper.listDormRules(dormRuleForm);
        return new PageInfo<>(dormRuleList);
    }

    /**
     * 编辑宿舍规则时 验证
     *
     * @param editForm
     */
    private void editRuleValidate(EditDormRuleForm editForm) {
        if (StringUtils.isEmpty(editForm.getPkDormRuleId())) {//新增验证
            Assert.notNull(editForm.getRuleName());
            Assert.notNull(editForm.getRuleDesc());
            List<DormRule> dormRules = dormRuleMapper.selectList(new EntityWrapper<DormRule>()
                    .eq("rule_name", editForm.getRuleName())
            );
            if (!dormRules.isEmpty()) {
                throw new ServerException("已存在相同名称的规则名！");
            }
        } else {//修改验证
            DormRule dormRule = dormRuleMapper.selectById(editForm.getPkDormRuleId());
            Assert.notNull(dormRule,"参数错误！");
            if (StringUtils.isNotEmpty(editForm.getRuleName())) {
                List<DormRule> dormRules = dormRuleMapper.selectList(new EntityWrapper<DormRule>()
                        .eq("rule_name", editForm.getRuleName())
                );
                if (!dormRules.isEmpty()) {
                    if (!StringUtils.equals(dormRules.get(Constant.ZERO).getPkDormRuleId(), editForm.getPkDormRuleId())) {
                        throw new ServerException("已存在相同名称的规则名！");
                    }
                }
            }
        }
    }
}
