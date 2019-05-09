package com.waken.dorm.common.view.resource;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ResourceView
 * @Description ResourceView
 * @Author zhaoRong
 * @Date 2019/4/6 22:27
 **/
@Getter
@Setter
public class ResourceView {
    private String pkResourceId;

    private String resourceName;

    private Integer resourceType;

    private String resourceUrl;

    private Integer resourceNo;
}
