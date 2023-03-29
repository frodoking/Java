package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.LinkedNode;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/palindrome-linked-list/
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 由于借用了系统递归的堆栈空间，因此空间复杂度是O(n)
 *
 * @author frodoking
 * @ClassName: LC234IsPalindrme
 * @date 2020/9/8
 */
@Deprecated
@AlgorithmPoint(tag = AlgorithmPoint.Tag.leetcode,
        difficulty = AlgorithmPoint.Difficulty.easy,
        category = AlgorithmPoint.Category.array)
public class LC234IsPalindrome implements IAlgorithm {

    private LinkedNode left = null;

    @Override
    public void exec() {
        LinkedNode linkedNode = LinkedNode.link(1,2,2,1);
        this.left = linkedNode;
        Assert.assertTrue(traverse(linkedNode));
    }

    private boolean traverse(LinkedNode right) {
        if (right == null) return true;
        boolean res = traverse(right.next);
        res = res & (left.data == right.data);
        left = left.next;
        return res;
    }
}
