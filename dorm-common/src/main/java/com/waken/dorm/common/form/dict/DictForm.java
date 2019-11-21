package com.waken.dorm.common.form.dict;

import com.waken.dorm.common.form.base.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName DictForm
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/4/19 13:03
 **/
@ToString
@Getter
@Setter
public class DictForm extends BaseForm {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 父节点id
     */
    private String parentId;


    /**
     * 是否默认 0：否 1：是
     */
    private Boolean isDefault;

    /**
     * 关键字
     */
    private String keywords;
}
