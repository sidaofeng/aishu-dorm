package com.waken.dorm.serviceImpl.dorm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.entity.dorm.DormStudentRel;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.AddDormStudentRelForm;
import com.waken.dorm.common.form.dorm.DormForm;
import com.waken.dorm.common.form.dorm.EditDormForm;
import com.waken.dorm.common.manager.UserManager;
import com.waken.dorm.common.utils.Assert;
import com.waken.dorm.common.utils.BeanMapper;
import com.waken.dorm.common.utils.DormUtil;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.view.dorm.AppDormView;
import com.waken.dorm.common.view.dorm.DormStudentRelView;
import com.waken.dorm.common.view.dorm.DormStudentsView;
import com.waken.dorm.common.view.dorm.DormView;
import com.waken.dorm.dao.dorm.DormMapper;
import com.waken.dorm.dao.dorm.DormStudentRelMapper;
import com.waken.dorm.handle.DataHandle;
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
public class DormServiceImpl implements DormService {
    private final DormMapper dormMapper;
    private final DormStudentRelMapper dormStudentRelMapper;

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
        Dorm dorm = new Dorm();
        BeanMapper.copy(editForm, dorm);
        if (StringUtils.isEmpty(editForm.getPkDormId())) {//新增
            log.info("service: 开始进入新增宿舍信息");
            dorm.setStatus(CodeEnum.ENABLE.getCode());
            int count = dormMapper.insert(dorm);
            Assert.isFalse(count == Constant.ZERO);
            return dorm;
        } else {//更新宿舍信息
            log.info("service: 开始进入更新宿舍信息");
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
        log.info("service: 删除宿舍开始");
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
    public IPage<DormView> listDorms(DormForm dormForm) {
        log.info("service: 分页查询宿舍信息开始");
        return dormMapper.listDorms(DataHandle.getWrapperPage(dormForm),dormForm);
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
        log.info("service: 批量新增宿舍与学生关联开始");
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
        DormStudentRel dormStudentRel = dormStudentRelMapper.selectOne(new LambdaQueryWrapper<DormStudentRel>()
                .eq(DormStudentRel::getStudentId, studentId)
                .eq(DormStudentRel::getStatus, CodeEnum.ENABLE.getCode())
        );

        if (null != dormStudentRel) {
            return dormStudentRel.getDormId();
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
        List<DormStudentRel> dormStudentRelList = dormStudentRelMapper.selectList(new LambdaQueryWrapper<DormStudentRel>()
                .eq(DormStudentRel::getDormId, dormId)
                .eq(DormStudentRel::getStatus, CodeEnum.ENABLE.getCode())
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
            DormStudentRel DormStudentRel = new DormStudentRel();
            DormStudentRel.setDormId(dormId);
            DormStudentRel.setStudentId(studentId);
            DormStudentRel.setStatus(CodeEnum.ENABLE.getCode());
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
            Dorm dorm = dormMapper.selectOne(new LambdaQueryWrapper<Dorm>()
                    .eq(Dorm::getDormNum, editForm.getDormNum())
            );
            Assert.isNull(dorm,"已存在相同名称的宿舍编号！");
        } else {//修改验证
            Assert.notNull(dormMapper.selectById(editForm.getPkDormId()),"参数错误！");
            if (StringUtils.isNotEmpty(editForm.getDormNum())) {
                Dorm dorm = dormMapper.selectOne(new LambdaQueryWrapper<Dorm>()
                        .eq(Dorm::getDormNum, editForm.getDormNum())
                );
                if (null != dorm) {
                    if (!StringUtils.equals(dorm.getPkDormId(), editForm.getPkDormId())) {
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
