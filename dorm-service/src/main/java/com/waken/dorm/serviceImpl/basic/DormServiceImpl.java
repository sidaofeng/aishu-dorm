package com.waken.dorm.serviceImpl.basic;

import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dorm.Building;
import com.waken.dorm.common.entity.dorm.BuildingFloor;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.entity.dorm.DormBed;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormForm;
import com.waken.dorm.common.form.dorm.FloorDormForm;
import com.waken.dorm.common.utils.Assert;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.common.view.dorm.AppDormView;
import com.waken.dorm.common.view.dorm.DormView;
import com.waken.dorm.dao.dorm.BuildingMapper;
import com.waken.dorm.dao.dorm.DormMapper;
import com.waken.dorm.dao.dorm.FloorMapper;
import com.waken.dorm.handle.DataHandle;
import com.waken.dorm.service.basic.BedService;
import com.waken.dorm.service.basic.DormService;
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
    private final FloorMapper floorMapper;
    private final BuildingMapper buildingMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insert(Dorm dorm) {

        if (this.verification(dorm) == 0) {
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
                    List<Dorm> dormList = new ArrayList<>();
                    dormList.add(dorm);
                    this.initBed(dormList, dorm.getBedNum());
                }
            }
            return result;
        }
        return 0;
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

        return this.baseMapper.findPage(DataHandle.getWrapperPage(dormForm), dormForm);
    }

    /**
     * app端查询宿舍视图
     *
     * @param studentId
     * @return
     */
    @Override
    public AppDormView queryAppDormView(String studentId) {
        Assert.notNull(studentId);
        AppDormView appDormView = this.baseMapper.queryAppDormView(studentId);
        if (appDormView == null) {
            return new AppDormView();
        }
        return appDormView;
    }

    @Override
    public List<Dorm> listByFloor(String floorId) {
        Assert.notNull(floorId);
        return this.baseMapper.selectList(new LambdaQueryWrapper<Dorm>()
                .eq(Dorm::getFloorId, floorId)
                .eq(Dorm::getIsDeleted, false)
        );
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer batchInsert(FloorDormForm form) {
        if (this.verification(form) == 0) {
            BuildingFloor floor = this.floorMapper.selectById(form.getFloorId());
            Building building = this.buildingMapper.selectById(floor.getBuildingId());
            Integer bedNum = form.getBedNum();
            if (bedNum == null) {
                bedNum = 0;
            }
            List<Dorm> toAddList = new ArrayList<>();
            for (int i = 1; i <= form.getRoomNum(); i++) {
                Dorm dorm = new Dorm();
                dorm.setFloorId(form.getFloorId());
                dorm.setBedNum(bedNum);
                dorm.setDormSex(form.getSex());
                dorm.setType(form.getType());
                dorm.setStatus(1);
                dorm.setIsDeleted(false);
                //默认生成规则：建筑号+"-"+楼层号+流水号
                dorm.setCode(building.getCode() + Constant.UNDER_LINE + floor.getCode() + String.format("%02d", i));
                dorm.setName(dorm.getCode());
                toAddList.add(dorm);
            }
            if (this.saveBatch(toAddList) && bedNum > 0) {
                this.initBed(toAddList, bedNum);
            }
        }
        return 1;
    }

    /**
     * 初始化床位
     * @param dormList
     * @param bedNum
     */
    private int initBed(List<Dorm> dormList, Integer bedNum) {
        if (bedNum < 0 || bedNum > 20) {
            throw new ServerException("宿舍床位数量必须再0~20之间");
        } else {
            if (dormList != null && !dormList.isEmpty()) {
                List<DormBed> bedList = new ArrayList<>();
                dormList.stream().forEach(dorm -> {
                    for (int i = 1; i <= bedNum; i++) {
                        DormBed bed = new DormBed();
                        bed.setDormId(dorm.getId());
                        bed.setCode(i);
                        bed.setName(i + "号床");
                        bed.setMemo(dorm.getCode() + "-" + i + "号床");
                        bedList.add(bed);
                    }
                });
                this.bedService.saveBatch(bedList);
            }
        }
        return 0;
    }

    private int verification(FloorDormForm form) {
        Assert.notNull(form);
        Assert.notNull(form.getFloorId(), "楼层ID不能为空！");
        Assert.notNull(form.getRoomNum(), "房间数量不能为空！");
        Assert.isFalse(form.getRoomNum() == 0, "房间数量必须大于0！");
        if (form.getRoomNum() < 0 || form.getRoomNum() > 20) {
            throw new ServerException("宿舍房间数量必须再0~30之间");
        }
        if (form.getBedNum() != null && (form.getBedNum() < 0 || form.getBedNum() > 20)) {
            throw new ServerException("宿舍床位数量必须再0~20之间");
        }
        Integer count = this.baseMapper.selectCount(new LambdaQueryWrapper<Dorm>()
                .eq(Dorm::getFloorId, form.getFloorId())
                .eq(Dorm::getIsDeleted, false)
        );
        if (count != 0) {
            throw new ServerException("该楼层已存在宿舍房间！");
        }
        return 0;
    }
    private int verification(Dorm dorm) {
        if (dorm.getName() != null) {
            //验证宿舍名称
            List<Dorm> dormList = this.baseMapper.selectList(new LambdaQueryWrapper<Dorm>()
                    .eq(Dorm::getName, dorm.getName())
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

