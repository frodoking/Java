package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 167. 两数之和 II - 输入有序数组
 * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
 * <p>
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 * <p>
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 * <p>
 * 你所设计的解决方案必须只使用常量级的额外空间。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 * 示例 2：
 * <p>
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 * 解释：2 与 4 之和等于目标数 6 。因此 index1 = 1, index2 = 3 。返回 [1, 3] 。
 * 示例 3：
 * <p>
 * 输入：numbers = [-1,0], target = -1
 * 输出：[1,2]
 * 解释：-1 与 0 之和等于目标数 -1 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 */
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.interview},
        difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.array,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.twoPointers))
public class LC167TwoSumIIInputArrayIsSorted implements IAlgorithm {
    @Override
    public void exec() {
        int[] nums = {2, 7, 11, 15};
        int[] expected = {2, 7};
        Assert.assertEquals(expected, twoSum(nums, 9));
    }

    public int[] twoSum(int[] numbers, int target) {
        int len = numbers.length;
        int s = 0;
        int e = len - 1;
        while (s < e && numbers[s] + numbers[e] != target) {
            if (numbers[s] + numbers[e] < target) {
                s++;
            } else {
                e--;
            }
        }
        return new int[]{s + 1, e + 1};
    }

}
