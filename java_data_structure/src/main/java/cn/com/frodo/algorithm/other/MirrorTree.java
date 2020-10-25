package cn.com.frodo.algorithm.other;

import cn.com.frodo.BinaryTreeNode;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * @author frodoking
 * @ClassName: MirrorTree
 * @date 2020/10/25
 */
public class MirrorTree implements IAlgorithm {
    @Override
    public void exec() {

    }

    public BinaryTreeNode mirrorTree(BinaryTreeNode root) {
        if (root == null) return null;
        BinaryTreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return root;
    }

}
