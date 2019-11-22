package com.waken.dorm.service.dict;

import com.baomidou.mybatisplus.extension.service.IService;
import com.waken.dorm.common.entity.basic.Dict;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dict.EditDictForm;
import com.waken.dorm.common.view.base.Tree;
import com.waken.dorm.common.view.dict.DictView;

import java.util.List;

/**
 * @ClassName DictService
 * @Description 字典业务
 * @Author zhaoRong
 * @Date 2019/4/19 12:50
 **/
public interface DictService extends IService<Dict> {
    /**
     * 保存或修改系统字典
     *
     * @param editDictForm
     * @return
     */
    Dict saveDict(EditDictForm editDictForm);

    /**
     * 删除字典
     *
     * @param deleteForm
     */
    void deleteDict(DeleteForm deleteForm);

    /**
     * 查询字典根节点的集合
     *
     * @param name 模糊搜索（name\code）
     * @return
     */
    List<DictView> getDictRootList(String name);

    /**
     * 通过父ID查询对应对应的字典树
     *
     * @param parentId 父ID
     * @return
     */
    Tree<DictView> getTreeByParentId(String parentId);

    /**
     * 通过父节点编码查询字典树
     *
     * @param parentCode
     * @return
     */
    Tree<DictView> getTreeByParentCode(String parentCode);

    /**
     * 通过父节点编码查询字典下一级的字典集合（只查下一级）
     *
     * @param parentCode
     * @return
     */
    List<DictView> getDictByParentCode(String parentCode);

}
