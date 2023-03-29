package cn.com.frodo;

import java.util.*;

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
                if (node1 == null) {
                    continue;
                }
                if (i < array.length && array[i] != _null.data) {
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

    public void print() {
        int maxLevel = depth();
        printNodeInternal(Collections.singletonList(this), 1, maxLevel);
    }

    private static void printNodeInternal(List<BinaryTreeNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        List<BinaryTreeNode> newNodes = new ArrayList<>();
        for (BinaryTreeNode node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    printWhitespaces(1);

                printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    printWhitespaces(1);

                printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private int depth() {
        return depth(this);
    }

    private static int depth(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }
        return true;
    }

}
