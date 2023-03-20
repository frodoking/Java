package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.Arrays;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 238. 除自身以外数组的乘积
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * <p>
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * <p>
 * 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 * 示例 2:
 * <p>
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 */
@LCPoint(difficulty = LCPoint.Difficulty.medium,
        category = LCPoint.Category.array)
public class LC238ProductExceptSelf implements IAlgorithm {
    @Override
    public void exec() {
        int[] nums = {1, 2, 3, 4};
        int[] res = productExceptSelf(nums);

        Arrays.show(res, "LC238ProductExceptSelf");
    }

    /**
     * 思路：前序乘积 乘以 后续乘积
     */
    public int[] productExceptSelf(int[] nums) {
        int size = nums.length;
        int[] prefix = new int[size];

        prefix[0] = 1;
        for (int i = 1; i < size; i++) {
            prefix[i] = nums[i - 1] * prefix[i - 1];
        }

        int[] suffix = new int[size];

        suffix[size - 1] = 1;
        for (int i = size - 2; i >= 0; i--) {
            suffix[i] = nums[i + 1] * suffix[i + 1];
        }

        for (int i = 0; i < size; i++) {
            nums[i] = prefix[i] * suffix[i];
        }

        return nums;
    }
}
