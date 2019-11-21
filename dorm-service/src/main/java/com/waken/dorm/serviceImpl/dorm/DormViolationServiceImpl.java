package com.waken.dorm.serviceImpl.dorm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import com.waken.dorm.common.manager.UserManager;
import com.waken.dorm.common.utils.Assert;
import com.waken.dorm.common.utils.BeanMapper;
import com.waken.dorm.common.utils.DateUtils;
import com.waken.dorm.common.utils.DormUtil;
import com.waken.dorm.common.view.dorm.AppDormViolationView;
import com.waken.dorm.common.view.dorm.DormViolationView;
import com.waken.dorm.dao.dorm.DormMapper;
import com.waken.dorm.dao.dorm.DormViolationMapper;
import com.waken.dorm.dao.student.StudentMapper;
import com.waken.dorm.handle.DataHandle;
import com.waken.dorm.service.dorm.DormViolationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DormViolationServiceImpl implements DormViolationService {
    private final DormViolationMapper dormViolationMapper;
    private final DormMapper dormMapper;
    private final StudentMapper studentMapper;

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
        DormViolation dormViolation = new DormViolation();
        BeanMapper.copy(addDormViolationForm, dormViolation);
        dormViolation.setDormId(dormId);
        dormViolation.setStudentId(studentId);
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
        log.info("service: 删除违规记录开始");
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
            Assert.isNull(sb.toString(),"以下违规记录处于未处理状态中：" + sb.toString());
            //删除宿舍
            count = dormViolationMapper.deleteBatchIds(ids);
            Assert.isFalse(count == Constant.ZERO);

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
    public IPage<DormViolationView> listDormViolations(DormViolationForm dormViolationForm) {
        log.info("service: 违规记录分页查询开始");
        return dormViolationMapper.listDormViolations(DataHandle.getWrapperPage(dormViolationForm),dormViolationForm);
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
    public IPage<AppDormViolationView> appListDormViolations(DormViolationForm dormViolationForm) {
        log.info("service: 违规记录分页查询开始");
        Page page  = new Page(dormViolationForm.getPageNum(),dormViolationForm.getPageSize());
        return dormViolationMapper.appListDormViolations(page,dormViolationForm);
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
        Dorm dorm = dormMapper.selectOne(new LambdaQueryWrapper<Dorm>()
                .eq(Dorm::getCode, dormNum)
        );
        if (null == dorm) {
            throw new ServerException("宿舍号不存在!");
        }
        return dorm.getId();
    }

    private String validateStudent(Integer studentNum) {
        Student student = studentMapper.selectOne(new LambdaQueryWrapper<Student>()
                .eq(Student::getStudentNum, studentNum)
        );
        if (null == student) {
            throw new ServerException("学号不存在!");
        }
        return student.getPkStudentId();
    }

}
