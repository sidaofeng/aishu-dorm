package com.waken.dorm.serviceImpl.dorm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dorm.DormBuilding;
import com.waken.dorm.common.entity.dorm.DormBuildingExample;
import com.waken.dorm.common.entity.dorm.DormRule;
import com.waken.dorm.common.entity.dorm.DormRuleExample;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.DormException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormRuleForm;
import com.waken.dorm.common.form.dorm.EditDormRuleForm;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.dorm.DormRuleView;
import com.waken.dorm.dao.dorm.DormRuleMapper;
import com.waken.dorm.service.dorm.DormRuleService;
import com.waken.dorm.service.school.SchoolClassService;
import com.waken.dorm.service.school.SchoolService;
import com.waken.dorm.serviceImpl.base.BaseServerImpl;
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
public class DormRuleServiceImpl extends BaseServerImpl implements DormRuleService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DormRuleMapper dormRuleMapper;
    @Autowired
    SchoolService schoolService;
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
        String userId = ShiroUtils.getUserId();
        Date curDate = DateUtils.getCurrentDate();
        int count = Constant.ZERO;
        DormRule dormRule = new DormRule();
        BeanMapper.copy(editRuleForm,dormRule);
        dormRule.setLastModifyTime(curDate);
        dormRule.setLastModifyUserId(userId);
        if (StringUtils.isEmpty(editRuleForm.getPkDormRuleId())){//新增
            logger.info("service: 开始进入新增宿舍规则信息");
            String pkDormRuleId = UUIDUtils.getPkUUID();
            if (StringUtils.isEmpty(editRuleForm.getSchoolId())){
                String schoolId = schoolService.getSchoolIdByUserId(userId);
                dormRule.setSchoolId(schoolId);
            }else {
                dormRule.setSchoolId(editRuleForm.getSchoolId());
            }
            dormRule.setPkDormRuleId(pkDormRuleId);
            dormRule.setStatus(CodeEnum.ENABLE.getCode());
            dormRule.setCreateTime(curDate);
            dormRule.setCreateUserId(userId);
            count = dormRuleMapper.insertSelective(dormRule);
            if (count == Constant.ZERO){
                throw new DormException("新增个数为 0 条");
            }
            return dormRule;
        }else {//更新宿舍规则信息
            logger.info("service: 开始进入更新宿舍规则信息");
            dormRuleMapper.updateByPrimaryKeySelective(dormRule);
            return dormRuleMapper.selectByPrimaryKey(editRuleForm.getPkDormRuleId());
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
        List<String> dormRuleIds = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count = Constant.ZERO;
        if (CodeEnum.YES.getCode() == delStatus){ // 物理删除
            List<DormRule> dormRuleList = dormRuleMapper.selectByIds(dormRuleIds);
            StringBuffer sb = new StringBuffer();
            for (DormRule dormRule : dormRuleList){
                if (CodeEnum.ENABLE.getCode() == dormRule.getStatus()){
                    sb.append(dormRule.getRuleName());
                }
            }
            if (StringUtils.isNotEmpty(sb.toString())){
                throw new DormException("以下宿舍规则处于生效中："+sb.toString());
            }else {//删除宿舍
                DormRuleExample example = new DormRuleExample();
                DormRuleExample.Criteria criteria = example.createCriteria();
                criteria.andPkDormRuleIdIn(dormRuleIds);
                count = dormRuleMapper.deleteByExample(example);
                if (count == Constant.ZERO){
                    throw new DormException("删除宿舍规则个数为 0 条");
                }
            }

        }else if(CodeEnum.NO.getCode() == delStatus){
            count = dormRuleMapper.batchUpdateStatus(getToUpdateStatusMap(dormRuleIds,CodeEnum.DELETE.getCode()));
            if (count == Constant.ZERO){
                throw new DormException("状态删除失败");
            }
        }else {
            throw new DormException("删除状态码错误！");
        }
    }

    /**
     * 分页查询宿舍规则信息
     * @param dormRuleForm
     * @return
     */
    @Override
    public PageInfo<DormRuleView> listDormRules(DormRuleForm dormRuleForm) {
        logger.info("service: 分页查询宿舍规则信息开始");
        if (dormRuleForm.getTerminal() == CodeEnum.WEB.getCode()){
            if (StringUtils.isEmpty(dormRuleForm.getSchoolId())) {
                String userId = ShiroUtils.getUserId();
                String schoolId = schoolService.getSchoolIdByUserId(userId);
                dormRuleForm.setSchoolId(schoolId);
            }
        }
        if (dormRuleForm.getStartTime() != null) {
            dormRuleForm.setStartTime(DateUtils.formatDate2DateTimeStart(dormRuleForm.getStartTime()));
        }
        if (dormRuleForm.getEndTime() != null) {
            dormRuleForm.setEndTime(DateUtils.formatDate2DateTimeEnd(dormRuleForm.getEndTime()));
        }
        PageHelper.startPage(dormRuleForm.getPageNum(),dormRuleForm.getPageSize());
        List<DormRuleView> dormRuleList = dormRuleMapper.listDormRules(dormRuleForm);
        return new PageInfo<DormRuleView>(dormRuleList);
    }

    /**
     * 编辑宿舍规则时 验证
     * @param editRuleForm
     */
    private void editRuleValidate(EditDormRuleForm editRuleForm){
        if (StringUtils.isEmpty(editRuleForm.getPkDormRuleId())){//新增验证
            StringBuffer sb = new StringBuffer();
            if (StringUtils.isEmpty(editRuleForm.getRuleName())){
                sb.append("规则名称为空");
            }
            if (StringUtils.isEmpty(editRuleForm.getRuleDesc())){
                sb.append("规则描述为空");
            }
            if (StringUtils.isNotEmpty(sb.toString())){
                throw new DormException("验证失败："+sb.toString());
            }
            DormRuleExample example = new DormRuleExample();
            DormRuleExample.Criteria criteria = example.createCriteria();
            criteria.andRuleNameEqualTo(editRuleForm.getRuleName());
            List<DormRule> dormRules = dormRuleMapper.selectByExample(example);
            if (!dormRules.isEmpty()){
                throw new DormException("已存在相同名称的规则名！");
            }
        }else {//修改验证
            DormRule dormRule = dormRuleMapper.selectByPrimaryKey(editRuleForm.getPkDormRuleId());
            if (dormRule == null){
                throw new DormException("宿舍规则id不正确");
            }
            if (StringUtils.isNotEmpty(editRuleForm.getRuleName())){
                DormRuleExample example = new DormRuleExample();
                DormRuleExample.Criteria criteria = example.createCriteria();
                criteria.andRuleNameEqualTo(editRuleForm.getRuleName());
                List<DormRule> dormRules = dormRuleMapper.selectByExample(example);
                if (!dormRules.isEmpty()){
                    if (!StringUtils.equals(dormRules.get(Constant.ZERO).getPkDormRuleId(),editRuleForm.getPkDormRuleId())){
                        throw new DormException("已存在相同名称的规则名！");
                    }
                }
            }
        }
    }
}
