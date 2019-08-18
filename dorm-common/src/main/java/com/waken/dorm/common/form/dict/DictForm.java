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
    private String pkDictId;

    private String dictKey;

    private String dictValue;

    private String columnName;

    private String columnDesc;

    private String tableName;

    private String tableDesc;

    private Integer status;

    private String createUserName;

    private String lastModifyUserName;
}
