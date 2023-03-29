package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import com.google.common.base.Splitter;

import java.util.Objects;

/**
 * 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 * <p>
 * 输入：root = [1,2]
 * 输出：[1,2]
 */
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.offer},
        difficulty = AlgorithmPoint.Difficulty.hard,
        category = AlgorithmPoint.Category.tree,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.dfs))
public class LC297SerializeAndDeserializeBinaryTree implements IAlgorithm {

    @Override
    public void exec() {
        int[] root = {1, 2, 3, BinaryTreeNode._null.data, BinaryTreeNode._null.data, 4, 5};
        BinaryTreeNode binaryTreeNode = BinaryTreeNode.build(root);
        binaryTreeNode.print();
        CodecDFS codecDFS = new CodecDFS();
        String serialize = codecDFS.serialize(binaryTreeNode);

        BinaryTreeNode deserializeNode = codecDFS.deserialize(serialize);
        deserializeNode.print();

        System.out.println(serialize);
    }

    public static class CodecDFS {

        // Encodes a tree to a single string.
        public String serialize(BinaryTreeNode root) {
            if (root == null) {
                return "X";
            }

            return root.data + "," + serialize(root.left) + "," + serialize(root.right);
        }

        // Decodes your encoded data to tree.
        private int index = 0;
        public BinaryTreeNode deserialize(String data) {
            String[] array = Splitter.on(',').splitToList(data).toArray(new String[0]);
            return deserialize(array);
        }

        public BinaryTreeNode deserialize(String[] array) {
            System.out.println("cur = " + index + " = " + array[index]);
            if (Objects.equals(array[index], "X") || index > array.length) {
                index++;
                return null;
            }
            BinaryTreeNode root = new BinaryTreeNode(Integer.parseInt(array[index]));
            index++;
            root.left = deserialize(array);
            root.right = deserialize(array);
            return root;
        }

    }
}
