package com.waken.dorm.common.view.resource;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ResourceTreeView
 * @Description 资源树视图
 * @Author zhaoRong
 * @Date 2019/4/5 22:14
 **/
@ApiModel(value = "ResourceTreeView", description = "资源树视图")
@Getter
@Setter
public class ResourceTreeView {
    private String pkResourceId;

    private String resourceName;

    private List<ResourceTreeView> childResourceTreeView = new ArrayList<ResourceTreeView>();
}
