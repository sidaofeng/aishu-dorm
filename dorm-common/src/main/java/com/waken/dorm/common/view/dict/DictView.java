package com.waken.dorm.common.view.dict;

import com.waken.dorm.common.view.base.BaseView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName DictView
 * @Description DictView
 * @Author zhaoRong
 * @Date 2019/4/19 13:05
 **/
@ToString
@Getter
@Setter
public class DictView extends BaseView {
    /**
     * 主键id
     */
    private String id;

    /**
     * 父节点id
     */
    private String parentId;

    /**
     * 字典数据编码
     */
    private String code;

    /**
     * 字典数据名称
     */
    private String name;

    /**
     * 是否默认 0：否 1：是
     */
    private Boolean isDefault;

    /**
     * 排序sort
     */
    private Integer sort;

    /**
     * 字典描述
     */
    private String description;

}
