package com.waken.dorm.serviceImpl.dorm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dorm.Building;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.BuildingForm;
import com.waken.dorm.common.form.dorm.EditBuildingForm;
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

import java.util.ArrayList;
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
public class DormBuildingServiceImpl extends ServiceImpl<DormBuildingMapper, Building> implements DormBuildingService {
    private final DormBuildingMapper buildingMapper;

    /**
     * 保存/修改宿舍楼信息
     *
     * @param editBuildingForm
     * @return
     */
    @Transactional
    @Override
    public Building saveDormBuilding(EditBuildingForm editBuildingForm) {
        this.editBuildingValidate(editBuildingForm);
        int count;
        Building building = new Building();
        BeanMapper.copy(editBuildingForm, building);
        if (StringUtils.isEmpty(editBuildingForm.getId())) {//新增
            log.info("service: 开始进入新增宿舍楼信息");
            building.setIsDeleted(false);
            count = buildingMapper.insert(building);
            if (count == Constant.ZERO) {
                throw new ServerException("新增个数为 0 条");
            }
            return building;
        } else {//更新宿舍楼信息
            log.info("service: 开始进入更新宿舍楼信息");
            buildingMapper.updateById(building);
            return buildingMapper.selectById(editBuildingForm.getId());
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
        if (ids == null || ids.isEmpty()) {
            return;
        }
        Integer delStatus = deleteForm.getDelStatus();
        int count;
        if (CodeEnum.YES.getCode() == delStatus) { // 物理删除
            //删除宿舍
            count = buildingMapper.deleteBatchIds(ids);
            Assert.isFalse(count == Constant.ZERO);
        } else if (CodeEnum.NO.getCode() == delStatus) {//状态删除
            List<Building> buildingList = new ArrayList<>(ids.size());
            ids.stream().forEach(id -> {
                Building building = new Building();
                building.setId(id);
                building.setIsDeleted(true);
                buildingList.add(building);
            });
            this.updateBatchById(buildingList);
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
    public IPage<DormBuildingView> listDormBuildings(BuildingForm buildingForm) {
        log.info("service: 分页查询宿舍楼信息开始");
        return buildingMapper.findBuildingPage(DataHandle.getWrapperPage(buildingForm), buildingForm);
    }

    /**
     * 编辑宿舍楼时 验证
     *
     * @param editForm
     */
    private void editBuildingValidate(EditBuildingForm editForm) {
        if (StringUtils.isEmpty(editForm.getId())) {
            //新增验证
            Assert.notNull(editForm.getType());
            Assert.notNull(editForm.getCode());
            Assert.notNull(editForm.getFloorTotal());
            Building building = buildingMapper.selectOne(new LambdaQueryWrapper<Building>()
                    .eq(Building::getCode, editForm.getCode())
            );
            Assert.isNull(building, "已存在相同名称的楼栋号");
        } else {//修改验证
            Assert.notNull(buildingMapper.selectById(editForm.getId()), "参数错误");
            if (StringUtils.isNotEmpty(editForm.getCode())) {
                Building building = buildingMapper.selectOne(new LambdaQueryWrapper<Building>()
                        .eq(Building::getCode, editForm.getCode())
                );
                if (null != building) {
                    if (!StringUtils.equals(building.getId(), editForm.getId())) {
                        throw new ServerException("已存在相同名称的楼栋号");
                    }
                }
            }
        }

    }
}
