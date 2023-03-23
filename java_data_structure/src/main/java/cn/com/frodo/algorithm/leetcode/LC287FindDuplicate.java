package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 * <p>
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 * <p>
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 */
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.array)
public class LC287FindDuplicate implements IAlgorithm {

    @Override
    public void exec() {
        int[] nums = {1,3,4,2,2};

        Assert.assertEquals(2, findDuplicate(nums));

    }

    /**
     * 思路：
     * 转化成链表：0->1
     *           1->3
     *           2->4
     *           3->2
     *           4->2
     *
     *           则会形成：0->1 ->3 ->2 ->4 ->2 的链表
     *
     *           单链表如果有环的话，那么slow，fast会相遇
     *           slow++ 向前走一步对应：slow.next = nums[slow]
     *           fast+2 向前走两步对应：fast.next.next = nums[nums[fast]]
     */
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
