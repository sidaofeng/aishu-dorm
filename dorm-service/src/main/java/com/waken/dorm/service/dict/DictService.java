package com.waken.dorm.service.dict;

import com.baomidou.mybatisplus.extension.service.IService;
import com.waken.dorm.common.entity.basic.Dict;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.dict.EditDictForm;

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

}
