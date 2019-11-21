package com.waken.dorm.serviceImpl.dorm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.entity.dorm.DormRepair;
import com.waken.dorm.common.entity.student.Student;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormRepairForm;
import com.waken.dorm.common.form.dorm.DormRepairForm;
import com.waken.dorm.common.form.dorm.UpdateRepairForm;
import com.waken.dorm.common.manager.UserManager;
import com.waken.dorm.common.utils.Assert;
import com.waken.dorm.common.utils.DateUtils;
import com.waken.dorm.common.utils.DormUtil;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.view.dorm.DormRepairView;
import com.waken.dorm.dao.dorm.DormMapper;
import com.waken.dorm.dao.dorm.DormRepairMapper;
import com.waken.dorm.dao.student.StudentMapper;
import com.waken.dorm.handle.DataHandle;
import com.waken.dorm.service.dorm.DormRepairService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DormRepairServiceImpl implements DormRepairService {
    private final DormRepairMapper dormRepairMapper;
    private final DormMapper dormMapper;
    private final StudentMapper studentMapper;

    /**
     * 新增维修记录
     *
     * @param addDormRepairForm
     * @return
     */
    @Transactional
    @Override
    public DormRepair addDormRepair(AddDormRepairForm addDormRepairForm) {
        log.info("service : 开始进入新增维修记录");
        this.addRepairValidate(addDormRepairForm);//验证合法性
        String studentId;
        String dormId = this.validateDorm(addDormRepairForm.getDormNum());//验证宿舍是否存在
        DormRepair dormRepair = new DormRepair();
        if (addDormRepairForm.getTerminal() == CodeEnum.WEB.getCode()) {//web端请求
            String userId = UserManager.getCurrentUserId();
            studentId = this.validateStudent(addDormRepairForm.getStudentNum());//验证学生是否存在
            dormRepair.setCreateUserId(userId);
            dormRepair.setLastModifyUserId(userId);
        } else {//app端请求
            studentId = addDormRepairForm.getStudentId();
            dormRepair.setCreateUserId(studentId);
            dormRepair.setLastModifyUserId(studentId);
        }
        dormRepair.setDormId(dormId);
        dormRepair.setRepairType(addDormRepairForm.getRepairType());
        dormRepair.setRepairDesc(addDormRepairForm.getRepairDesc());
        dormRepair.setRepairImgUrl(addDormRepairForm.getRepairImgUrl());
        dormRepair.setStudentId(studentId);
        dormRepair.setStudentMobile(addDormRepairForm.getStudentMobile());
        dormRepair.setStatus(CodeEnum.REPAIRING.getCode());
        if (StringUtils.isEmpty(addDormRepairForm.getMemo())) {
            dormRepair.setMemo(addDormRepairForm.getMemo());
        }
        int count = dormRepairMapper.insert(dormRepair);
        Assert.isFalse(count == Constant.ZERO);
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
        log.info("service: 删除维修记录开始");
        List<String> ids = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        if (CodeEnum.YES.getCode() == delStatus) { // 物理删除
            List<DormRepair> dormRepairs = dormRepairMapper.selectByIds(ids);
            StringBuffer sb = new StringBuffer();
            for (DormRepair dormRepair : dormRepairs) {
                if (CodeEnum.REPAIRING.getCode() == dormRepair.getStatus()) {
                    sb.append(dormRepair.getPkDormRepairId());
                }
            }
            Assert.isNull(sb.toString(),"以下维修记录处于正在维修状态中：" + sb.toString());
            //删除宿舍
            int count = dormRepairMapper.deleteBatchIds(ids);
            Assert.isFalse(count == Constant.ZERO);

        } else if (CodeEnum.NO.getCode() == delStatus) {
            int count = dormRepairMapper.batchUpdateStatus(DormUtil.getToUpdateStatusMap(ids,UserManager.getCurrentUserId()));
            Assert.isFalse(count == Constant.ZERO);
        } else {
            throw new ServerException("删除状态码错误！");
        }
    }

    /**
     * 分页查询宿舍维修记录
     *
     * @param dormRepairForm
     * @return
     */
    @Override
    public IPage<DormRepairView> listDormRepairs(DormRepairForm dormRepairForm) {
        log.info("service: 分页查询宿舍维修信息开始");
        return dormRepairMapper.listDormRepairs(DataHandle.getWrapperPage(dormRepairForm),dormRepairForm);
    }

    /**
     * 更新宿舍维修记录（提交维修结果）
     *
     * @param updateForm
     * @return
     */
    @Transactional
    @Override
    public DormRepair updateDormRepair(UpdateRepairForm updateForm) {
        Assert.notNull(updateForm.getPkDormRepairId());
        Assert.notNull(updateForm.getStatus());
        DormRepair dormRepair = dormRepairMapper.selectById(updateForm.getPkDormRepairId());
        dormRepair.setStatus(updateForm.getStatus());
        if (updateForm.getRepairCost() != null) {
            dormRepair.setRepairCost(updateForm.getRepairCost());
        }
        if (StringUtils.isNotEmpty(updateForm.getRepairBillUrl())) {
            dormRepair.setRepairBillUrl(updateForm.getRepairBillUrl());
        }
        if (StringUtils.isNotEmpty(updateForm.getMemo())) {
            dormRepair.setMemo(updateForm.getMemo());
        }
        String userId = UserManager.getCurrentUserId();
        Date curDate = DateUtils.getCurrentDate();
        dormRepair.setLastModifyTime(curDate);
        dormRepair.setLastModifyUserId(userId);
        int count = dormRepairMapper.updateById(dormRepair);
        if (count == Constant.ZERO) {
            throw new ServerException("修改维修记录为 0 条");
        }
        return dormRepair;
    }

    /**
     * 新增验证
     *
     * @param addForm
     */
    private void addRepairValidate(AddDormRepairForm addForm) {
        Assert.notNull(addForm.getDormNum());
        Assert.notNull(addForm.getRepairType());
        Assert.notNull(addForm.getRepairDesc());
        Assert.notNull(addForm.getRepairImgUrl());
        Assert.notNull(addForm.getStudentNum());
        Assert.notNull(addForm.getStudentMobile());
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
