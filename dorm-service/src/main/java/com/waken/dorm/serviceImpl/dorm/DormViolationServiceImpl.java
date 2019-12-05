package com.waken.dorm.serviceImpl.dorm;

import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.entity.dorm.DormViolation;
import com.waken.dorm.common.entity.student.StudentBasic;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormViolationForm;
import com.waken.dorm.common.utils.Assert;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.view.dorm.DormViolationView;
import com.waken.dorm.dao.dorm.DormMapper;
import com.waken.dorm.dao.dorm.DormViolationMapper;
import com.waken.dorm.dao.student.StudentBasicMapper;
import com.waken.dorm.handle.DataHandle;
import com.waken.dorm.service.dorm.DormViolationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class DormViolationServiceImpl extends ServiceImpl<DormViolationMapper, DormViolation> implements DormViolationService {
    private final DormMapper dormMapper;
    private final StudentBasicMapper studentBasicMapper;

    @Override
    public int insert(DormViolation violation) {
        Assert.notNull(violation.getDormCode());
        Assert.notNull(violation.getStudentCode());
        Assert.notNull(violation.getReason());
        this.validateDorm(violation.getDormCode());
        this.validateStudent(violation.getStudentCode());
        return this.baseMapper.insert(violation);
    }

    @Override
    public void delete(DeleteForm deleteForm) {
        if (deleteForm == null || deleteForm.getDelIds() == null) {
            throw new ServiceException("入参数据为空");
        }
        if (!deleteForm.getDelIds().isEmpty()) {
            //物理删除
            if (deleteForm.getDelStatus() == CodeEnum.YES.getCode()) {
                this.baseMapper.deleteBatchIds(deleteForm.getDelIds());
            } else if (deleteForm.getDelStatus() == CodeEnum.NO.getCode()) {
                List<DormViolation> violationList = new ArrayList<>();
                for (String id : deleteForm.getDelIds()) {
                    DormViolation violation = new DormViolation();
                    violation.setId(id);
                    violation.setIsDeleted(true);
                    violationList.add(violation);
                }
                this.updateBatchById(violationList);
            } else {
                throw new ServerException("删除状态码错误");
            }

        }
    }

    @Override
    public int update(DormViolation violation) {
        if (violation == null) {
            throw new ServiceException("入参数据为空");
        }
        return this.baseMapper.updateById(violation);
    }

    @Override
    public DormViolation get(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException("入参数据为空");
        }
        return this.baseMapper.selectById(id);
    }


    /**
     * 分页查询宿舍违规记录
     *
     * @param dormViolationForm
     * @return
     */
    @Override
    public IPage<DormViolationView> page(DormViolationForm dormViolationForm) {
        return this.baseMapper.findPage(DataHandle.getWrapperPage(dormViolationForm), dormViolationForm);
    }

    private void validateDorm(String dormCode) {
        Dorm dorm = dormMapper.selectOne(new LambdaQueryWrapper<Dorm>()
                .eq(Dorm::getCode, dormCode)
        );
        if (null == dorm) {
            throw new ServerException("宿舍号不存在!");
        }
    }

    private void validateStudent(String studentCode) {
        StudentBasic student = this.studentBasicMapper.selectOne(new LambdaQueryWrapper<StudentBasic>()
                .eq(StudentBasic::getCode, studentCode)
        );
        if (null == student) {
            throw new ServerException("学号不存在!");
        }
    }

}
