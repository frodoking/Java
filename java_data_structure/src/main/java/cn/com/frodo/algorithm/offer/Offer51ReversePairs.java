package cn.com.frodo.algorithm.offer;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.Arrays;

/**
 * 剑指 Offer 51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,5,6,4]
 * 输出: 5
 * <p>
 * 限制：
 * <p>
 * 0 <= 数组长度 <= 50000
 */
@AlgorithmPoint(tag = AlgorithmPoint.Tag.offer,
        difficulty = AlgorithmPoint.Difficulty.hard,
        category = AlgorithmPoint.Category.array)
public class Offer51ReversePairs implements IAlgorithm {

    @Override
    public void exec() {
        int[] nums = {233, 2000000001, 234, 2000000006, 235, 2000000003, 236, 2000000007, 237, 2000000002, 2000000005, 233, 233, 233, 233, 233, 2000000004};

        Assert.assertEquals(69, reversePairs(nums));
    }


    public int reversePairs(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }

        return reversePairs(nums, 0, len - 1, new int[len]);
    }

    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }
        int mid = left + (right - left) / 2;

        int leftCount = reversePairs(nums, left, mid, temp);
        int rightCount = reversePairs(nums, mid + 1, right, temp);

        if (nums[mid] <= nums[mid + 1]) {
            return leftCount + rightCount;
        }

        int midCount = mergeAndCount(nums, left, mid, right, temp);

        return leftCount + rightCount + midCount;
    }

    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        int first = left;
        int second = mid + 1;

        int count = 0;
        for (int i = left; i <= right; i++) {
            if (first == mid + 1) {
                nums[i] = temp[second];
                second++;
            } else if (second == right + 1) {
                nums[i] = temp[first];
                first++;
            } else if (temp[first] <= temp[second]) {
                nums[i] = temp[first];
                first++;
            } else {
                nums[i] = temp[second];
                second++;

                count += (mid - first + 1);
            }
        }
        System.out.println("left = " + left + ", mid = " + mid + ", right = " + right + ", count = " + count + ", nums = " + Arrays.toString(nums));
        return count;
    }

}
