package com.waken.dorm.service.dict;

import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.entity.dict.Dict;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dict.DictForm;
import com.waken.dorm.common.form.dict.EditDictForm;
import com.waken.dorm.common.view.dict.DictView;

/**
 * @ClassName DictService
 * @Description 字典业务
 * @Author zhaoRong
 * @Date 2019/4/19 12:50
 **/
public interface DictService {
    /**
     * 保存或修改系统字典
     * @param editDictForm
     * @return
     */
    public Dict saveDict(EditDictForm editDictForm);

    /**
     * 删除字典
     * @param deleteForm
     */
    public void deleteDict(DeleteForm deleteForm);

    /**
     * 分页查询字典
     * @param dictForm
     * @return
     */
    public PageInfo<DictView> listDicts(DictForm dictForm);

}
