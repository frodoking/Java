package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 494. 目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 * <p>
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * <p>
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * 示例 2：
 * <p>
 * 输入：nums = [1], target = 1
 * 输出：1
 */
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.tree)
public class LC494FindTargetSumWays implements IAlgorithm {
    @Override
    public void exec() {
        int[] nums = {1,0};
        int target = 1;

        Assert.assertEquals(2, findTargetSumWays(nums, target));
    }

    private AtomicInteger counter = new AtomicInteger();

    public int findTargetSumWays(int[] nums, int target) {
        if (nums.length == 1 && (nums[0] + target == 0 || nums[0] - target == 0)) {
            return 1;
        }
        dfs(nums, 0, target);

        return counter.get();
    }

    public void dfs(int[] nums, int index, int target) {
        if (index >= nums.length) {
            if (target == 0) {
                counter.incrementAndGet();
            }
        } else {
            System.out.println("index = " + index + ", target = " + target);

            int num = nums[index];
            dfs(nums, index + 1, target + num);
            dfs(nums, index + 1, target - num);
        }
    }
}
