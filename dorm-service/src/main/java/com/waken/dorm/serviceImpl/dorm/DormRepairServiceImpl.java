package com.waken.dorm.serviceImpl.dorm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dorm.*;
import com.waken.dorm.common.entity.student.Student;
import com.waken.dorm.common.entity.student.StudentExample;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.DormException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormRepairForm;
import com.waken.dorm.common.form.dorm.DormRepairForm;
import com.waken.dorm.common.form.dorm.UpdateRepairForm;
import com.waken.dorm.common.utils.DateUtils;
import com.waken.dorm.common.utils.ShiroUtils;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.utils.UUIDUtils;
import com.waken.dorm.common.view.dorm.DormRepairView;
import com.waken.dorm.dao.dorm.DormMapper;
import com.waken.dorm.dao.dorm.DormRepairMapper;
import com.waken.dorm.dao.student.StudentMapper;
import com.waken.dorm.service.dorm.DormRepairService;
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
 * @ClassName DormRepairServiceImpl
 * @Description 宿舍维修业务层
 * @Author zhaoRong
 * @Date 2019/4/1 21:14
 **/
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@Service
public class DormRepairServiceImpl extends BaseServerImpl implements DormRepairService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DormRepairMapper dormRepairMapper;
    @Autowired
    DormMapper dormMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    SchoolService schoolService;
    /**
     * 新增维修记录
     *
     * @param addDormRepairForm
     * @return
     */
    @Transactional
    @Override
    public DormRepair addDormRepair(AddDormRepairForm addDormRepairForm) {
        logger.info("service : 开始进入新增维修记录");
        this.addRepairValidate(addDormRepairForm);//验证合法性
        String studentId = null;
        String dormId = this.validateDorm(addDormRepairForm.getDormNum());//验证宿舍是否存在
        String pkDormRepairId = UUIDUtils.getPkUUID();
        Date curDate = DateUtils.getCurrentDate();
        DormRepair dormRepair = new DormRepair();
        if (addDormRepairForm.getTerminal() == CodeEnum.WEB.getCode()){//web端请求
            String userId = ShiroUtils.getUserId();
            studentId = this.validateStudent(addDormRepairForm.getStudentNum());//验证学生是否存在
            dormRepair.setCreateUserId(userId);
            dormRepair.setLastModifyUserId(userId);
        }else {//app端请求
            studentId = addDormRepairForm.getStudentId();
            dormRepair.setCreateUserId(studentId);
            dormRepair.setLastModifyUserId(studentId);
        }
        dormRepair.setPkDormRepairId(pkDormRepairId);
        dormRepair.setDormId(dormId);
        dormRepair.setRepairType(addDormRepairForm.getRepairType());
        dormRepair.setRepairDesc(addDormRepairForm.getRepairDesc());
        dormRepair.setRepairImgUrl(addDormRepairForm.getRepairImgUrl());
        dormRepair.setStudentId(studentId);
        dormRepair.setStudentMobile(addDormRepairForm.getStudentMobile());
        dormRepair.setStatus(CodeEnum.REPAIRING.getCode());
        dormRepair.setCreateTime(curDate);
        dormRepair.setLastModifyTime(curDate);
        if (StringUtils.isEmpty(addDormRepairForm.getMemo())){
            dormRepair.setMemo(addDormRepairForm.getMemo());
        }
        int count = Constant.ZERO;
        count = dormRepairMapper.insertSelective(dormRepair);
        if (count == Constant.ZERO){
            throw new DormException("新增个数为 0 条");
        }
        return dormRepair;
    }

    /**
     * 删除维修记录
     *
     * @param deleteForm
     */
    @Transactional
    @Override
    public void deleteDormRepair(DeleteForm deleteForm) {
        logger.info("service: 删除维修记录开始");
        List<String> pkDormRepairIds = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count = Constant.ZERO;
        if (CodeEnum.YES.getCode() == delStatus){ // 物理删除
            List<DormRepair> dormRepairs = dormRepairMapper.selectByIds(pkDormRepairIds);
            StringBuffer sb = new StringBuffer();
            for (DormRepair dormRepair : dormRepairs){
                if (CodeEnum.REPAIRING.getCode() == dormRepair.getStatus()){
                    sb.append(dormRepair.getPkDormRepairId());
                }
            }
            if (StringUtils.isNotEmpty(sb.toString())){
                throw new DormException("以下维修记录处于正在维修状态中："+sb.toString());
            }else {//删除宿舍
                DormRepairExample example = new DormRepairExample();
                DormRepairExample.Criteria criteria = example.createCriteria();
                criteria.andPkDormRepairIdIn(pkDormRepairIds);
                count = dormRepairMapper.deleteByExample(example);
                if (count == Constant.ZERO){
                    throw new DormException("删除维修记录为 0 条");
                }
            }

        }else if(CodeEnum.NO.getCode() == delStatus){
            count = dormRepairMapper.batchUpdateStatus(getToUpdateStatusMap(pkDormRepairIds,CodeEnum.DELETE.getCode()));
            if (count == Constant.ZERO){
                throw new DormException("状态删除失败");
            }
        }else {
            throw new DormException("删除状态码错误！");
        }
    }

    /**
     * 分页查询宿舍维修记录
     *
     * @param dormRepairForm
     * @return
     */
    @Override
    public PageInfo<DormRepairView> listDormRepairs(DormRepairForm dormRepairForm) {
        logger.info("service: 分页查询宿舍维修信息开始");
        if (dormRepairForm.getTerminal() == CodeEnum.WEB.getCode()){
            if (StringUtils.isEmpty(dormRepairForm.getSchoolId())) {
                String userId = ShiroUtils.getUserId();
                String schoolId = schoolService.getSchoolIdByUserId(userId);
                dormRepairForm.setSchoolId(schoolId);
            }
        }
        if (dormRepairForm.getStartTime() != null) {
            dormRepairForm.setStartTime(DateUtils.formatDate2DateTimeStart(dormRepairForm.getStartTime()));
        }
        if (dormRepairForm.getEndTime() != null) {
            dormRepairForm.setEndTime(DateUtils.formatDate2DateTimeEnd(dormRepairForm.getEndTime()));
        }
        PageHelper.startPage(dormRepairForm.getPageNum(),dormRepairForm.getPageSize());
        List<DormRepairView> dormRepairs = dormRepairMapper.listDormRepairs(dormRepairForm);
        return new PageInfo<DormRepairView>(dormRepairs);
    }

    /**
     * 更新宿舍维修记录（提交维修结果）
     *
     * @param updateRepairForm
     * @return
     */
    @Transactional
    @Override
    public DormRepair updateDormRepair(UpdateRepairForm updateRepairForm) {
        this.updateRepairValidate(updateRepairForm);//验证合法性
        DormRepair dormRepair = dormRepairMapper.selectByPrimaryKey(updateRepairForm.getPkDormRepairId());
        dormRepair.setStatus(updateRepairForm.getStatus());
        if (updateRepairForm.getRepairCost() != null){
            dormRepair.setRepairCost(updateRepairForm.getRepairCost());
        }
        if (StringUtils.isNotEmpty(updateRepairForm.getRepairBillUrl())){
            dormRepair.setRepairBillUrl(updateRepairForm.getRepairBillUrl());
        }
        if (StringUtils.isNotEmpty(updateRepairForm.getMemo())){
            dormRepair.setMemo(updateRepairForm.getMemo());
        }
        String userId = ShiroUtils.getUserId();
        Date curDate = DateUtils.getCurrentDate();
        dormRepair.setLastModifyTime(curDate);
        dormRepair.setLastModifyUserId(userId);
        int count = Constant.ZERO;
        count = dormRepairMapper.updateByPrimaryKeySelective(dormRepair);
        if (count == Constant.ZERO){
            throw new DormException("修改维修记录为 0 条");
        }
        return dormRepair;
    }

    /**
     * 修改验证
     * @param updateRepairForm
     */
    private void updateRepairValidate(UpdateRepairForm updateRepairForm){
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isEmpty(updateRepairForm.getPkDormRepairId())){
            sb.append("维修主id为空！");
        }
        if (updateRepairForm.getStatus() == null){
            sb.append("状态为空！");
        }
        if (StringUtils.isNotEmpty(sb.toString())){
            throw new DormException("验证失败："+sb.toString());
        }
    }

    /**
     * 新增验证
     * @param addDormRepairForm
     */
    private void addRepairValidate(AddDormRepairForm addDormRepairForm){
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isEmpty(addDormRepairForm.getDormNum())){
            sb.append("宿舍号为空！");
        }
        if (addDormRepairForm.getRepairType() == null){
            sb.append("维修类型为空！");
        }
        if (StringUtils.isEmpty(addDormRepairForm.getRepairDesc())){
            sb.append("维修描述为空");
        }
        if (StringUtils.isEmpty(addDormRepairForm.getRepairImgUrl())){
            sb.append("维修图片为空");
        }
        if (addDormRepairForm.getStudentNum() == null){
            sb.append("学号为空！");
        }
        if (StringUtils.isEmpty(addDormRepairForm.getStudentMobile())){
            sb.append("手机号为空");
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
