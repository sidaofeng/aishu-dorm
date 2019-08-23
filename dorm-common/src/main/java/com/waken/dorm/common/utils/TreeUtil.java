package com.waken.dorm.common.utils;

import com.waken.dorm.common.view.base.Tree;
import lombok.Data;
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

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
//        TreeUtil treeUtil = new TreeUtil();
//        treeUtil.testGetNodes();
//        treeUtil.testGetTree();
    }

    private void testGetNodes(){
        Map<Long,Long> map = new HashMap();
        for (Long i = 0L; i < 5000; i++) {
            map.put(i+1L,i);
        }
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
//        List<Long> nodes = TreeUtil.getNodes(map,ids);
        TreeUtil treeUtil = new TreeUtil();
        treeUtil.getAllNodes(map,ids);


//        System.out.println(nodes);
    }

    /**
     * 模拟获取树结构的测试数据
     */
    private void testGetTree(){
        List<TreeView> treeViewList = new ArrayList<>();
        TreeView t;
        for (Long i = 0L; i < 50000 ; i++) {
            t = new TreeView();
            t.setId(i+1);
            t.setPId(i);
            treeViewList.add(t);
        }
        List<TreeView> list = this.toTreeT(treeViewList,0L);
        System.out.println(list);
    }


    public List<TreeView> toTreeT(List<TreeView> treeList, Long rootName){
        //树根节点
        List<TreeView> rootList = new ArrayList<>();
        Iterator<TreeView> var3 = treeList.iterator();
        while (var3.hasNext()) {
            TreeView tree = var3.next();
            if (tree.getPId() == rootName) {
                rootList.add(tree);
            }
        }
        rootList.stream().forEach(root -> this.getChildrenT(root, treeList));
        return rootList;
    }

    /**
     * 找到子节点并设置进去
     *
     * @param rootTree
     * @param treeList
     */
    private void getChildrenT(TreeView rootTree, List<TreeView> treeList) {
        Iterator<TreeView> var3 = treeList.iterator();
        TreeView tree;
        List<TreeView> childrenTree = new ArrayList<>();
        while (var3.hasNext()) {
            tree = var3.next();
            if (rootTree.getId() ==  tree.getPId()){
                childrenTree.add(tree);
                rootTree.setChildren(childrenTree);
                var3.remove();
            }
        }
        if (!childrenTree.isEmpty()) {
            childrenTree.stream().forEach(childTree -> this.getChildrenT(childTree, treeList));
        }
    }




    private static List<Long> getNodes(Map<Long, Long> idAndPidMap, List<Long> ids) {
        //接收所有的子节点变量，并返回
        Set<Long> allNodes = new HashSet<>();
        allNodes.addAll(ids);
        //子节点集合变量，用于接收每次循环找出的子节点
        List<Long> children;
        //临时集合变量，用于接收每次循环找找出的子节点
        List<Long> temp;
        Set<Long> keys;
        Iterator<Long> var6;
        Long key;
        for (Long id : ids) {
            children = new ArrayList<>();
            children.add(id);
            temp = new ArrayList<>();
            //递归循环
            while (true) {
                for (Long childId : children) {
                    keys = idAndPidMap.keySet();
                    var6 = keys.iterator();
                    while (var6.hasNext()) {
                        key = var6.next();
                        //如果key这个节点的父节点id等于当前这个childId节点，说明key这个节点是childId这个节点的子节点，需要删除
                        if (idAndPidMap.get(key) == childId) {
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

    private void getAllNodes(Map<Long,Long> map,List<Long> ids){
        List<Long> all = new ArrayList<>();
        all.addAll(ids);
        ids.stream().forEach(id->{
            this.getChild(map,id,all);
        });
        System.out.println(all);
    }

    private void getChild(Map<Long,Long> map,Long id,List<Long> all){
        List<Long> children = new ArrayList<>();
        map.entrySet().stream().forEach(entry->{
            if (entry.getValue() == id){
                children.add(entry.getKey());
                all.add(entry.getKey());
//                map.remove(entry.getKey());
            }
        });
        if (!children.isEmpty()){
            children.stream().forEach(child->{
                this.getChild(map,child,all);
            });

        }
    }
}



/**
 * 测试树视图
 */
@Data
class TreeView {
    private Long id;
    private Long pId;
    private boolean isLeaf = false;
    private List<TreeView> children = new ArrayList<>();
}