package com.waken.dorm.common.form.resource;

/**
 * @ClassName ResourceTreeForm
 * @Description ResourceTreeForm
 * @Author zhaoRong
 * @Date 2019/4/5 22:23
 **/
public class ResourceTreeForm {
    private String pkResourceId;

    public String getPkResourceId() {
        return pkResourceId;
    }

    public void setPkResourceId(String pkResourceId) {
        this.pkResourceId = pkResourceId;
    }

    @Override
    public String toString() {
        return "ResourceTreeForm{" +
                "pkResourceId='" + pkResourceId + '\'' +
                '}';
    }
}
