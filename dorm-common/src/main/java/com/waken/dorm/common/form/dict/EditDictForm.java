package com.waken.dorm.common.form.dict;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName EditDictForm
 * @Description 编辑字典form表单
 * @Author zhaoRong
 * @Date 2019/4/19 12:54
 **/
@ToString
@Getter
@Setter
public class EditDictForm {
    /**
     * 主键id
     */
    private String id;

    /**
     * 父节点id
     */
    private String parentId;

    /**
     * 字典数据名称
     */
    private String name;

    /**
     * 字典数据编码
     */
    private String code;

    /**
     * 是否默认 0：否 1：是
     */
    private Boolean isDefault;

    /**
     * 字典描述
     */
    private String description;

}
