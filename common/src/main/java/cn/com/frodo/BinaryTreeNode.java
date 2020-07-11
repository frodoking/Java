package cn.com.frodo;

/**
 * @author frodoking
 * @ClassName: BinaryTreeNode
 * @date 2020/7/11
 */
public class BinaryTreeNode {
    public int data;
    public BinaryTreeNode left = null;
    public BinaryTreeNode right = null;

    public BinaryTreeNode(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data + "";
    }
}
