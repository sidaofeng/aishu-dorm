package com.waken.dorm.serviceImpl.dorm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.entity.dorm.DormExample;
import com.waken.dorm.common.entity.dorm.DormViolation;
import com.waken.dorm.common.entity.dorm.DormViolationExample;
import com.waken.dorm.common.entity.student.Student;
import com.waken.dorm.common.entity.student.StudentExample;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.DormException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormViolationForm;
import com.waken.dorm.common.form.dorm.DormViolationForm;
import com.waken.dorm.common.form.dorm.UpdateDormViolationForm;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.dorm.AppDormViolationView;
import com.waken.dorm.common.view.dorm.DormViolationView;
import com.waken.dorm.common.view.user.UserView;
import com.waken.dorm.dao.dorm.DormMapper;
import com.waken.dorm.dao.dorm.DormViolationMapper;
import com.waken.dorm.dao.student.StudentMapper;
import com.waken.dorm.service.dorm.DormViolationService;
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
 * @ClassName DormViolationServiceImpl
 * @Description DormViolationServiceImpl
 * @Author zhaoRong
 * @Date 2019/4/2 13:00
 **/
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@Service
public class DormViolationServiceImpl extends BaseServerImpl implements DormViolationService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DormViolationMapper dormViolationMapper;
    @Autowired
    DormMapper dormMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    SchoolService schoolService;
    /**
     * 新增违规记录
     *
     * @param addDormViolationForm
     * @return
     */
    @Transactional
    @Override
    public DormViolation addDormViolation(AddDormViolationForm addDormViolationForm) {
        this.addViolationValidate(addDormViolationForm);//验证合法性
        String dormId = this.validateDorm(addDormViolationForm.getDormNum());//验证宿舍是否存在
        String studentId = this.validateStudent(addDormViolationForm.getStudentNum());//验证学生是否存在
        String pkDormViolationId = UUIDUtils.getPkUUID();
        String userId = ShiroUtils.getUserId();
        Date curDate = DateUtils.getCurrentDate();
        DormViolation dormViolation = new DormViolation();
        BeanMapper.copy(addDormViolationForm,dormViolation);
        dormViolation.setPkDormViolationId(pkDormViolationId);
        dormViolation.setDormId(dormId);
        dormViolation.setStudentId(studentId);
        dormViolation.setCreateTime(curDate);
        dormViolation.setCreateUserId(userId);
        dormViolation.setLastModifyTime(curDate);
        dormViolation.setLastModifyUserId(userId);
        int count = Constant.ZERO;
        count = dormViolationMapper.insertSelective(dormViolation);
        if (count == Constant.ZERO){
            throw new DormException("新增个数为 0 条");
        }
        return dormViolation;
    }

    /**
     * 删除违规记录
     *
     * @param deleteForm
     */
    @Transactional
    @Override
    public void deleteDormViolation(DeleteForm deleteForm) {
        logger.info("service: 删除违规记录开始");
        List<String> pkDormViolationIds = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count = Constant.ZERO;
        if (CodeEnum.YES.getCode() == delStatus){ // 物理删除
            List<DormViolation> dormViolations = dormViolationMapper.selectByIds(pkDormViolationIds);
            StringBuffer sb = new StringBuffer();
            for (DormViolation dormViolation : dormViolations){
                if (CodeEnum.UNCOMPLETE.getCode() == dormViolation.getStatus()){
                    sb.append(dormViolation.getPkDormViolationId());
                }
            }
            if (StringUtils.isNotEmpty(sb.toString())){
                throw new DormException("以下违规记录处于未处理状态中："+sb.toString());
            }else {//删除宿舍
                DormViolationExample example = new DormViolationExample();
                DormViolationExample.Criteria criteria = example.createCriteria();
                criteria.andPkDormViolationIdIn(pkDormViolationIds);
                count = dormViolationMapper.deleteByExample(example);
                if (count == Constant.ZERO){
                    throw new DormException("删除违规记录为 0 条");
                }
            }

        }else if(CodeEnum.NO.getCode() == delStatus){
            count = dormViolationMapper.batchUpdateStatus(getToUpdateStatusMap(pkDormViolationIds,CodeEnum.DELETE.getCode()));
            if (count == Constant.ZERO){
                throw new DormException("状态删除失败");
            }
        }else {
            throw new DormException("删除状态码错误！");
        }
    }

    /**
     * 分页查询宿舍违规记录
     *
     * @param dormViolationForm
     * @return
     */
    @Override
    public PageInfo<DormViolationView> listDormViolations(DormViolationForm dormViolationForm) {
        logger.info("service: 违规记录分页查询开始");
        if (StringUtils.isEmpty(dormViolationForm.getSchoolId())) {
            String userId = ShiroUtils.getUserId();
            String schoolId = schoolService.getSchoolIdByUserId(userId);
            dormViolationForm.setSchoolId(schoolId);
        }
        if (dormViolationForm.getStartTime() != null) {
            dormViolationForm.setStartTime(DateUtils.formatDate2DateTimeStart(dormViolationForm.getStartTime()));
        }
        if (dormViolationForm.getEndTime() != null) {
            dormViolationForm.setEndTime(DateUtils.formatDate2DateTimeEnd(dormViolationForm.getEndTime()));
        }
        PageHelper.startPage(dormViolationForm.getPageNum(),dormViolationForm.getPageSize());
        List<DormViolationView> dormViolationViews = dormViolationMapper.listDormViolations(dormViolationForm);
        return new PageInfo<DormViolationView>(dormViolationViews);
    }

    /**
     * 更新宿舍违规记录（提交违规结果）
     *
     * @param updateViolationForm
     * @return
     */
    @Transactional
    @Override
    public DormViolation updateDormViolation(UpdateDormViolationForm updateViolationForm) {
        this.updateViolationValidate(updateViolationForm);//验证合法性
        DormViolation dormViolation = dormViolationMapper.selectByPrimaryKey(updateViolationForm.getPkDormViolationId());
        BeanMapper.copy(updateViolationForm,dormViolation);
        String userId = ShiroUtils.getUserId();
        Date curDate = DateUtils.getCurrentDate();
        dormViolation.setLastModifyTime(curDate);
        dormViolation.setLastModifyUserId(userId);
        int count = Constant.ZERO;
        count = dormViolationMapper.updateByPrimaryKeySelective(dormViolation);
        if (count == Constant.ZERO){
            throw new DormException("修改违规记录为 0 条");
        }
        return dormViolation;
    }

    /**
     * app端分页查询宿舍违规记录
     *
     * @param dormViolationForm
     * @return
     */
    @Override
    public PageInfo<AppDormViolationView> appListDormViolations(DormViolationForm dormViolationForm) {
        logger.info("service: 违规记录分页查询开始");
        PageHelper.startPage(dormViolationForm.getPageNum(),dormViolationForm.getPageSize());
        List<AppDormViolationView> appDormViolationViews = dormViolationMapper.appListDormViolations(dormViolationForm);
        return new PageInfo<AppDormViolationView>(appDormViolationViews);
    }

    /**
     * 修改验证
     * @param updateViolationForm
     */
    private void updateViolationValidate(UpdateDormViolationForm updateViolationForm){
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isEmpty(updateViolationForm.getPkDormViolationId())){
            sb.append("违规主id为空！");
        }
        if (updateViolationForm.getStatus() == null){
            sb.append("状态为空！");
        }
        if (StringUtils.isNotEmpty(sb.toString())){
            throw new DormException("验证失败："+sb.toString());
        }
    }

    /**
     * 新增验证
     * @param addDormViolationForm
     */
    private void addViolationValidate(AddDormViolationForm addDormViolationForm){
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isEmpty(addDormViolationForm.getDormNum())){
            sb.append("宿舍号为空！");
        }
        if (StringUtils.isEmpty(addDormViolationForm.getDormRuleId())){
            sb.append("违规规则id为空");
        }
        if (addDormViolationForm.getStudentNum() == null){
            sb.append("学号为空！");
        }
        if (StringUtils.isEmpty(addDormViolationForm.getViolationReason())){
            sb.append("违规原因为空");
        }
        if (addDormViolationForm.getStatus() == null){
            sb.append("状态码为空！");
        }
        if (StringUtils.isEmpty(addDormViolationForm.getSolveResult())){
            sb.append("违规处理结果为空");
        }
        if (StringUtils.isNotEmpty(sb.toString())){
            throw new DormException("验证失败："+sb.toString());
        }
    }

    private String validateDorm(String dormNum){
        DormExample example = new DormExample();
        DormExample.Criteria criteria = example.createCriteria();
        criteria.andDormNumEqualTo(dormNum);
        List<Dorm> dorms = dormMapper.selectByExample(example);
        if (dorms.isEmpty()){
            throw new DormException("宿舍号不存在!");
        }else {
            return dorms.get(Constant.ZERO).getPkDormId();
        }
    }

    private String validateStudent(Integer studentNum){
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andStudentNumEqualTo(studentNum);
        List<Student> studentList = studentMapper.selectByExample(example);
        if (studentList.isEmpty()){
            throw new DormException("学号不存在!");
        }else {
            return studentList.get(Constant.ZERO).getPkStudentId();
        }
    }

}
