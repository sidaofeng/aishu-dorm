package com.waken.dorm.serviceImpl.dorm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waken.dorm.common.entity.dorm.DormBed;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.dorm.DormStudentForm;
import com.waken.dorm.common.form.dorm.StudentBedForm;
import com.waken.dorm.common.utils.Assert;
import com.waken.dorm.common.view.dorm.DormStudentView;
import com.waken.dorm.dao.dorm.BedMapper;
import com.waken.dorm.handle.DataHandle;
import com.waken.dorm.service.dorm.DormStudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: DormStudentServiceImpl
 * @Description:
 * @Author: zhaoRong
 * @Create: 2019/12/5 20:48
 **/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DormStudentServiceImpl extends ServiceImpl<BedMapper, DormBed> implements DormStudentService {
    /**
     * 分页
     *
     * @param form
     * @return
     */
    @Override
    public IPage<DormStudentView> findPage(DormStudentForm form) {

        return this.baseMapper.findPage(DataHandle.getWrapperPage(form), form);
    }

    @Override
    public Integer batchAdd(StudentBedForm form) {
        if (this.verification(form)) {
            List<String> bedIdList = form.getBedIdList();
            List<String> studentIdList = form.getStudentIdList();
            List<String> oldBedIds = this.baseMapper.selectList(new LambdaQueryWrapper<DormBed>()
                    .in(DormBed::getSubjectId, studentIdList))
                    .stream().map(dormBed -> dormBed.getId())
                    .collect(Collectors.toList());
            this.baseMapper.deleteBatchIds(oldBedIds);
            //随机分配房间
            List<DormBed> toUpdateList = new ArrayList<>(bedIdList.size());
            for (int i = 0; i < bedIdList.size(); i++) {
                DormBed dormBed = new DormBed();
                dormBed.setId(bedIdList.get(i));
                dormBed.setSubjectId(studentIdList.get(i));
                toUpdateList.add(dormBed);
            }
            this.updateBatchById(toUpdateList);
        }


        return null;
    }

    private Boolean verification(StudentBedForm form) {
        Assert.notNull(form);
        List<String> bedIdList = form.getBedIdList();
        List<String> studentIdList = form.getStudentIdList();
        if (studentIdList == null || studentIdList.size() == 0) {
            return false;
        } else {
            if (bedIdList != null && bedIdList.size() != studentIdList.size()) {
                throw new ServerException("学生数量和床位数量必须一样！");
            }
        }
        return true;
    }
}
