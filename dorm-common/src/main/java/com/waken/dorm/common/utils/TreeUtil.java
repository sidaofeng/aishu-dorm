package com.waken.dorm.common.utils;

import com.waken.dorm.common.view.base.Tree;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description 树工具类
 * @Author zhaoRong
 * @Date 2019/8/15 23:01
 **/
@Service
public class TreeUtil {
    /**
     * 将树集合对象转为树
     * @param treeList 树形集合（子节点）
     * @param rootName 树的跟节点名称，根据具体系统的设计而言，此系统中的根节点统一为 "root"
     * @return
     */
    public <T> List<Tree<T>> toTree(List<Tree<T>> treeList,String rootName){
        //树根节点
        List<Tree<T>> rootList = new ArrayList<>();
        Iterator<Tree<T>> var3 = treeList.iterator();
        while (var3.hasNext()) {
            Tree tree = var3.next();
            if (StringUtils.equals(tree.getParentId(), rootName)) {
                rootList.add(tree);
            }
        }
        rootList.stream().forEach(root -> this.getChildren(root, treeList));
        return rootList;
    }

    /**
     * 找到子节点并设置进去
     *
     * @param rootTree
     * @param treeList
     */
    private <T> void getChildren(Tree<T> rootTree, List<Tree<T>> treeList) {
        Iterator<Tree<T>> var3 = treeList.iterator();
        Tree<T> tree;
        List<Tree<T>> childrenTree = new ArrayList<>();
        while (var3.hasNext()) {
            tree = var3.next();
            if (StringUtils.equals(rootTree.getId(), tree.getParentId())) {
                childrenTree.add(tree);
                rootTree.setChildren(childrenTree);
                var3.remove();
            }
        }
        if (!childrenTree.isEmpty()) {
            childrenTree.stream().forEach(childTree -> this.getChildren(childTree, treeList));
        }
    }

    /**
     * 查询出tree指定节点下的所有的子节点
     *
     * @param idAndPidMap(k=id,v=pid)
     * @param ids 主键id集合
     * @return
     */
    public static List<String> getNodesByIds(Map<String, String> idAndPidMap, List<String> ids) {
        //接收所有的子节点变量，并返回
        Set<String> allNodes = new HashSet<>();
        allNodes.addAll(ids);
        //子节点集合变量，用于接收每次循环找出的子节点
        List<String> children;
        //临时集合变量，用于接收每次循环找找出的子节点
        List<String> temp;
        Set<String> keys;
        Iterator<String> var6;
        String key;
        for (String id : ids) {
            children = new ArrayList<>();
            children.add(id);
            temp = new ArrayList<>();
            //递归循环
            while (true) {
                for (String childId : children) {
                    keys = idAndPidMap.keySet();
                    var6 = keys.iterator();
                    while (var6.hasNext()) {
                        key = var6.next();
                        //如果key这个节点的父节点id等于当前这个childId节点，说明key这个节点是childId这个节点的子节点，需要删除
                        if (StringUtils.equals(idAndPidMap.get(key), childId)) {
                            allNodes.add(key);
                            temp.add(key);
                            var6.remove();//移除当前这个节点
                        }
                    }
                }
                //如果temp集合不为空，说明存在子节点，则需要将子节点赋值给children集合变量，用于继续遍历children找到其节点
                if (!temp.isEmpty()) {
                    children = temp;
                    temp = new ArrayList<>();
                } else {//递归退出条件
                    break;
                }
            }
        }
        return new ArrayList<>(allNodes);
    }
}
