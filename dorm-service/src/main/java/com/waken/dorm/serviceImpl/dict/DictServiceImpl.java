package com.waken.dorm.serviceImpl.dict;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import com.waken.dorm.common.view.base.Tree;
import com.waken.dorm.common.view.dict.DictView;
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
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName DictServiceImpl
 * @Description 字典业务
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
        this.validate(editDictForm);
        Dict dict = new Dict();
        BeanMapper.copy(editDictForm, dict);
        if (StringUtils.isEmpty(editDictForm.getId())) {
            //新增
            dict.setIsDeleted(false);
            int count = dictMapper.insert(dict);
            Assert.isFalse(count == Constant.ZERO);
            return dict;
        } else {
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
    public void deleteDict(DeleteForm deleteForm) {
        log.info("service: 删除字典信息开始");
        List<String> ids = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count;
        if (CodeEnum.YES.getCode().equals(delStatus)) {
            // 物理删除
            count = dictMapper.deleteBatchIds(ids);
            Assert.isFalse(count == Constant.ZERO);
        } else if (CodeEnum.NO.getCode().equals(delStatus)) {
            //状态删除
            List<Dict> dictList = new ArrayList<>(ids.size());
            ids.stream().forEach(id -> {
                Dict dict = new Dict();
                dict.setId(id);
                dict.setIsDeleted(true);
                dictList.add(dict);
            });
            this.updateBatchById(dictList);
        } else {
            throw new ServerException("删除状态码错误！");
        }
    }

    /**
     * 查询字典根节点的集合
     *
     * @param name 模糊搜索（name\code）
     * @return
     */
    @Override
    public List<DictView> getDictRootList(String name) {
        return null;
    }

    /**
     * 通过父ID查询对应对应的字典树
     *
     * @param parentId 父ID
     * @return
     */
    @Override
    public Tree<DictView> getTreeByParentId(String parentId) {
        return null;
    }

    /**
     * 通过父节点编码查询字典树
     *
     * @param parentCode
     * @return
     */
    @Override
    public Tree<DictView> getTreeByParentCode(String parentCode) {
        return null;
    }

    /**
     * 通过父节点编码查询字典下一级的字典集合（只查下一级）
     *
     * @param parentCode
     * @return
     */
    @Override
    public List<DictView> getDictByParentCode(String parentCode) {
        return null;
    }

    /**
     * 编辑字典验证
     *
     * @param editForm
     */
    private void validate(EditDictForm editForm) {
        String parentId = editForm.getParentId();
        if (StringUtils.isEmpty(parentId)) {
            parentId = Constant.ROOT;
        }
        List<Dict> dictList = this.baseMapper.selectList(new LambdaQueryWrapper<Dict>().eq(Dict::getParentId, parentId));
        if (dictList == null || dictList.isEmpty()) {
            return;
        }
        Map<String, String> codeAndIdMap = dictList.stream().collect(Collectors.toMap(Dict::getCode, Dict::getId));
        Map<String, String> nameAndIdMap = dictList.stream().collect(Collectors.toMap(Dict::getName, Dict::getId));
        String name = editForm.getName();
        String code = editForm.getCode();
        String id = editForm.getCode();
        if (StringUtils.isEmpty(id)) {
            //新增验证
            Assert.notNull(name);
            Assert.notNull(code);
            if (codeAndIdMap.containsKey(code)) {
                throw new ServerException("字典编码不能重复！");
            }
            if (nameAndIdMap.containsKey(name)) {
                throw new ServerException("字典名称不能重复！");
            }
        } else {//修改验证
            if (StringUtils.isNotEmpty(name)) {
                if (StringUtils.isNotEmpty(nameAndIdMap.get(name)) && !StringUtils.equals(nameAndIdMap.get(name), id)) {
                    throw new ServerException("字典名称不能重复");
                }
                if (StringUtils.isNotEmpty(codeAndIdMap.get(code)) && !StringUtils.equals(codeAndIdMap.get(code), id)) {
                    throw new ServerException("字典名称不能重复");
                }
            }
        }
    }

}
