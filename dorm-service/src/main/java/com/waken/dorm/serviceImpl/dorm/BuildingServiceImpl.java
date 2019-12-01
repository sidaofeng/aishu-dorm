package com.waken.dorm.serviceImpl.dorm;

import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waken.dorm.common.entity.dorm.Building;
import com.waken.dorm.common.entity.dorm.BuildingFloor;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.BuildingForm;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.view.dorm.BuildingView;
import com.waken.dorm.dao.dorm.BuildingMapper;
import com.waken.dorm.handle.DataHandle;
import com.waken.dorm.service.dorm.BuildingService;
import com.waken.dorm.service.dorm.FloorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BuildingServiceImpl
 * @Description 宿舍楼业务层
 * @Author zhaoRong
 * @Date 2019/3/31 11:00
 **/
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements BuildingService {
    private final FloorService floorService;
    @Override
    public int insert(Building building) {
        int result;
        if (building == null) {
            throw new ServiceException("入参数据为空");
        }
        if (this.verification(building) != 0) {
            throw new ServiceException("建筑名称/建筑编码重复");
        }
        int floorTotal = building.getFloorTotal();
        int floorStart = building.getFloorStart();
        //验证楼层总数
        String errorMsg = this.verifyFloor(floorStart, floorTotal);
        if (StringUtils.isNotEmpty(errorMsg)) {
            throw new ServiceException(errorMsg);
        }
        //添加建筑数据
        result = this.baseMapper.insert(building);
        //初始化楼层
        if (result != 0) {
            List<BuildingFloor> addFloorList = this.getToAddFloorList(building.getId(), floorStart, floorTotal);
            if (null != addFloorList && !addFloorList.isEmpty()) {
                this.floorService.saveBatch(addFloorList);
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
                List<Building> buildingList = new ArrayList<>();
                for (String id : deleteForm.getDelIds()) {
                    Building building = new Building();
                    building.setId(id);
                    building.setIsDeleted(true);
                    buildingList.add(building);
                }
                this.updateBatchById(buildingList);
            } else {
                throw new ServerException("删除状态码错误");
            }

        }
    }

    @Override
    public int update(Building building) {
        int result = 0;
        if (building == null) {
            throw new ServiceException("入参数据为空");
        }
        if (this.verification(building) == 0) {
            result = this.baseMapper.updateById(building);
        }
        return result;
    }

    @Override
    public Building get(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException("入参数据为空");
        }
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<Building> list(String campusId) {
        List<Building> buildingList = this.baseMapper.selectList(new LambdaQueryWrapper<Building>()
                .eq(Building::getIsDeleted, 0)
                .eq(Building::getCampusId, campusId)
                .orderByDesc(Building::getCreateTime));
        return buildingList;
    }

    @Override
    public IPage<BuildingView> page(BuildingForm buildingForm) {

        return this.baseMapper.findBuildingPage(DataHandle.getWrapperPage(buildingForm), buildingForm);
    }

    /**
     * 获取需要新增的建筑楼层
     * @param buildId
     * @param floorStart 起始楼层
     * @param floorTotal 总楼层数
     * @return
     */
    private List<BuildingFloor> getToAddFloorList(String buildId, Integer floorStart, Integer floorTotal) {
        int endFloor;
        if (floorStart < 0) {
            endFloor = floorTotal + floorStart;
        } else {
            endFloor = floorTotal + floorStart - 1;
        }
        List<BuildingFloor> buildingFloors = new ArrayList<>();
        for (int i = floorStart; i <= endFloor; i++) {
            if (i == 0) {
                continue;
            } else {
                BuildingFloor buildingFloor = new BuildingFloor();
                buildingFloor.setBuildingId(buildId);
                buildingFloor.setCode(i);
                buildingFloor.setName(i + "楼");
                buildingFloor.setIsDeleted(false);
                buildingFloors.add(buildingFloor);
            }
        }
        return buildingFloors;
    }

    /**
     * 验证楼层
     * @param startFloor
     * @param floorTotal
     */
    private String verifyFloor(Integer startFloor, Integer floorTotal) {
        if (startFloor == null || floorTotal == null) {
            return "起始楼层或者楼层总数为空！";
        }
        if (startFloor == 0 || floorTotal == 0) {
            return "总楼层不能为 0 层！";
        }

        if (startFloor > floorTotal) {
            return "起始楼层不能大于总楼层";
        }

        if (floorTotal > 99) {
            return "建筑物层数不能超过99层！";
        }
        if (floorTotal < 1) {
            return "建筑物层数少于一层！";
        }
        return null;
    }

    private int verification(Building building) {
        if (building.getName() != null) {
            //验证建筑
            List<Building> buildingList = this.baseMapper.selectList(new LambdaQueryWrapper<Building>()
                    .eq(Building::getName, building.getName())
                    .eq(Building::getCampusId, building.getCampusId())
                    .eq(Building::getIsDeleted, 0));
            if (buildingList.size() != 0) {
                if (building.getId() == null) {
                    throw new ServiceException("该名称已经存在");
                } else {
                    for (Building campus : buildingList) {
                        if (!campus.getId().equals(building.getId())) {
                            //Id不一致说明数据不止一条 ，数据重复
                            throw new ServiceException("该名称已经存在");
                        }
                    }
                }
            }
        }
        if (building.getCode() != null) {
            //验证建筑名称
            List<Building> buildingList = this.baseMapper.selectList(new LambdaQueryWrapper<Building>()
                    .eq(Building::getCode, building.getCode())
                    .eq(Building::getCampusId, building.getCampusId())
                    .eq(Building::getIsDeleted, 0));
            if (buildingList.size() != 0) {
                if (building.getId() == null) {
                    throw new ServiceException("建筑号不能重复");
                } else {
                    for (Building campus : buildingList) {
                        if (!campus.getId().equals(building.getId())) {
                            //Id不一致说明数据不止一条 ，数据重复
                            throw new ServiceException("建筑号不能重复");
                        }
                    }
                }
            }
        }
        return 0;
    }
}
