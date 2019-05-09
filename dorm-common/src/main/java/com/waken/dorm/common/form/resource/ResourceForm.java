package com.waken.dorm.common.form.resource;

import com.waken.dorm.common.form.base.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName ResourceForm
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/3/21 19:45
 **/
@ToString
@Getter
@Setter
public class ResourceForm extends BaseForm {
    private static final long serialVersionUID = -5910404465143943089L;
    private String pkResourceId;

    private String resourceName;

    private Integer resourceType;

    private Integer resourceNo;

    private String parentId;

    private Integer status;

    private Integer isParent;
}
