package com.waken.dorm.common.form.dict;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

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
    private String pkDictId;

    private String schoolId;

    private String dictKey;

    private String dictValue;

    private String columnName;

    private String columnDesc;

    private String tableName;

    private String tableDesc;
}
