package com.waken.dorm.serviceImpl.dorm;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.entity.dorm.DormViolation;
import com.waken.dorm.common.entity.student.Student;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormViolationForm;
import com.waken.dorm.common.form.dorm.DormViolationForm;
import com.waken.dorm.common.form.dorm.UpdateDormViolationForm;
import com.waken.dorm.common.sequence.UUIDSequence;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.dorm.AppDormViolationView;
import com.waken.dorm.common.view.dorm.DormViolationView;
import com.waken.dorm.dao.dorm.DormMapper;
import com.waken.dorm.dao.dorm.DormViolationMapper;
import com.waken.dorm.dao.student.StudentMapper;
import com.waken.dorm.manager.UserManager;
import com.waken.dorm.service.dorm.DormViolationService;
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
public class DormViolationServiceImpl implements DormViolationService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DormViolationMapper dormViolationMapper;
    @Autowired
    DormMapper dormMapper;
    @Autowired
    StudentMapper studentMapper;

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
        String pkDormViolationId = UUIDSequence.next();
        String userId = UserManager.getCurrentUserId();
        Date curDate = DateUtils.getCurrentDate();
        DormViolation dormViolation = new DormViolation();
        BeanMapper.copy(addDormViolationForm, dormViolation);
        dormViolation.setPkDormViolationId(pkDormViolationId);
        dormViolation.setDormId(dormId);
        dormViolation.setStudentId(studentId);
        dormViolation.setCreateTime(curDate);
        dormViolation.setCreateUserId(userId);
        dormViolation.setLastModifyTime(curDate);
        dormViolation.setLastModifyUserId(userId);
        dormViolationMapper.insert(dormViolation);
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
        List<String> ids = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count;
        if (CodeEnum.YES.getCode() == delStatus) { // 物理删除
            List<DormViolation> dormViolations = dormViolationMapper.selectByIds(ids);
            StringBuffer sb = new StringBuffer();
            for (DormViolation dormViolation : dormViolations) {
                if (CodeEnum.UNCOMPLETE.getCode() == dormViolation.getStatus()) {
                    sb.append(dormViolation.getPkDormViolationId());
                }
            }
            if (StringUtils.isNotEmpty(sb.toString())) {
                throw new ServerException("以下违规记录处于未处理状态中：" + sb.toString());
            }//删除宿舍
            count = dormViolationMapper.deleteBatchIds(ids);
            if (count == Constant.ZERO) {
                throw new ServerException("删除违规记录为 0 条");
            }

        } else if (CodeEnum.NO.getCode() == delStatus) {
            dormViolationMapper.batchUpdateStatus(DormUtil.getToUpdateStatusMap(ids,UserManager.getCurrentUserId()));
        } else {
            throw new ServerException("删除状态码错误！");
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
        if (dormViolationForm.getStartTime() != null) {
            dormViolationForm.setStartTime(DateUtils.formatDate2DateTimeStart(dormViolationForm.getStartTime()));
        }
        if (dormViolationForm.getEndTime() != null) {
            dormViolationForm.setEndTime(DateUtils.formatDate2DateTimeEnd(dormViolationForm.getEndTime()));
        }
        PageHelper.startPage(dormViolationForm.getPageNum(), dormViolationForm.getPageSize());
        List<DormViolationView> dormViolationViews = dormViolationMapper.listDormViolations(dormViolationForm);
        return new PageInfo<>(dormViolationViews);
    }

    /**
     * 更新宿舍违规记录（提交违规结果）
     *
     * @param updateForm
     * @return
     */
    @Transactional
    @Override
    public DormViolation updateDormViolation(UpdateDormViolationForm updateForm) {
        Assert.notNull(updateForm.getPkDormViolationId());
        Assert.notNull(updateForm.getStatus());
        DormViolation dormViolation = dormViolationMapper.selectById(updateForm.getPkDormViolationId());
        BeanMapper.copy(updateForm, dormViolation);
        String userId = UserManager.getCurrentUserId();
        Date curDate = DateUtils.getCurrentDate();
        dormViolation.setLastModifyTime(curDate);
        dormViolation.setLastModifyUserId(userId);
        int count = dormViolationMapper.updateById(dormViolation);
        if (count == Constant.ZERO) {
            throw new ServerException("修改违规记录为 0 条");
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
        PageHelper.startPage(dormViolationForm.getPageNum(), dormViolationForm.getPageSize());
        List<AppDormViolationView> appDormViolationViews = dormViolationMapper.appListDormViolations(dormViolationForm);
        return new PageInfo<>(appDormViolationViews);
    }

    /**
     * 新增验证
     *
     * @param addForm
     */
    private void addViolationValidate(AddDormViolationForm addForm) {
        Assert.notNull(addForm.getDormNum());
        Assert.notNull(addForm.getDormRuleId());
        Assert.notNull(addForm.getStudentNum());
        Assert.notNull(addForm.getViolationReason());
        Assert.notNull(addForm.getSolveResult());
    }

    private String validateDorm(String dormNum) {
        List<Dorm> dorms = dormMapper.selectList(new EntityWrapper<Dorm>()
                .eq("dorm_num", dormNum)
        );
        if (dorms.isEmpty()) {
            throw new ServerException("宿舍号不存在!");
        }
        return dorms.get(Constant.ZERO).getPkDormId();
    }

    private String validateStudent(Integer studentNum) {
        List<Student> studentList = studentMapper.selectList(new EntityWrapper<Student>()
                .eq("student_num", studentNum)
        );
        if (studentList.isEmpty()) {
            throw new ServerException("学号不存在!");
        }
        return studentList.get(Constant.ZERO).getPkStudentId();
    }

}
