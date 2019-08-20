package com.waken.dorm.serviceImpl.dorm;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dorm.DormBuilding;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormBuildingForm;
import com.waken.dorm.common.form.dorm.EditDormBuildingForm;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.dorm.DormBuildingView;
import com.waken.dorm.dao.dorm.DormBuildingMapper;
import com.waken.dorm.manager.UserManager;
import com.waken.dorm.service.dorm.DormBuildingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName DormBuildingServiceImpl
 * @Description 宿舍楼业务层
 * @Author zhaoRong
 * @Date 2019/3/31 11:00
 **/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DormBuildingServiceImpl implements DormBuildingService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DormBuildingMapper buildingMapper;

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
        String userId = UserManager.getCurrentUserId();
        Date curDate = DateUtils.getCurrentDate();
        int count;
        DormBuilding dormBuilding = new DormBuilding();
        BeanMapper.copy(editBuildingForm, dormBuilding);
        dormBuilding.setLastModifyTime(curDate);
        dormBuilding.setLastModifyUserId(userId);
        if (StringUtils.isEmpty(editBuildingForm.getPkDormBuildingId())) {//新增
            logger.info("service: 开始进入新增宿舍楼信息");
            String pkDormBuildingId = UUIDUtils.getPkUUID();
            dormBuilding.setPkDormBuildingId(pkDormBuildingId);
            dormBuilding.setStatus(CodeEnum.ENABLE.getCode());
            dormBuilding.setCreateTime(curDate);
            dormBuilding.setCreateUserId(userId);
            count = buildingMapper.insert(dormBuilding);
            if (count == Constant.ZERO) {
                throw new ServerException("新增个数为 0 条");
            }
            return dormBuilding;
        } else {//更新宿舍楼信息
            logger.info("service: 开始进入更新宿舍楼信息");
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
        logger.info("service: 删除宿舍楼开始");
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
            if (StringUtils.isNotEmpty(sb.toString())) {
                throw new ServerException("以下宿舍楼处于生效中：" + sb.toString());
            } else {//删除宿舍
                count = buildingMapper.deleteBatchIds(ids);
                if (count == Constant.ZERO) {
                    throw new ServerException("删除宿舍楼个数为 0 条");
                }
            }

        } else if (CodeEnum.NO.getCode() == delStatus) {//状态删除
            count = buildingMapper.batchUpdateStatus(DormUtil.getToUpdateStatusMap(ids,UserManager.getCurrentUserId()));
            if (count == Constant.ZERO) {
                throw new ServerException("状态删除失败");
            }
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
    public PageInfo<DormBuildingView> listDormBuildings(DormBuildingForm buildingForm) {
        logger.info("service: 分页查询宿舍楼信息开始");
        if (buildingForm.getStartTime() != null) {
            buildingForm.setStartTime(DateUtils.formatDate2DateTimeStart(buildingForm.getStartTime()));
        }
        if (buildingForm.getEndTime() != null) {
            buildingForm.setEndTime(DateUtils.formatDate2DateTimeEnd(buildingForm.getEndTime()));
        }
        PageHelper.startPage(buildingForm.getPageNum(), buildingForm.getPageSize());
        List<DormBuildingView> dormBuildingList = buildingMapper.listDormBuildings(buildingForm);
        return new PageInfo<DormBuildingView>(dormBuildingList);
    }

    /**
     * 编辑宿舍楼时 验证
     *
     * @param editBuildingForm
     */
    private void editBuildingValidate(EditDormBuildingForm editBuildingForm) {
        if (StringUtils.isEmpty(editBuildingForm.getPkDormBuildingId())) {//新增验证
            StringBuffer sb = new StringBuffer();
            if (editBuildingForm.getDormBuildingType() == null) {
                sb.append("宿舍楼类型为空");
            }
            if (StringUtils.isEmpty(editBuildingForm.getDormBuildingNum())) {
                sb.append("宿舍楼编号为空");
            }
            if (editBuildingForm.getDormBuildingLevels() == null) {
                sb.append("宿舍楼楼层总数为空");
            }
            if (StringUtils.isNotEmpty(sb.toString())) {
                throw new ServerException("验证失败：" + sb.toString());
            }
            List<DormBuilding> dormBuildings = buildingMapper.selectList(new EntityWrapper<DormBuilding>()
                    .eq("dorm_building_num", editBuildingForm.getDormBuildingNum())
            );
            if (!dormBuildings.isEmpty()) {
                throw new ServerException("已存在相同名称的楼栋号");
            }
        } else {//修改验证
            DormBuilding dormBuilding = buildingMapper.selectById(editBuildingForm.getPkDormBuildingId());
            if (dormBuilding == null) {
                throw new ServerException("宿舍楼id不正确");
            }
            if (StringUtils.isNotEmpty(editBuildingForm.getDormBuildingNum())) {
                List<DormBuilding> dormBuildings = buildingMapper.selectList(new EntityWrapper<DormBuilding>()
                        .eq("dorm_building_num", editBuildingForm.getDormBuildingNum())
                );
                if (!dormBuildings.isEmpty()) {
                    if (!StringUtils.equals(dormBuildings.get(Constant.ZERO).getPkDormBuildingId(), editBuildingForm.getPkDormBuildingId())) {
                        throw new ServerException("已存在相同名称的楼栋号");
                    }
                }
            }
        }

    }
}
