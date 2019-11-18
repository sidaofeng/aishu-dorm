package com.waken.dorm.serviceImpl.dorm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dorm.DormBuilding;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormBuildingForm;
import com.waken.dorm.common.form.dorm.EditDormBuildingForm;
import com.waken.dorm.common.manager.UserManager;
import com.waken.dorm.common.utils.Assert;
import com.waken.dorm.common.utils.BeanMapper;
import com.waken.dorm.common.utils.DormUtil;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.view.dorm.DormBuildingView;
import com.waken.dorm.dao.dorm.DormBuildingMapper;
import com.waken.dorm.handle.DataHandle;
import com.waken.dorm.service.dorm.DormBuildingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName DormBuildingServiceImpl
 * @Description 宿舍楼业务层
 * @Author zhaoRong
 * @Date 2019/3/31 11:00
 **/
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DormBuildingServiceImpl implements DormBuildingService {
    private final DormBuildingMapper buildingMapper;

    /**
     * 保存/修改宿舍楼信息
     *
     * @param editBuildingForm
     * @return
     */
    @Transactional
    @Override
    public DormBuilding saveDormBuilding(EditDormBuildingForm editBuildingForm) {
        this.editBuildingValidate(editBuildingForm);
        int count;
        DormBuilding dormBuilding = new DormBuilding();
        BeanMapper.copy(editBuildingForm, dormBuilding);
        if (StringUtils.isEmpty(editBuildingForm.getPkDormBuildingId())) {//新增
            log.info("service: 开始进入新增宿舍楼信息");
            dormBuilding.setStatus(CodeEnum.ENABLE.getCode());
            count = buildingMapper.insert(dormBuilding);
            if (count == Constant.ZERO) {
                throw new ServerException("新增个数为 0 条");
            }
            return dormBuilding;
        } else {//更新宿舍楼信息
            log.info("service: 开始进入更新宿舍楼信息");
            buildingMapper.updateById(dormBuilding);
            return buildingMapper.selectById(editBuildingForm.getPkDormBuildingId());
        }
    }

    /**
     * 删除宿舍楼
     *
     * @param deleteForm
     */
    @Transactional
    @Override
    public void deleteDormBuilding(DeleteForm deleteForm) {
        log.info("service: 删除宿舍楼开始");
        List<String> ids = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count;
        if (CodeEnum.YES.getCode() == delStatus) { // 物理删除
            List<DormBuilding> dormBuildingList = buildingMapper.selectByIds(ids);
            StringBuffer sb = new StringBuffer();
            for (DormBuilding dormBuilding : dormBuildingList) {
                if (CodeEnum.ENABLE.getCode() == dormBuilding.getStatus()) {
                    sb.append(dormBuilding.getDormBuildingNum());
                }
            }
            Assert.isNull(sb.toString(),"操作失败,以下宿舍楼处于生效中：" + sb.toString());
            //删除宿舍
            count = buildingMapper.deleteBatchIds(ids);
            Assert.isFalse(count == Constant.ZERO);
        } else if (CodeEnum.NO.getCode() == delStatus) {//状态删除
            count = buildingMapper.batchUpdateStatus(DormUtil.getToUpdateStatusMap(ids,UserManager.getCurrentUserId()));
            Assert.isFalse(count == Constant.ZERO);
        } else {
            throw new ServerException("删除状态码错误！");
        }
    }

    /**
     * 分页查询宿舍楼信息
     *
     * @param buildingForm
     * @return
     */
    @Override
    public IPage<DormBuildingView> listDormBuildings(DormBuildingForm buildingForm) {
        log.info("service: 分页查询宿舍楼信息开始");
        return buildingMapper.listDormBuildings(DataHandle.getWrapperPage(buildingForm),buildingForm);
    }

    /**
     * 编辑宿舍楼时 验证
     *
     * @param editForm
     */
    private void editBuildingValidate(EditDormBuildingForm editForm) {
        if (StringUtils.isEmpty(editForm.getPkDormBuildingId())) {//新增验证
            Assert.notNull(editForm.getDormBuildingType());
            Assert.notNull(editForm.getDormBuildingNum());
            Assert.notNull(editForm.getDormBuildingLevels());
            DormBuilding dormBuilding = buildingMapper.selectOne(new LambdaQueryWrapper<DormBuilding>()
                    .eq(DormBuilding::getDormBuildingNum, editForm.getDormBuildingNum())
            );
            Assert.isNull(dormBuilding,"已存在相同名称的楼栋号");
        } else {//修改验证
            Assert.notNull(buildingMapper.selectById(editForm.getPkDormBuildingId()),"参数错误");
            if (StringUtils.isNotEmpty(editForm.getDormBuildingNum())) {
                DormBuilding dormBuilding = buildingMapper.selectOne(new LambdaQueryWrapper<DormBuilding>()
                        .eq(DormBuilding::getDormBuildingNum, editForm.getDormBuildingNum())
                );
                if (null != dormBuilding) {
                    if (!StringUtils.equals(dormBuilding.getPkDormBuildingId(), editForm.getPkDormBuildingId())) {
                        throw new ServerException("已存在相同名称的楼栋号");
                    }
                }
            }
        }

    }
}
