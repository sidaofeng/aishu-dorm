package com.waken.dorm.serviceImpl.dorm;

import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waken.dorm.common.entity.dorm.Building;
import com.waken.dorm.common.entity.dorm.BuildingFloor;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.dao.dorm.BuildingMapper;
import com.waken.dorm.dao.dorm.FloorMapper;
import com.waken.dorm.service.dorm.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 建筑物楼层 服务实现类
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FloorServiceImpl extends ServiceImpl<FloorMapper, BuildingFloor> implements FloorService {
    private final BuildingMapper buildingMapper;

    @Override
    public int insert(BuildingFloor floor) {
        int result = 0;
        if (floor == null || StringUtils.isEmpty(floor.getBuildingId())) {
            throw new ServiceException("入参数据为空");
        }
        this.verifyFloor(floor);
        if (this.verification(floor) == 0) {
            result = this.baseMapper.insert(floor);
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
                List<BuildingFloor> floorList = new ArrayList<>();
                for (String id : deleteForm.getDelIds()) {
                    BuildingFloor floor = new BuildingFloor();
                    floor.setId(id);
                    floor.setIsDeleted(true);
                    floorList.add(floor);
                }
                this.updateBatchById(floorList);
            } else {
                throw new ServerException("删除状态码错误");
            }

        }
    }

    @Override
    public int update(BuildingFloor floor) {
        int result = 0;
        if (floor == null) {
            throw new ServiceException("入参数据为空");
        }
        if (this.verification(floor) == 0) {
            //楼层编码不能修改
            floor.setCode(null);
            result = this.baseMapper.updateById(floor);
        }
        return result;
    }

    @Override
    public BuildingFloor get(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException("入参数据为空");
        }
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<BuildingFloor> list(String buildId) {
        List<BuildingFloor> floorList = this.baseMapper.selectList(new LambdaQueryWrapper<BuildingFloor>()
                .eq(BuildingFloor::getIsDeleted, 0)
                .eq(BuildingFloor::getBuildingId, buildId)
                .orderByAsc(BuildingFloor::getCode));
        return floorList;
    }

    private void verifyFloor(BuildingFloor buildingFloor) {
        if (buildingFloor == null) {
            throw new ServiceException("入参数据为空");
        }
        if (buildingFloor.getCode() == 0) {
            throw new ServiceException("不能有第 0 层！");
        }
        Building building = this.buildingMapper.selectById(buildingFloor.getBuildingId());
        int floorTotal = building.getFloorTotal();
        int floorStart = building.getFloorStart();
        int endFloor;
        if (floorStart < 0) {
            endFloor = floorTotal + floorStart;
        } else {
            endFloor = floorTotal + floorStart - 1;
        }
        if (buildingFloor.getCode() > endFloor) {
            throw new ServiceException("楼层对象必须小于等于终点楼层");
        }
        if (buildingFloor.getCode() < building.getFloorStart()) {
            throw new ServiceException("楼层对象必须大于于等于起始楼层");
        }
    }

    private int verification(BuildingFloor floor) {
        if (floor.getName() != null) {
            //验证楼层
            List<BuildingFloor> floorList = this.baseMapper.selectList(new LambdaQueryWrapper<BuildingFloor>()
                    .eq(BuildingFloor::getName, floor.getName())
                    .eq(BuildingFloor::getBuildingId, floor.getBuildingId())
                    .eq(BuildingFloor::getIsDeleted, 0));
            if (floorList.size() != 0) {
                if (floor.getId() == null) {
                    throw new ServiceException("该名称已经存在");
                } else {
                    for (BuildingFloor campus : floorList) {
                        if (!campus.getId().equals(floor.getId())) {
                            //Id不一致说明数据不止一条 ，数据重复
                            throw new ServiceException("该名称已经存在");
                        }
                    }
                }
            }
        }
        if (floor.getCode() != null) {
            //验证校区名称
            List<BuildingFloor> floorList = this.baseMapper.selectList(new LambdaQueryWrapper<BuildingFloor>()
                    .eq(BuildingFloor::getCode, floor.getCode())
                    .eq(BuildingFloor::getBuildingId, floor.getBuildingId())
                    .eq(BuildingFloor::getIsDeleted, 0));
            if (floorList.size() != 0) {
                if (floor.getId() == null) {
                    throw new ServiceException("楼层不能重复");
                } else {
                    for (BuildingFloor campus : floorList) {
                        if (!campus.getId().equals(floor.getId())) {
                            //Id不一致说明数据不止一条 ，数据重复
                            throw new ServiceException("楼层不能重复");
                        }
                    }
                }
            }
        }
        return 0;
    }
}
