package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.Arrays;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 31. 下一个排列
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 *
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 *
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 *
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 *
 * @author frodoking
 * @ClassName: LC31NextPermutation
 * @date 2022/3/13
 */
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.hard,
        company = {AlgorithmPoint.Company.bytedance})
@Deprecated
public class LC31NextPermutation implements IAlgorithm {

    @Override
    public void exec() {
        final int[] array = {1, 3, 2};
        Arrays.show(array, "Before");

        nextPermutation(array);

        Arrays.show(array, "After");
    }

    // 思路
    // 1. 从后往前找到最后一个升序位置
    // 2. 再基于这个点找后边第一个大于该值的点
    // 3. 交换位置，后续剩余节点一定是降序的，再逆转成升序。那么这一定就是最近一次大于的序列
    public void nextPermutation(int[] nums) {
        int size = nums.length;
        if (size == 1) {
            return;
        }

        int index = size - 1;

        while (index > 0 && nums[index - 1] >= nums[index]) {
            index--;
        }

        if (index == size - 1) {
            Arrays.swap(nums, index, index - 1);
            return;
        }

        if (index == 0) {
            java.util.Arrays.sort(nums);
            return;
        }

        int changeIndex = size - 1;
        while (changeIndex > 0 && nums[changeIndex] <= nums[index-1]) {
            changeIndex--;
        }
        Arrays.swap(nums, index - 1, changeIndex);

        java.util.Arrays.sort(nums, index, size);
    }

}
