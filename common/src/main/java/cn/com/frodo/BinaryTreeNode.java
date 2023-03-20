package cn.com.frodo;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author frodoking
 * @ClassName: BinaryTreeNode
 * @date 2020/7/11
 */
public class BinaryTreeNode {
    public static final BinaryTreeNode _null = new BinaryTreeNode(Integer.MAX_VALUE);
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

    public static BinaryTreeNode build(int[] array) {
        BinaryTreeNode root = new BinaryTreeNode(array[0]);
        Queue<BinaryTreeNode> level = new ArrayDeque<>();
        level.add(root);

        int index = 1;
        while (index < array.length) {
            int size = level.size() * 2;
            for (int i = index; i < index + size; i++) {
                BinaryTreeNode node1 = level.poll();
                if (array[i] != _null.data) {
                    node1.left = new BinaryTreeNode(array[i]);
                    level.add(node1.left);
                }

                i++;
                if (i < array.length && array[i] != _null.data) {
                    node1.right = new BinaryTreeNode(array[i]);
                    level.add(node1.right);
                }
            }
            index += size;
        }

        return root;
    }
}
