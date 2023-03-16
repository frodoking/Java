package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 * <p>
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */
@Deprecated
@LCPoint(difficulty = LCPoint.Difficulty.medium,
        category = LCPoint.Category.array)
public class LC198Rob implements IAlgorithm {

    public Map<Integer, Integer> cache = new HashMap<>();

    @Override
    public void exec() {
        int[] array = {1, 2, 3, 1};
        Assert.assertEquals(4, rob(array));
    }

    public int rob(int[] nums) {
        return rob(nums, 0);
    }

    public int rob(int[] nums, int i) {
        if (i > nums.length - 1) return 0;

        int result = 0;
        if (i == nums.length - 1) {
            result = nums[i];
            return result;
        }

        if (cache.containsKey(i)) {
            return cache.get(i);
        }

        result = Math.max(nums[i] + rob(nums, i + 2), rob(nums, i + 1));
        cache.put(i, result);
        return result;
    }


}
