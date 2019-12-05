package com.waken.dorm.serviceImpl.dorm;

import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.entity.dorm.DormRepair;
import com.waken.dorm.common.entity.student.StudentBasic;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormRepairForm;
import com.waken.dorm.common.utils.Assert;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.view.dorm.DormRepairView;
import com.waken.dorm.dao.dorm.DormMapper;
import com.waken.dorm.dao.dorm.DormRepairMapper;
import com.waken.dorm.dao.student.StudentBasicMapper;
import com.waken.dorm.handle.DataHandle;
import com.waken.dorm.service.dorm.RepairService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RepairServiceImpl
 * @Description 宿舍维修业务层
 * @Author zhaoRong
 * @Date 2019/4/1 21:14
 **/
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RepairServiceImpl extends ServiceImpl<DormRepairMapper, DormRepair> implements RepairService {
    private final DormMapper dormMapper;
    private final StudentBasicMapper studentBasicMapper;

    @Override
    public int insert(DormRepair repair) {
        Assert.notNull(repair.getDormCode());
        Assert.notNull(repair.getStudentCode());
        Assert.notNull(repair.getDescription());
        Assert.notNull(repair.getStudentMobile());
        this.validateDorm(repair.getDormCode());
        this.validateStudent(repair.getStudentCode());
        return this.baseMapper.insert(repair);
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
                List<DormRepair> repairList = new ArrayList<>();
                for (String id : deleteForm.getDelIds()) {
                    DormRepair repair = new DormRepair();
                    repair.setId(id);
                    repair.setIsDeleted(true);
                    repairList.add(repair);
                }
                this.updateBatchById(repairList);
            } else {
                throw new ServerException("删除状态码错误");
            }

        }
    }

    @Override
    public int update(DormRepair repair) {
        if (repair == null) {
            throw new ServiceException("入参数据为空");
        }
        return this.baseMapper.updateById(repair);
    }

    @Override
    public DormRepair get(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException("入参数据为空");
        }
        return this.baseMapper.selectById(id);
    }

    @Override
    public IPage<DormRepairView> findPage(DormRepairForm dormRepairForm) {

        return this.baseMapper.findPage(DataHandle.getWrapperPage(dormRepairForm), dormRepairForm);
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
