package com.waken.dorm.serviceImpl.dict;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dict.Dict;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dict.DictForm;
import com.waken.dorm.common.form.dict.EditDictForm;
import com.waken.dorm.common.sequence.UUIDSequence;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.dict.DictView;
import com.waken.dorm.dao.dict.DictMapper;
import com.waken.dorm.manager.UserManager;
import com.waken.dorm.service.dict.DictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName DictServiceImpl
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/19 13:08
 **/
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DictServiceImpl implements DictService {
    private final DictMapper dictMapper;

    /**
     * 保存或修改系统字典
     *
     * @param editDictForm
     * @return
     */
    @Override
    @Transactional
    public Dict saveDict(EditDictForm editDictForm) {
        String userId = UserManager.getCurrentUserId();
        this.editDictValidate(editDictForm);
        Date curDate = DateUtils.getCurrentDate();
        Dict dict = new Dict();
        BeanMapper.copy(editDictForm, dict);
        dict.setLastModifyTime(curDate);
        dict.setLastModifyUserId(userId);
        if (StringUtils.isEmpty(editDictForm.getPkDictId())) {//新增
            String pkDictId = UUIDSequence.next();
            dict.setPkDictId(pkDictId);
            dict.setStatus(CodeEnum.ENABLE.getCode());
            dict.setCreateTime(curDate);
            dict.setCreateUserId(userId);
            int count = dictMapper.insert(dict);
            Assert.isFalse(count == Constant.ZERO);
            return dict;
        } else {//修改
            dictMapper.updateById(dict);
            return dict;
        }
    }

    /**
     * 删除字典
     *
     * @param deleteForm
     */
    @Override
    @Transactional
    public void deleteDict(DeleteForm deleteForm) {
        log.info("service: 删除字典信息开始");
        List<String> ids = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count;
        if (CodeEnum.YES.getCode() == delStatus) { // 物理删除
            List<Dict> dictList = dictMapper.selectByIds(ids);
            StringBuffer sb = new StringBuffer();
            for (Dict dict : dictList) {
                if (CodeEnum.ENABLE.getCode() == dict.getStatus()) {
                    sb.append(dict.getDictValue());
                }
            }
            Assert.isNull(sb.toString(),"以下字典生效中"+sb.toString());
            //删除字典
            count = dictMapper.deleteBatchIds(ids);
            Assert.isFalse(count == Constant.ZERO);
        } else if (CodeEnum.NO.getCode() == delStatus) {//状态删除
            count = dictMapper.batchUpdateStatus(DormUtil.getToUpdateStatusMap(ids,UserManager.getCurrentUserId()));
            Assert.isFalse(count == Constant.ZERO);
        } else {
            throw new ServerException("删除状态码错误！");
        }
    }

    /**
     * 分页查询字典
     *
     * @param dictForm
     * @return
     */
    @Override
    public PageInfo<DictView> listDicts(DictForm dictForm) {
        log.info("service: 分页查询宿舍楼信息开始");
        if (dictForm.getStartTime() != null) {
            dictForm.setStartTime(DateUtils.formatDate2DateTimeStart(dictForm.getStartTime()));
        }
        if (dictForm.getEndTime() != null) {
            dictForm.setEndTime(DateUtils.formatDate2DateTimeEnd(dictForm.getEndTime()));
        }
        PageHelper.startPage(dictForm.getPageNum(), dictForm.getPageSize());
        List<DictView> dictViews = dictMapper.listDicts(dictForm);
        return new PageInfo<>(dictViews);
    }

    /**
     * 编辑字典验证
     *
     * @param editForm
     */
    private void editDictValidate(EditDictForm editForm) {
        if (StringUtils.isEmpty(editForm.getPkDictId())) {//新增验证
            Assert.notNull(editForm.getDictKey());
            Assert.notNull(editForm.getDictValue());
            Assert.notNull(editForm.getColumnName());
            Assert.notNull(editForm.getTableName());
            this.keyValidate(editForm);
            this.valueValidate(editForm);
        } else {//修改验证
            Assert.notNull(dictMapper.selectById(editForm.getPkDictId()),"参数错误");
        }
    }

    private void keyValidate(EditDictForm editForm) {
        DictForm dictForm = new DictForm();
        dictForm.setTableName(editForm.getTableName());
        dictForm.setColumnName(editForm.getColumnName());
        dictForm.setDictKey(editForm.getDictKey());
        List<DictView> dicts = dictMapper.listDicts(dictForm);
        Assert.isNull(dicts,dicts.isEmpty(),"操作失败，原因是" + editForm.getColumnName() + "字段中存在相同key");
    }

    private void valueValidate(EditDictForm editForm) {
        DictForm dictForm = new DictForm();
        dictForm.setTableName(editForm.getTableName());
        dictForm.setColumnName(editForm.getColumnName());
        dictForm.setDictValue(editForm.getDictValue());
        List<DictView> dicts = dictMapper.listDicts(dictForm);
        Assert.isNull(dicts,dicts.isEmpty(),"操作失败，原因是" + editForm.getColumnName() + "字段中存在相同value");
    }
}
