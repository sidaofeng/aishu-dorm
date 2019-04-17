package com.waken.dorm.common.view.base;

import io.swagger.annotations.ApiModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TreeView
 * @Description 树形视图
 * @Author zhaoRong
 * @Date 2019/4/6 21:58
 **/
@ApiModel(value = "TreeView", description = "树形视图")
public class TreeView {
    private String pkId;

    private String text;

    private List<TreeView> nodes = new ArrayList<>();

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<TreeView> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeView> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "TreeView{" +
                "pkId='" + pkId + '\'' +
                ", text='" + text + '\'' +
                ", nodes=" + nodes +
                '}';
    }
}
