package com.waken.dorm.dao.dict;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waken.dorm.common.entity.dict.Dict;
import com.waken.dorm.common.form.dict.DictForm;
import com.waken.dorm.common.view.dict.DictView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DictMapper extends BaseMapper<Dict> {
    int batchUpdateStatus(Map<String, Object> param);

    IPage<DictView> listDicts(Page page,@Param("form") DictForm dictForm);

    List<Dict> selectByIds(@Param("Ids") List<String> Ids);//通过批量Ids批量查询
}