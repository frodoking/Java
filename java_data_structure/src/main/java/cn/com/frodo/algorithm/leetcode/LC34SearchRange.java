package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.Arrays;
import cn.com.frodo.algorithm.IAlgorithm;


/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 * @author frodoking
 * @ClassName: LC34SearchRange
 * @date 2022/3/13
 */
@LCPoint
@Deprecated
public class LC34SearchRange implements IAlgorithm {
    @Override
    public void exec() {
        int[] nums = {5, 7, 8, 8, 8, 10};
        int[] res = searchRange(nums, 8);
        Arrays.show(res, "LC34SearchRange");
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        if (nums[0] > target) {
            return new int[]{-1, -1};
        }
        int first = findFirstLocation(nums, target);
        if (first == -1) {
            return new int[]{-1, -1};
        }
        int last = findLastLocation(nums, target);

        return new int[]{first, last};
    }

    public int findFirstLocation(int[] nums, int target) {
        int size = nums.length;
        int l = 0;
        int r = size - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > target) {
                r = mid - 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                if (mid == 0 || nums[mid - 1] != target) {
                    return mid;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    public int findLastLocation(int[] nums, int target) {
        int size = nums.length;
        int l = 0;
        int r = size - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > target) {
                r = mid - 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                if (mid == size - 1 || nums[mid + 1] != target) {
                    return mid;
                } else {
                    l = mid + 1;
                }
            }
        }
        return l;
    }
}
