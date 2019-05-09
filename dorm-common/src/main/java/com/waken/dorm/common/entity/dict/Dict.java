package com.waken.dorm.common.entity.dict;

import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class Dict extends BaseEntity {
    private String pkDictId;

    private String dictKey;

    private String dictValue;

    private String columnName;

    private String columnDesc;

    private String tableName;

    private String tableDesc;

    private Integer status;

    private String schoolId;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

    public String getPkDictId() {
        return pkDictId;
    }

    public void setPkDictId(String pkDictId) {
        this.pkDictId = pkDictId == null ? null : pkDictId.trim();
    }

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }
}