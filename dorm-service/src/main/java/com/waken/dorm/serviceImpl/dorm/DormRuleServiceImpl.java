package com.waken.dorm.serviceImpl.dorm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dorm.DormRule;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormRuleForm;
import com.waken.dorm.common.form.dorm.EditDormRuleForm;
import com.waken.dorm.common.manager.UserManager;
import com.waken.dorm.common.utils.Assert;
import com.waken.dorm.common.utils.BeanMapper;
import com.waken.dorm.common.utils.DormUtil;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.view.dorm.DormRuleView;
import com.waken.dorm.dao.dorm.DormRuleMapper;
import com.waken.dorm.handle.DataHandle;
import com.waken.dorm.service.dorm.DormRuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName DormRuleServiceImpl
 * @Description DormRuleServiceImpl
 * @Author zhaoRong
 * @Date 2019/4/2 10:42
 **/
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DormRuleServiceImpl implements DormRuleService {
    private final DormRuleMapper dormRuleMapper;

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
        int count;
        DormRule dormRule = new DormRule();
        BeanMapper.copy(editRuleForm, dormRule);
        if (StringUtils.isEmpty(editRuleForm.getId())) {//新增
            log.info("service: 开始进入新增宿舍规则信息");
            dormRule.setStatus(CodeEnum.ENABLE.getCode());
            count = dormRuleMapper.insert(dormRule);
            Assert.isFalse(count == Constant.ZERO);
            return dormRule;
        } else {//更新宿舍规则信息
            log.info("service: 开始进入更新宿舍规则信息");
            dormRuleMapper.updateById(dormRule);
            return dormRuleMapper.selectById(editRuleForm.getId());
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
        log.info("service: 删除宿舍规则开始");
        List<String> ids = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count;
        if (CodeEnum.YES.getCode() == delStatus) { // 物理删除
            List<DormRule> dormRuleList = dormRuleMapper.selectBatchIds(ids);
            StringBuffer sb = new StringBuffer();
            for (DormRule dormRule : dormRuleList) {
                if (CodeEnum.ENABLE.getCode() == dormRule.getStatus()) {
                    sb.append(dormRule.getName());
                }
            }
            Assert.isNull(sb.toString(),"以下宿舍规则处于生效中：" + sb.toString());
            //删除宿舍规则
            count = dormRuleMapper.deleteBatchIds(ids);
            Assert.isFalse(count == Constant.ZERO);

        } else if (CodeEnum.NO.getCode() == delStatus) {
            count = dormRuleMapper.batchUpdateStatus(DormUtil.getToUpdateStatusMap(ids,UserManager.getCurrentUserId()));
            Assert.isFalse(count == Constant.ZERO);
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
    public IPage<DormRuleView> listDormRules(DormRuleForm dormRuleForm) {
        log.info("service: 分页查询宿舍规则信息开始");
        return dormRuleMapper.listDormRules(DataHandle.getWrapperPage(dormRuleForm),dormRuleForm);
    }

    /**
     * 编辑宿舍规则时 验证
     *
     * @param editForm
     */
    private void editRuleValidate(EditDormRuleForm editForm) {
        if (StringUtils.isEmpty(editForm.getId())) {//新增验证
            Assert.notNull(editForm.getName());
            Assert.notNull(editForm.getDescription());
            DormRule dormRule = dormRuleMapper.selectOne(new LambdaQueryWrapper<DormRule>()
                    .eq(DormRule::getName, editForm.getName())
            );
            Assert.isNull(dormRule,"已存在相同名称的规则名！");
        } else {//修改验证
            Assert.notNull(dormRuleMapper.selectById(editForm.getId()), "参数错误！");
            if (StringUtils.isNotEmpty(editForm.getName())) {
                DormRule dormRule = dormRuleMapper.selectOne(new LambdaQueryWrapper<DormRule>()
                        .eq(DormRule::getName, editForm.getName())
                );
                if (null != dormRule) {
                    if (!StringUtils.equals(dormRule.getId(), editForm.getId())) {
                        throw new ServerException("已存在相同名称的规则名！");
                    }
                }
            }
        }
    }
}
