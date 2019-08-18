package com.waken.dorm.common.view.base;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TreeView
 * @Description 树形视图
 * @Author zhaoRong
 * @Date 2019/4/6 21:58
 **/
@ApiModel(value = "TreeView", description = "树形视图")
@Getter
@Setter
@ToString
public class TreeView extends BaseView {
    private String pkId;

    private String text;

    private List<TreeView> nodes = new ArrayList<>();
}
