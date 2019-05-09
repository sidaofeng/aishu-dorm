package com.waken.dorm.common.form.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName EditResourceForm
 * @Description EditResourceForm
 * @Author zhaoRong
 * @Date 2019/4/4 14:08
 **/
@ToString
@Getter
@Setter
public class EditResourceForm {
    private String pkResourceId;

    private String resourceName;

    private Integer resourceType;

    private String resourceUrl;

    private Integer resourceNo;

    private String parentId;
}
