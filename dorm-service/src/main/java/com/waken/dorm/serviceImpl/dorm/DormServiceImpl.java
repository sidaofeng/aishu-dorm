package com.waken.dorm.serviceImpl.dorm;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.entity.dorm.DormStudentRel;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormStudentRelForm;
import com.waken.dorm.common.form.dorm.DormForm;
import com.waken.dorm.common.form.dorm.EditDormForm;
import com.waken.dorm.common.sequence.UUIDSequence;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.dorm.AppDormView;
import com.waken.dorm.common.view.dorm.DormStudentRelView;
import com.waken.dorm.common.view.dorm.DormStudentsView;
import com.waken.dorm.common.view.dorm.DormView;
import com.waken.dorm.dao.dorm.DormMapper;
import com.waken.dorm.dao.dorm.DormStudentRelMapper;
import com.waken.dorm.manager.UserManager;
import com.waken.dorm.service.dorm.DormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName DormServiceImpl
 * @Description DormServiceImpl
 * @Author zhaoRong
 * @Date 2019/3/31 13:16
 **/
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@Service
public class DormServiceImpl implements DormService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DormMapper dormMapper;
    @Autowired
    DormStudentRelMapper dormStudentRelMapper;

    /**
     * 保存/修改宿舍信息
     *
     * @param editForm
     * @return
     */
    @Transactional
    @Override
    public Dorm saveDorm(EditDormForm editForm) {
        this.editValidate(editForm);
        String userId = UserManager.getCurrentUserId();
        Date curDate = DateUtils.getCurrentDate();
        Dorm dorm = new Dorm();
        BeanMapper.copy(editForm, dorm);
        dorm.setLastModifyTime(curDate);
        dorm.setLastModifyUserId(userId);
        if (StringUtils.isEmpty(editForm.getPkDormId())) {//新增
            logger.info("service: 开始进入新增宿舍信息");
            String pkDormId = UUIDSequence.next();
            dorm.setPkDormId(pkDormId);
            dorm.setStatus(CodeEnum.ENABLE.getCode());
            dorm.setCreateTime(curDate);
            dorm.setCreateUserId(userId);
            int count = dormMapper.insert(dorm);
            Assert.isFalse(count == Constant.ZERO);
            return dorm;
        } else {//更新宿舍信息
            logger.info("service: 开始进入更新宿舍信息");
            dormMapper.updateById(dorm);
            return dormMapper.selectById(editForm.getPkDormId());
        }
    }

    /**
     * 删除宿舍
     *
     * @param deleteForm
     */
    @Transactional
    @Override
    public void deleteDorm(DeleteForm deleteForm) {
        logger.info("service: 删除宿舍开始");
        List<String> ids = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count ;
        if (CodeEnum.YES.getCode() == delStatus) { // 物理删除
            List<Dorm> dormList = dormMapper.selectByIds(ids);
            StringBuffer sb = new StringBuffer();
            for (Dorm dorm : dormList) {
                if (CodeEnum.ENABLE.getCode() == dorm.getStatus()) {
                    sb.append(dorm.getDormNum());
                }
            }
            if (StringUtils.isNotEmpty(sb.toString())) {
                throw new ServerException("以下宿舍处于生效中：" + sb.toString());
            }
            //删除宿舍
            count = dormMapper.deleteBatchIds(ids);
            Assert.isFalse(count == Constant.ZERO);

        } else if (CodeEnum.NO.getCode() == delStatus) {
            count = dormMapper.batchUpdateStatus(DormUtil.getToUpdateStatusMap(ids,UserManager.getCurrentUserId()));
            Assert.isFalse(count == Constant.ZERO);
        } else {
            throw new ServerException("删除状态码错误！");
        }
    }

    /**
     * 分页查询宿舍楼信息
     *
     * @param dormForm
     * @return
     */
    @Override
    public PageInfo<DormView> listDorms(DormForm dormForm) {
        logger.info("service: 分页查询宿舍信息开始");
        if (dormForm.getStartTime() != null) {
            dormForm.setStartTime(DateUtils.formatDate2DateTimeStart(dormForm.getStartTime()));
        }
        if (dormForm.getEndTime() != null) {
            dormForm.setEndTime(DateUtils.formatDate2DateTimeEnd(dormForm.getEndTime()));
        }
        PageHelper.startPage(dormForm.getPageNum(), dormForm.getPageSize());
        List<DormView> dormList = dormMapper.listDorms(dormForm);
        return new PageInfo<>(dormList);
    }

    /**
     * 查询出该宿舍所关联的班级中的已与该宿舍关联的学生 与未关联该宿舍的学生
     *
     * @param dormId
     * @return
     */
    @Override
    public DormStudentsView queryDormStudentsView(String dormId) {
        Assert.notNull(dormId);
        List<DormStudentRelView> relList = dormStudentRelMapper.listDormStudentRelView(dormId);
        if (null == relList || relList.isEmpty()){
            return new DormStudentsView();
        }
        List<DormStudentRelView> addedList = new ArrayList<>();//用于接收已经存在关联的学生信息
        List<DormStudentRelView> unAddedList = new ArrayList<>();//用于接收没有关联的学生信息
        for (DormStudentRelView dormStudentRelView : relList) {
            if (StringUtils.isEmpty(dormStudentRelView.getPkDormStudentId())) {//关联id为空，表示是未关联的学生
                unAddedList.add(dormStudentRelView);
            } else {
                addedList.add(dormStudentRelView);
            }
        }
        DormStudentsView dormStudentsView = new DormStudentsView();
        dormStudentsView.setAddedList(addedList);
        dormStudentsView.setToBeAddList(unAddedList);
        return dormStudentsView;
    }

    /**
     * 批量新增宿舍与学生关联
     *
     * @param addDormStudentRelForm
     */
    @Transactional
    @Override
    public void batchAddDormStudentRel(AddDormStudentRelForm addDormStudentRelForm) {
        logger.info("service: 批量新增宿舍与学生关联开始");
        List<DormStudentRel> toBeAddDormStudentRel = this.getToBeAddDormStudentRel(addDormStudentRelForm);
        if (!toBeAddDormStudentRel.isEmpty()) {
            int count = dormStudentRelMapper.batchAddDormStudentRel(toBeAddDormStudentRel);
            if (count == Constant.ZERO) {
                throw new ServerException("批量新增宿舍与学生关联个数为 0 条");
            }

        }
    }

    /**
     * 通过学生id获取宿舍信息
     *
     * @param studentId
     */
    @Override
    public String getDormIdByStudentId(String studentId) {
        List<DormStudentRel> dormStudentRelList = dormStudentRelMapper.selectList(new EntityWrapper<DormStudentRel>()
                .eq("student_id", studentId)
                .eq("status", CodeEnum.ENABLE.getCode())
        );
        if (null != dormStudentRelList && !dormStudentRelList.isEmpty()) {
            return dormStudentRelList.get(Constant.ZERO).getDormId();
        }
        return Constant.NULL_STRING;
    }

    /**
     * 得到需要新增的宿舍学生关联集合
     *
     * @param addDormStudentRelForm
     * @return
     */
    private List<DormStudentRel> getToBeAddDormStudentRel(AddDormStudentRelForm addDormStudentRelForm) {
        String dormId = addDormStudentRelForm.getDormId();
        List<String> studentIds = addDormStudentRelForm.getStudentIds();
        List<DormStudentRel> dormStudentRelList = dormStudentRelMapper.selectList(new EntityWrapper<DormStudentRel>()
                .eq("dorm_id", dormId)
                .eq("status", CodeEnum.ENABLE.getCode())
        );
        if (!dormStudentRelList.isEmpty()) {
            List<String> existIds = new ArrayList<>();// 接收已经存在关联的学生id
            List<String> toDelIds = new ArrayList<>();// 接收需要删除的学生id
            for (DormStudentRel rel : dormStudentRelList) {
                if (studentIds.contains(rel.getStudentId())) {
                    existIds.add(rel.getStudentId());
                } else {
                    toDelIds.add(rel.getPkDormStudentId());
                }
            }
            studentIds.removeAll(existIds);
            if (!toDelIds.isEmpty()) {
                int count = dormStudentRelMapper.deleteBatchIds(toDelIds);
                Assert.isFalse(count == Constant.ZERO);
            }
        }
        if (null == studentIds || studentIds.isEmpty()){
            return new ArrayList<>();
        }
        List<DormStudentRel> toBeAddDormStudentRelList = new ArrayList<>();
        for (String studentId : studentIds) {
            String pkDormStudentId = UUIDSequence.next();
            String curUserId = UserManager.getCurrentUserId();
            Date curDate = DateUtils.getCurrentDate();
            DormStudentRel DormStudentRel = new DormStudentRel();
            DormStudentRel.setPkDormStudentId(pkDormStudentId);
            DormStudentRel.setDormId(dormId);
            DormStudentRel.setStudentId(studentId);
            DormStudentRel.setStatus(CodeEnum.ENABLE.getCode());
            DormStudentRel.setCreateTime(curDate);
            DormStudentRel.setCreateUserId(curUserId);
            DormStudentRel.setLastModifyTime(curDate);
            DormStudentRel.setLastModifyUserId(curUserId);
            toBeAddDormStudentRelList.add(DormStudentRel);
        }
        return toBeAddDormStudentRelList;
    }

    /**
     * 新增宿舍时 验证
     *
     * @param editForm
     */
    private void editValidate(EditDormForm editForm) {
        if (StringUtils.isEmpty(editForm.getPkDormId())) {//新增验证
            Assert.notNull(editForm.getDormBuildingId());
            Assert.notNull(editForm.getDormType());
            Assert.notNull(editForm.getDormNum());
            Assert.notNull(editForm.getBuildingLevelth());
            Assert.notNull(editForm.getDormBuildingId());
            List<Dorm> dorms = dormMapper.selectList(new EntityWrapper<Dorm>()
                    .eq("dorm_num", editForm.getDormNum())
            );
            Assert.isNull(dorms,dorms.isEmpty(),"已存在相同名称的宿舍编号！");
        } else {//修改验证
            Dorm dorm = dormMapper.selectById(editForm.getPkDormId());
            Assert.notNull(dorm,"参数错误！");
            if (StringUtils.isNotEmpty(editForm.getDormNum())) {
                List<Dorm> dorms = dormMapper.selectList(new EntityWrapper<Dorm>()
                        .eq("dorm_num", editForm.getDormNum())
                );
                if (!dorms.isEmpty()) {
                    if (!StringUtils.equals(dorms.get(Constant.ZERO).getPkDormId(), editForm.getPkDormId())) {
                        throw new ServerException("已存在相同名称的宿舍编号！");
                    }
                }
            }
        }
    }

    /**
     * app端查询宿舍视图
     *
     * @param studentId
     * @return
     */
    @Override
    public AppDormView queryAppDormView(String studentId) {
        AppDormView appDormView = dormMapper.queryAppDormView(studentId);
        if (appDormView == null) {
            return new AppDormView();
        }
        return appDormView;
    }
}
