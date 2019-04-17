package com.waken.dorm.common.form.log;

import com.waken.dorm.common.form.base.BaseForm;

import java.util.Date;

/**
 * @ClassName SysLogForm
 * @Description SysLogForm
 * @Author zhaoRong
 * @Date 2019/4/16 13:03
 **/
public class SysLogForm extends BaseForm {
    private String pkLogId;

    private String keyWord;

    public String getPkLogId() {
        return pkLogId;
    }

    public void setPkLogId(String pkLogId) {
        this.pkLogId = pkLogId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String toString() {
        return "SysLogForm{" +
                "pkLogId='" + pkLogId + '\'' +
                ", keyWord='" + keyWord + '\'' +
                '}';
    }
}
