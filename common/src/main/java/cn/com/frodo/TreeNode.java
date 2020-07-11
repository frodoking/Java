package cn.com.frodo;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author frodoking
 * @ClassName: TreeNode
 * @date 2020/7/10
 */
public class TreeNode {

    public int data;
    List<TreeNode> children = Lists.newArrayList();

    public TreeNode(int data) {
        this.data = data;
    }

    public void addChild(TreeNode node){
        children.add(node);
    }

}
