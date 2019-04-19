package com.waken.dorm.serviceImpl.dorm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dorm.DormBuilding;
import com.waken.dorm.common.entity.dorm.DormBuildingExample;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.DormException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormBuildingForm;
import com.waken.dorm.common.form.dorm.EditDormBuildingForm;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.dorm.DormBuildingView;
import com.waken.dorm.dao.dorm.DormBuildingMapper;
import com.waken.dorm.service.dorm.DormBuildingService;
import com.waken.dorm.service.school.SchoolService;
import com.waken.dorm.serviceImpl.base.BaseServerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DormBuildingServiceImpl
 * @Description 宿舍楼业务层
 * @Author zhaoRong
 * @Date 2019/3/31 11:00
 **/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DormBuildingServiceImpl extends BaseServerImpl implements DormBuildingService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DormBuildingMapper buildingMapper;
    @Autowired
    SchoolService schoolService;

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
        String userId = ShiroUtils.getUserId();
        Date curDate = DateUtils.getCurrentDate();
        int count = Constant.ZERO;
        DormBuilding dormBuilding = new DormBuilding();
        BeanMapper.copy(editBuildingForm,dormBuilding);
        dormBuilding.setLastModifyTime(curDate);
        dormBuilding.setLastModifyUserId(userId);
        if (StringUtils.isEmpty(editBuildingForm.getPkDormBuildingId())){//新增
            logger.info("service: 开始进入新增宿舍楼信息");
            String pkDormBuildingId = UUIDUtils.getPkUUID();
            if (StringUtils.isEmpty(editBuildingForm.getSchoolId())){
                String schoolId = schoolService.getSchoolIdByUserId(userId);
                dormBuilding.setSchoolId(schoolId);
            }else {
                dormBuilding.setSchoolId(editBuildingForm.getSchoolId());
            }
            dormBuilding.setPkDormBuildingId(pkDormBuildingId);
            dormBuilding.setStatus(CodeEnum.ENABLE.getCode());
            dormBuilding.setCreateTime(curDate);
            dormBuilding.setCreateUserId(userId);
            count = buildingMapper.insertSelective(dormBuilding);
            if (count == Constant.ZERO){
                throw new DormException("新增个数为 0 条");
            }
            return dormBuilding;
        }else {//更新宿舍楼信息
            logger.info("service: 开始进入更新宿舍楼信息");
            buildingMapper.updateByPrimaryKeySelective(dormBuilding);
            return buildingMapper.selectByPrimaryKey(editBuildingForm.getPkDormBuildingId());
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
        List<String> dormBuildingIds = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count = Constant.ZERO;
        if (CodeEnum.YES.getCode() == delStatus){ // 物理删除
            List<DormBuilding> dormBuildingList = buildingMapper.selectByIds(dormBuildingIds);
            StringBuffer sb = new StringBuffer();
            for (DormBuilding dormBuilding : dormBuildingList){
                if (CodeEnum.ENABLE.getCode() == dormBuilding.getStatus()){
                    sb.append(dormBuilding.getDormBuildingNum());
                }
            }
            if (StringUtils.isNotEmpty(sb.toString())){
                throw new DormException("以下宿舍楼处于生效中："+sb.toString());
            }else {//删除宿舍
                DormBuildingExample example = new DormBuildingExample();
                DormBuildingExample.Criteria criteria = example.createCriteria();
                criteria.andPkDormBuildingIdIn(dormBuildingIds);
                count = buildingMapper.deleteByExample(example);
                if (count == Constant.ZERO){
                    throw new DormException("删除宿舍楼个数为 0 条");
                }
            }

        }else if(CodeEnum.NO.getCode() == delStatus){//状态删除
            count = buildingMapper.batchUpdateStatus(getToUpdateStatusMap(dormBuildingIds,CodeEnum.DELETE.getCode()));
            if (count == Constant.ZERO){
                throw new DormException("状态删除失败");
            }
        }else {
            throw new DormException("删除状态码错误！");
        }
    }

    /**
     * 分页查询宿舍楼信息
     * @param buildingForm
     * @return
     */
    @Override
    public PageInfo<DormBuildingView> listDormBuildings(DormBuildingForm buildingForm) {
        logger.info("service: 分页查询宿舍楼信息开始");
        if (StringUtils.isEmpty(buildingForm.getSchoolId())) {
            String userId = ShiroUtils.getUserId();
            String schoolId = schoolService.getSchoolIdByUserId(userId);
            buildingForm.setSchoolId(schoolId);
        }
        if (buildingForm.getStartTime() != null) {
            buildingForm.setStartTime(DateUtils.formatDate2DateTimeStart(buildingForm.getStartTime()));
        }
        if (buildingForm.getEndTime() != null) {
            buildingForm.setEndTime(DateUtils.formatDate2DateTimeEnd(buildingForm.getEndTime()));
        }
        PageHelper.startPage(buildingForm.getPageNum(),buildingForm.getPageSize());
        List<DormBuildingView> dormBuildingList = buildingMapper.listDormBuildings(buildingForm);
        return new PageInfo<DormBuildingView>(dormBuildingList);
    }

    /**
     * 编辑宿舍楼时 验证
     * @param editBuildingForm
     */
    private void editBuildingValidate(EditDormBuildingForm editBuildingForm){
        if (StringUtils.isEmpty(editBuildingForm.getPkDormBuildingId())){//新增验证
            StringBuffer sb = new StringBuffer();
            if (editBuildingForm.getDormBuildingType() == null){
                sb.append("宿舍楼类型为空");
            }
            if (StringUtils.isEmpty(editBuildingForm.getDormBuildingNum())){
                sb.append("宿舍楼编号为空");
            }
            if (editBuildingForm.getDormBuildingLevels() == null){
                sb.append("宿舍楼楼层总数为空");
            }
            if (StringUtils.isNotEmpty(sb.toString())){
                throw new DormException("验证失败："+sb.toString());
            }
            DormBuildingExample example = new DormBuildingExample();
            DormBuildingExample.Criteria criteria = example.createCriteria();
            criteria.andDormBuildingNumEqualTo(editBuildingForm.getDormBuildingNum());
            List<DormBuilding> dormBuildings = buildingMapper.selectByExample(example);
            if (!dormBuildings.isEmpty()){
                throw new DormException("已存在相同名称的楼栋号");
            }
        }else {//修改验证
            DormBuilding dormBuilding = buildingMapper.selectByPrimaryKey(editBuildingForm.getPkDormBuildingId());
            if (dormBuilding == null){
                throw new DormException("宿舍楼id不正确");
            }
            if (StringUtils.isNotEmpty(editBuildingForm.getDormBuildingNum())){
                DormBuildingExample example = new DormBuildingExample();
                DormBuildingExample.Criteria criteria = example.createCriteria();
                criteria.andDormBuildingNumEqualTo(editBuildingForm.getDormBuildingNum());
                List<DormBuilding> dormBuildings = buildingMapper.selectByExample(example);
                if (!dormBuildings.isEmpty()){
                    if (!StringUtils.equals(dormBuildings.get(Constant.ZERO).getPkDormBuildingId(),editBuildingForm.getPkDormBuildingId())){
                        throw new DormException("已存在相同名称的楼栋号");
                    }
                }
            }
        }

    }
}
