package com.waken.dorm.common.view.dict;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName DictView
 * @Description DictView
 * @Author zhaoRong
 * @Date 2019/4/19 13:05
 **/
@ToString
@Getter
@Setter
public class DictView {
    private String pkDictId;

    private String dictKey;

    private String dictValue;

    private String columnName;

    private String columnDesc;

    private String tableName;

    private String tableDesc;

    private Integer status;

    private Date createTime;

    private String createUserName;

    private Date lastModifyTime;

    private String lastModifyUserName;
}
