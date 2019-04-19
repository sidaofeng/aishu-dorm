package com.waken.dorm.serviceImpl.dict;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.dict.Dict;
import com.waken.dorm.common.entity.dict.DictExample;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.DormException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dict.DictForm;
import com.waken.dorm.common.form.dict.EditDictForm;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.dict.DictView;
import com.waken.dorm.dao.dict.DictMapper;
import com.waken.dorm.service.dict.DictService;
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

/**
 * @ClassName DictServiceImpl
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/19 13:08
 **/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DictServiceImpl extends BaseServerImpl implements DictService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DictMapper dictMapper;
    @Autowired
    SchoolService schoolService;
    /**
     * 保存或修改系统字典
     *
     * @param editDictForm
     * @return
     */
    @Override
    @Transactional
    public Dict saveDict(EditDictForm editDictForm) {
        String userId = ShiroUtils.getUserId();
        String schoolId = schoolService.getSchoolIdByUserId(userId);
        if (StringUtils.isNotEmpty(schoolId)){
            editDictForm.setSchoolId(schoolId);
        }
        this.editDictValidate(editDictForm);
        Date curDate = DateUtils.getCurrentDate();
        Dict dict = new Dict();
        BeanMapper.copy(editDictForm,dict);
        dict.setLastModifyTime(curDate);
        dict.setLastModifyUserId(userId);
        if (StringUtils.isEmpty(editDictForm.getPkDictId())){//新增
            int count = Constant.ZERO;
            String pkDictId = UUIDUtils.getPkUUID();
            dict.setPkDictId(pkDictId);
            dict.setStatus(CodeEnum.ENABLE.getCode());
            dict.setCreateTime(curDate);
            dict.setCreateUserId(userId);
            count = dictMapper.insertSelective(dict);
            if (count == Constant.ZERO){
                throw new DormException("新增个数为 0 条！");
            }
            return dict;
        }else {//修改
            dictMapper.updateByPrimaryKeySelective(dict);
            return dictMapper.selectByPrimaryKey(dict.getPkDictId());
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
        logger.info("service: 删除字典信息开始");
        List<String> dictIds = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count = Constant.ZERO;
        if (CodeEnum.YES.getCode() == delStatus){ // 物理删除
            List<Dict> dictList = dictMapper.selectByIds(dictIds);
            StringBuffer sb = new StringBuffer();
            for (Dict dict : dictList){
                if (CodeEnum.ENABLE.getCode() == dict.getStatus()){
                    sb.append(dict.getDictValue());
                }
            }
            if (StringUtils.isNotEmpty(sb.toString())){
                throw new DormException("以下字典信息处于生效中："+sb.toString());
            }else {//删除字典
                DictExample example = new DictExample();
                DictExample.Criteria criteria = example.createCriteria();
                criteria.andPkDictIdIn(dictIds);
                count = dictMapper.deleteByExample(example);
                if (count == Constant.ZERO){
                    throw new DormException("删除字典信息个数为 0 条");
                }
            }

        }else if(CodeEnum.NO.getCode() == delStatus){//状态删除
            count = dictMapper.batchUpdateStatus(getToUpdateStatusMap(dictIds,CodeEnum.DELETE.getCode()));
            if (count == Constant.ZERO){
                throw new DormException("状态删除失败");
            }
        }else {
            throw new DormException("删除状态码错误！");
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
        logger.info("service: 分页查询宿舍楼信息开始");
        if (StringUtils.isEmpty(dictForm.getSchoolId())) {
            String userId = ShiroUtils.getUserId();
            String schoolId = schoolService.getSchoolIdByUserId(userId);
            if (StringUtils.isNotEmpty(schoolId)){
                dictForm.setSchoolId(schoolId);
            }
        }
        if (dictForm.getStartTime() != null) {
            dictForm.setStartTime(DateUtils.formatDate2DateTimeStart(dictForm.getStartTime()));
        }
        if (dictForm.getEndTime() != null) {
            dictForm.setEndTime(DateUtils.formatDate2DateTimeEnd(dictForm.getEndTime()));
        }
        PageHelper.startPage(dictForm.getPageNum(),dictForm.getPageSize());
        List<DictView> dictViews = dictMapper.listDicts(dictForm);
        return new PageInfo<DictView>(dictViews);
    }

    /**
     * 编辑字典验证
     * @param editDictForm
     */
    private void editDictValidate(EditDictForm editDictForm){
        if (StringUtils.isEmpty(editDictForm.getPkDictId())){//新增验证
            StringBuffer sb = new StringBuffer();
            if (StringUtils.isEmpty(editDictForm.getDictKey())){
                sb.append("键为空！");
            }
            if (StringUtils.isEmpty(editDictForm.getDictValue())){
                sb.append("值为空！");
            }
            if (StringUtils.isEmpty(editDictForm.getColumnName())){
                sb.append("字段名为空！");
            }
            if (StringUtils.isEmpty(editDictForm.getTableName())){
                sb.append("表名为空！");
            }
            if (StringUtils.isNotEmpty(sb.toString())){
                throw new DormException("保存或修改字典失败，原因："+sb.toString());
            }
            this.keyValidate(editDictForm);
            this.valueValidate(editDictForm);
        }else {//修改验证
            if (null == dictMapper.selectByPrimaryKey(editDictForm.getPkDictId())){
                throw new DormException("字典id不正确！");
            }
        }
    }
    private void keyValidate(EditDictForm editDictForm){
        DictForm dictForm = new DictForm();
        if (!StringUtils.isEmpty(editDictForm.getSchoolId())){
            dictForm.setSchoolId(editDictForm.getSchoolId());
        }
        dictForm.setTableName(editDictForm.getTableName());
        dictForm.setColumnName(editDictForm.getColumnName());
        dictForm.setDictKey(editDictForm.getDictKey());
        List<DictView> dicts = dictMapper.listDicts(dictForm);
        if (!dicts.isEmpty()){
            throw new DormException("新增验证失败，原因是"+editDictForm.getColumnName()+"字段中存在相同key");
        }
    }
    private void valueValidate(EditDictForm editDictForm){
        DictForm dictForm = new DictForm();
        if (!StringUtils.isEmpty(editDictForm.getSchoolId())){
            dictForm.setSchoolId(editDictForm.getSchoolId());
        }
        dictForm.setTableName(editDictForm.getTableName());
        dictForm.setColumnName(editDictForm.getColumnName());
        dictForm.setDictValue(editDictForm.getDictValue());
        List<DictView> dicts = dictMapper.listDicts(dictForm);
        if (!dicts.isEmpty()){
            throw new DormException("新增验证失败，原因是"+editDictForm.getColumnName()+"字段中存在相同value");
        }
    }
}