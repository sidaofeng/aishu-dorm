package com.waken.dorm.serviceImpl.dorm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dorm.Dorm;
import com.waken.dorm.common.entity.dorm.DormScore;
import com.waken.dorm.common.entity.dorm.DormScoreExample;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.DormException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormScoreForm;
import com.waken.dorm.common.form.dorm.ListDormScoreForm;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.utils.excel.ExcelUtil;
import com.waken.dorm.common.view.dorm.AppDormScoreView;
import com.waken.dorm.common.view.dorm.DormScoreView;
import com.waken.dorm.dao.dorm.DormMapper;
import com.waken.dorm.dao.dorm.DormScoreMapper;
import com.waken.dorm.service.dorm.DormScoreService;
import com.waken.dorm.service.school.SchoolService;
import com.waken.dorm.serviceImpl.base.BaseServerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * @ClassName DormScoreServiceImpl
 * @Description DormScoreServiceImpl
 * @Author zhaoRong
 * @Date 2019/3/31 19:45
 **/
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@Service
public class DormScoreServiceImpl extends BaseServerImpl implements DormScoreService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DormScoreMapper dormScoreMapper;
    @Autowired
    SchoolService schoolService;
    @Autowired
    DormMapper dormMapper;
    /**
     * 批量导入宿舍评分记录（excel）
     *
     * @param multipartFile
     */
    @Transactional
    @Override
    public void batchAddDormScore(MultipartFile multipartFile) {
        ExcelUtil<DormScore> excelUtil = new ExcelUtil<>(DormScore.class);
        List<DormScore> dormScoreList = excelUtil.importExcel(multipartFile, Constant.ONE);
        List<String> dormNums = new ArrayList<>();
        if (dormScoreList.isEmpty()){
            throw new DormException("批量导入失败，原因传入数据为空！");
        }
        for (DormScore dormScore : dormScoreList){
            dormNums.add(dormScore.getDormNum());
        }
        Set set = new HashSet();
        List<String> listNew=new ArrayList<>();
        set.addAll(dormNums);
        listNew.addAll(set);
        List<Dorm> dorms = dormMapper.selectByDormNums(listNew);
        Date curDate = DateUtils.getCurrentDate();
        String userId = ShiroUtils.getUserId();
        for (DormScore dormScore : dormScoreList) {
            for (Dorm dorm : dorms){
                if (StringUtils.equals(dorm.getDormNum(),dormScore.getDormNum())){
                    dormScore.setDormId(dorm.getPkDormId());
                }
            }
            String pkDormScoreId = UUIDUtils.getPkUUID();
            dormScore.setPkDormScoreId(pkDormScoreId);
            dormScore.setStatus(CodeEnum.ENABLE.getCode());
            dormScore.setCreateTime(curDate);
            dormScore.setCreateUserId(userId);
            dormScore.setLastModifyTime(curDate);
            dormScore.setLastModifyUserId(userId);
        }
        int count = Constant.ZERO;
        count = dormScoreMapper.batchAddDormScore(dormScoreList);
        if (count == Constant.ZERO){
            throw new DormException("批量新增评分的个数为 0 条！");
        }
    }
    /**
     * 删除评分记录
     *
     * @param deleteForm
     */
    @Transactional
    @Override
    public void deleteDormScore(DeleteForm deleteForm) {
        logger.info("service: 删除积分开始");
        List<String> dormScoreIds = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count = Constant.ZERO;
        if (CodeEnum.YES.getCode() == delStatus){ // 物理删除
            List<DormScore> dormScoreList = dormScoreMapper.selectByIds(dormScoreIds);
            StringBuffer sb = new StringBuffer();
            for (DormScore dormScore : dormScoreList){
                if (CodeEnum.ENABLE.getCode() == dormScore.getStatus()){
                    sb.append(dormScore.getDormNum());
                }
            }
            if (StringUtils.isNotEmpty(sb.toString())){
                throw new DormException("以下宿舍评分处于生效中："+sb.toString());
            }else {//删除宿舍
                DormScoreExample example = new DormScoreExample();
                DormScoreExample.Criteria criteria = example.createCriteria();
                criteria.andPkDormScoreIdIn(dormScoreIds);
                count = dormScoreMapper.deleteByExample(example);
                if (count == Constant.ZERO){
                    throw new DormException("删除宿舍评分个数为 0 条");
                }
            }
        }else if(CodeEnum.NO.getCode() == delStatus){
            count = dormScoreMapper.batchUpdateStatus(getToUpdateStatusMap(dormScoreIds,CodeEnum.DELETE.getCode()));
            if (count == Constant.ZERO){
                throw new DormException("状态删除失败");
            }
        } else {
            throw new DormException("删除状态码错误！");
        }
    }

    /**
     * 通过学生id查询对应宿舍的评分记录
     *
     * @param listDormScoreForm
     * @return
     */
    @Override
    public PageInfo<AppDormScoreView> appListDormScoreViews(ListDormScoreForm listDormScoreForm) {
        PageHelper.startPage(listDormScoreForm.getPageNum(),listDormScoreForm.getPageSize());
        List<AppDormScoreView> dormScoreViews = dormScoreMapper.appListDormScoreView(listDormScoreForm);
        return new PageInfo<AppDormScoreView>(dormScoreViews);
    }

    /**
     * 分页查询宿舍评分
     *
     * @param listDormScoreForm
     * @return
     */
    @Override
    public PageInfo<DormScoreView> listDormScores(ListDormScoreForm listDormScoreForm) {
        logger.info("service: 分页查询宿舍评分信息开始");
        if (StringUtils.isEmpty(listDormScoreForm.getSchoolId())) {
            String userId = ShiroUtils.getUserId();
            String schoolId = schoolService.getSchoolIdByUserId(userId);
            listDormScoreForm.setSchoolId(schoolId);
        }
        if (listDormScoreForm.getStartTime() != null) {
            listDormScoreForm.setStartTime(DateUtils.formatDate2DateTimeStart(listDormScoreForm.getStartTime()));
        }
        if (listDormScoreForm.getEndTime() != null) {
            listDormScoreForm.setEndTime(DateUtils.formatDate2DateTimeEnd(listDormScoreForm.getEndTime()));
        }
        PageHelper.startPage(listDormScoreForm.getPageNum(),listDormScoreForm.getPageSize());
        List<DormScoreView> dormScoreList = dormScoreMapper.listDormScores(listDormScoreForm);
        return new PageInfo<DormScoreView>(dormScoreList);
    }

    /**
     * 修改评分
     *
     * @param dormScoreForm
     * @return
     */
    @Transactional
    @Override
    public DormScore updateDormScore(DormScoreForm dormScoreForm) {
        if(StringUtils.isEmpty(dormScoreForm.getPkDormScoreId())){
            throw new DormException("宿舍评分id为空");
        }
        DormScore dormScore = new DormScore();
        BeanMapper.copy(dormScoreForm, dormScore);
        int count = Constant.ZERO;
        count = dormScoreMapper.updateByPrimaryKeySelective(dormScore);
        if (count == Constant.ZERO){
            throw new DormException("更新宿舍评分个数为 0 条");
        }

        return null;
    }
}
