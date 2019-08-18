package com.waken.dorm.common.view.base;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 树形视图
 * @Author zhaoRong
 * @Date 2019/8/9 20:16
 **/
@Getter
@Setter
@ApiModel(value = "Tree", description = "Ant树形结构")
public class Tree<T> implements Serializable {
    private static final long serialVersionUID = 7932221411557490900L;

    /**
     * 树节点id
     */
    private String id;

    /**
     * 节点唯一标识,在整个树范围内唯一
     */
    private String key;

    /**
     * 树节点显示的内容
     */
    private String title;

    /**
     * 树节点展示的图标
     */
    private String icon;

    /**
     * 排序序号
     */
    private Integer sort;

    /**
     * 是否是叶子节点
     */
    private boolean isLeaf;

    /**
     * 是否选中树节点，默认false
     */
    private boolean checked = false;

    /**
     * 是否打开节点，默认false
     */
    private boolean open = false;

    /**
     * 树节点对应的视图信息
     */
    private T attribute;

    /**
     * 子树节点
     */
    private List<Tree<T>> children = new ArrayList<>();
}
