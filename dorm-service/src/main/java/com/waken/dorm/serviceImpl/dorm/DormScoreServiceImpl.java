package com.waken.dorm.serviceImpl.dorm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dorm.DormScore;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dorm.DormScoreForm;
import com.waken.dorm.common.form.dorm.ListDormScoreForm;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.dorm.AppDormScoreView;
import com.waken.dorm.common.view.dorm.DormScoreView;
import com.waken.dorm.dao.dorm.DormMapper;
import com.waken.dorm.dao.dorm.DormScoreMapper;
import com.waken.dorm.manager.UserManager;
import com.waken.dorm.service.dorm.DormScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName DormScoreServiceImpl
 * @Description DormScoreServiceImpl
 * @Author zhaoRong
 * @Date 2019/3/31 19:45
 **/
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@Service
public class DormScoreServiceImpl implements DormScoreService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DormScoreMapper dormScoreMapper;
    @Autowired
    DormMapper dormMapper;

    /**
     * 批量导入宿舍评分记录（excel）
     *
     * @param dormScoreList
     */
    @Transactional
    @Override
    public void batchAddDormScore(List<DormScore> dormScoreList) {
        if (dormScoreList.isEmpty()) {
            throw new ServerException("批量导入失败，原因传入数据为空！");
        }
        Date curDate = DateUtils.getCurrentDate();
        String userId = UserManager.getCurrentUserId();
        for (DormScore dormScore : dormScoreList) {
            String pkDormScoreId = UUIDUtils.getPkUUID();
            dormScore.setPkDormScoreId(pkDormScoreId);
            dormScore.setStatus(CodeEnum.ENABLE.getCode());
            dormScore.setCreateTime(curDate);
            dormScore.setCreateUserId(userId);
            dormScore.setLastModifyTime(curDate);
            dormScore.setLastModifyUserId(userId);
        }
        int count = dormScoreMapper.batchAddDormScore(dormScoreList);
        if (count == Constant.ZERO) {
            throw new ServerException("批量新增评分的个数为 0 条！");
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
        List<String> ids = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count;
        if (CodeEnum.YES.getCode() == delStatus) { // 物理删除
            List<DormScore> dormScoreList = dormScoreMapper.selectByIds(ids);
            StringBuffer sb = new StringBuffer();
            for (DormScore dormScore : dormScoreList) {
                if (CodeEnum.ENABLE.getCode() == dormScore.getStatus()) {
                    sb.append(dormScore.getDormNum());
                }
            }
            if (StringUtils.isNotEmpty(sb.toString())) {
                throw new ServerException("以下宿舍评分处于生效中：" + sb.toString());
            } else {//删除宿舍
                count = dormScoreMapper.deleteBatchIds(ids);
                if (count == Constant.ZERO) {
                    throw new ServerException("删除宿舍评分个数为 0 条");
                }
            }
        } else if (CodeEnum.NO.getCode() == delStatus) {
            count = dormScoreMapper.batchUpdateStatus(DormUtil.getToUpdateStatusMap(ids,UserManager.getCurrentUserId()));
            if (count == Constant.ZERO) {
                throw new ServerException("状态删除失败");
            }
        } else {
            throw new ServerException("删除状态码错误！");
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
        PageHelper.startPage(listDormScoreForm.getPageNum(), listDormScoreForm.getPageSize());
        List<AppDormScoreView> dormScoreViews = dormScoreMapper.appListDormScoreView(listDormScoreForm);
        return new PageInfo<>(dormScoreViews);
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
        if (listDormScoreForm.getStartTime() != null) {
            listDormScoreForm.setStartTime(DateUtils.formatDate2DateTimeStart(listDormScoreForm.getStartTime()));
        }
        if (listDormScoreForm.getEndTime() != null) {
            listDormScoreForm.setEndTime(DateUtils.formatDate2DateTimeEnd(listDormScoreForm.getEndTime()));
        }
        PageHelper.startPage(listDormScoreForm.getPageNum(), listDormScoreForm.getPageSize());
        List<DormScoreView> dormScoreList = dormScoreMapper.listDormScores(listDormScoreForm);
        return new PageInfo<>(dormScoreList);
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
        if (StringUtils.isEmpty(dormScoreForm.getPkDormScoreId())) {
            throw new ServerException("宿舍评分id为空");
        }
        DormScore dormScore = new DormScore();
        BeanMapper.copy(dormScoreForm, dormScore);
        int count = Constant.ZERO;
        count = dormScoreMapper.updateById(dormScore);
        if (count == Constant.ZERO) {
            throw new ServerException("更新宿舍评分个数为 0 条");
        }

        return null;
    }
}
