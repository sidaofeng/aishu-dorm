package com.waken.dorm.serviceImpl.dict;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.basic.Dict;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dict.EditDictForm;
import com.waken.dorm.common.utils.Assert;
import com.waken.dorm.common.utils.BeanMapper;
import com.waken.dorm.common.utils.StringUtils;
import com.waken.dorm.dao.basic.DictMapper;
import com.waken.dorm.service.dict.DictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
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
        this.editDictValidate(editDictForm);
        Dict dict = new Dict();
        BeanMapper.copy(editDictForm, dict);
        if (StringUtils.isEmpty(editDictForm.getId())) {//新增
            dict.setIsDeleted(false);
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
            List<Dict> dictList = dictMapper.selectBatchIds(ids);
            //删除字典
            count = dictMapper.deleteBatchIds(ids);
            Assert.isFalse(count == Constant.ZERO);
        } else if (CodeEnum.NO.getCode() == delStatus) {//状态删除
            List<Dict> dormList = new ArrayList<>(ids.size());
            ids.stream().forEach(id -> {
                Dict dict = new Dict();
                dict.setId(id);
                dict.setIsDeleted(true);
                dormList.add(dict);
            });
            this.updateBatchById(dormList);
        } else {
            throw new ServerException("删除状态码错误！");
        }
    }

    /**
     * 编辑字典验证
     *
     * @param editForm
     */
    private void editDictValidate(EditDictForm editForm) {
        if (StringUtils.isEmpty(editForm.getId())) {//新增验证
            Assert.notNull(editForm.getName());
            Assert.notNull(editForm.getCode());
        } else {//修改验证
            //TODO
        }
    }

}
