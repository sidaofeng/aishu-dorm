package com.waken.dorm.dao.basic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.waken.dorm.common.entity.basic.Dict;
import com.waken.dorm.common.view.dict.DictView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictMapper extends BaseMapper<Dict> {
    /**
     * 查询字典的集合
     *
     * @param parentId 父级ID
     * @param keywords 模糊搜索（name\code）
     * @return
     */
    List<DictView> getDictList(@Param("parentId") String parentId,
                               @Param("keywords") String keywords);

    /**
     * 通过根节点查询字典下一级的字典集合（只查根节点下一级）
     *
     * @param rootCode
     * @return
     */
    List<DictView> getDictItemsByRoot(@Param("rootCode") String rootCode);

}