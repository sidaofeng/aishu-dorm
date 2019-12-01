package com.waken.dorm.serviceImpl.dorm;

import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.entity.dorm.DormBed;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormStudentRelForm;
import com.waken.dorm.common.form.dorm.DormForm;
import com.waken.dorm.common.utils.Assert;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.view.dorm.AppDormView;
import com.waken.dorm.common.view.dorm.DormStudentsView;
import com.waken.dorm.common.view.dorm.DormView;
import com.waken.dorm.dao.dorm.DormMapper;
import com.waken.dorm.handle.DataHandle;
import com.waken.dorm.service.dorm.BedService;
import com.waken.dorm.service.dorm.DormService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DormServiceImpl
 * @Description DormServiceImpl
 * @Author zhaoRong
 * @Date 2019/3/31 13:16
 **/
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DormServiceImpl extends ServiceImpl<DormMapper, Dorm> implements DormService {
    private final BedService bedService;

    @Override
    public int insert(Dorm dorm) {
        int result = 0;
        if (dorm == null) {
            throw new ServiceException("入参数据为空");
        } else {
            Assert.notNull(dorm.getName(), "宿舍名称必填");
            Assert.notNull(dorm.getCode(), "宿舍编号必填");
            Assert.notNull(dorm.getFloorId(), "宿舍楼层必填");
            Assert.notNull(dorm.getType(), "宿舍类型必填");
        }
        if (this.verification(dorm) == 0) {
            result = this.baseMapper.insert(dorm);
            if (result == 1) {
                this.initBed(dorm);
            }
        }
        return result;
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
                List<Dorm> dormList = new ArrayList<>();
                for (String id : deleteForm.getDelIds()) {
                    Dorm dorm = new Dorm();
                    dorm.setId(id);
                    dorm.setIsDeleted(true);
                    dormList.add(dorm);
                }
                this.updateBatchById(dormList);
            } else {
                throw new ServerException("删除状态码错误");
            }

        }
    }

    @Override
    public int update(Dorm dorm) {
        int result = 0;
        if (dorm == null) {
            throw new ServiceException("入参数据为空");
        }
        if (this.verification(dorm) == 0) {
            result = this.baseMapper.updateById(dorm);
        }
        return result;
    }

    @Override
    public Dorm get(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException("入参数据为空");
        }
        return this.baseMapper.selectById(id);
    }
    /**
     * 分页查询宿舍楼信息
     *
     * @param dormForm
     * @return
     */
    @Override
    public IPage<DormView> page(DormForm dormForm) {
        log.info("service: 分页查询宿舍信息开始");
        return this.baseMapper.findPage(DataHandle.getWrapperPage(dormForm), dormForm);
    }

    /**
     * 查询出该宿舍所关联的班级中的已与该宿舍关联的学生 与未关联该宿舍的学生
     *
     * @param dormId
     * @return
     */
    @Override
    public DormStudentsView queryDormStudentsView(String dormId) {
//        Assert.notNull(dormId);
//        List<DormStudentRelView> relList = dormStudentRelMapper.listDormStudentRelView(dormId);
//        if (null == relList || relList.isEmpty()){
//            return new DormStudentsView();
//        }
//        List<DormStudentRelView> addedList = new ArrayList<>();//用于接收已经存在关联的学生信息
//        List<DormStudentRelView> unAddedList = new ArrayList<>();//用于接收没有关联的学生信息
//        for (DormStudentRelView dormStudentRelView : relList) {
//            if (StringUtils.isEmpty(dormStudentRelView.getPkDormStudentId())) {//关联id为空，表示是未关联的学生
//                unAddedList.add(dormStudentRelView);
//            } else {
//                addedList.add(dormStudentRelView);
//            }
//        }
//        DormStudentsView dormStudentsView = new DormStudentsView();
//        dormStudentsView.setAddedList(addedList);
//        dormStudentsView.setToBeAddList(unAddedList);
        return null;
    }

    /**
     * 批量新增宿舍与学生关联
     *
     * @param addDormStudentRelForm
     */
    @Transactional
    @Override
    public void batchAddDormStudentRel(AddDormStudentRelForm addDormStudentRelForm) {
        log.info("service: 批量新增宿舍与学生关联开始");
//        List<DormStudentRel> toBeAddDormStudentRel = this.getToBeAddDormStudentRel(addDormStudentRelForm);
//        if (!toBeAddDormStudentRel.isEmpty()) {
//            int count = dormStudentRelMapper.batchAddDormStudentRel(toBeAddDormStudentRel);
//            if (count == Constant.ZERO) {
//                throw new ServerException("批量新增宿舍与学生关联个数为 0 条");
//            }
//
//        }
    }


    /**
     * 得到需要新增的宿舍学生关联集合
     *
     * @param addDormStudentRelForm
     * @return
     */
//    private List<DormStudentRel> getToBeAddDormStudentRel(AddDormStudentRelForm addDormStudentRelForm) {
//        String dormId = addDormStudentRelForm.getDormId();
//        List<String> studentIds = addDormStudentRelForm.getStudentIds();
//        List<DormStudentRel> dormStudentRelList = dormStudentRelMapper.selectList(new LambdaQueryWrapper<DormStudentRel>()
//                .eq(DormStudentRel::getDormId, dormId)
//                .eq(DormStudentRel::getStatus, CodeEnum.ENABLE.getCode())
//        );
//        if (!dormStudentRelList.isEmpty()) {
//            List<String> existIds = new ArrayList<>();// 接收已经存在关联的学生id
//            List<String> toDelIds = new ArrayList<>();// 接收需要删除的学生id
//            for (DormStudentRel rel : dormStudentRelList) {
//                if (studentIds.contains(rel.getStudentId())) {
//                    existIds.add(rel.getStudentId());
//                } else {
//                    toDelIds.add(rel.getPkDormStudentId());
//                }
//            }
//            studentIds.removeAll(existIds);
//            if (!toDelIds.isEmpty()) {
//                int count = dormStudentRelMapper.deleteBatchIds(toDelIds);
//                Assert.isFalse(count == Constant.ZERO);
//            }
//        }
//        if (null == studentIds || studentIds.isEmpty()){
//            return new ArrayList<>();
//        }
//        List<DormStudentRel> toBeAddDormStudentRelList = new ArrayList<>();
//        for (String studentId : studentIds) {
//            DormStudentRel DormStudentRel = new DormStudentRel();
//            DormStudentRel.setDormId(dormId);
//            DormStudentRel.setStudentId(studentId);
//            DormStudentRel.setStatus(CodeEnum.ENABLE.getCode());
//            toBeAddDormStudentRelList.add(DormStudentRel);
//        }
//        return toBeAddDormStudentRelList;
//    }


    /**
     * app端查询宿舍视图
     *
     * @param studentId
     * @return
     */
    @Override
    public AppDormView queryAppDormView(String studentId) {
        AppDormView appDormView = this.baseMapper.queryAppDormView(studentId);
        if (appDormView == null) {
            return new AppDormView();
        }
        return appDormView;
    }

    /**
     * 新增宿舍时初始化床位
     *
     * @param dorm
     */
    private void initBed(Dorm dorm) {
        String dormId = dorm.getId();
        if (dorm.getBedNum() < 0 || dorm.getBedNum() > 20) {
            throw new ServerException("宿舍床位数量必须再0~20之间");
        } else {
            List<DormBed> bedList = new ArrayList<>();
            for (int i = 1; i <= dorm.getBedNum(); i++) {
                DormBed bed = new DormBed();
                bed.setDormId(dormId);
                bed.setCode(i);
                bed.setName(i + "号床");
                bed.setMemo(dorm.getCode() + "-" + i + "号床");
                bedList.add(bed);
            }
            this.bedService.saveBatch(bedList);
        }
    }

    private int verification(Dorm dorm) {
        if (dorm.getName() != null) {
            //验证宿舍名称
            List<Dorm> dormList = this.baseMapper.selectList(new LambdaQueryWrapper<Dorm>()
                    .eq(Dorm::getName, dorm.getName())
                    .eq(Dorm::getFloorId, dorm.getFloorId())
                    .eq(Dorm::getIsDeleted, 0));
            if (dormList.size() != 0) {
                if (dorm.getId() == null) {
                    throw new ServiceException("该名称已经存在");
                } else {
                    for (Dorm dorms : dormList) {
                        if (!dorms.getId().equals(dorm.getId())) {
                            //Id不一致说明数据不止一条 ，数据重复
                            throw new ServiceException("该名称已经存在");
                        }
                    }
                }
            }
        }
        if (dorm.getCode() != null) {
            //验证宿舍号
            List<Dorm> dormList = this.baseMapper.selectList(new LambdaQueryWrapper<Dorm>()
                    .eq(Dorm::getCode, dorm.getCode())
                    .eq(Dorm::getFloorId, dorm.getFloorId())
                    .eq(Dorm::getIsDeleted, 0));
            if (dormList.size() != 0) {
                if (dorm.getId() == null) {
                    throw new ServiceException("宿舍号不能重复");
                } else {
                    for (Dorm dorms : dormList) {
                        if (!dorms.getId().equals(dorm.getId())) {
                            //Id不一致说明数据不止一条 ，数据重复
                            throw new ServiceException("宿舍号不能重复");
                        }
                    }
                }
            }
        }
        return 0;
    }
}
