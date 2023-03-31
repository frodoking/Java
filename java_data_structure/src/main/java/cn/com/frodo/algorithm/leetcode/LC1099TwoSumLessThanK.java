package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.Arrays;

/**
 * 给你一个整数数组 A 和一个整数 K，请在该数组中找出两个元素，使它们的和小于 K 但尽可能地接近 K，返回这两个元素的和。
 *
 * 如不存在这样的两个元素，请返回 -1。
 *
 * 示例 1：
 *
 * 输入：A = [34,23,1,24,75,33,54,8], K = 60
 * 输出：58
 * 解释：
 * 34 和 24 相加得到 58，58 小于 60，满足题意。
 * 1
 * 2
 * 3
 * 4
 * 示例 2：
 *
 * 输入：A = [10,20,30], K = 15
 * 输出：-1
 * 解释：
 * 我们无法找到和小于 15 的两个元素。
 * 1
 * 2
 * 3
 * 4
 * 提示：
 *
 * 1 <= A.length <= 100
 * 1 <= A[i] <= 1000
 * 1 <= K <= 2000
 */
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.interview, AlgorithmPoint.Tag.unlearn},
        difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.array,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.twoPointers))
public class LC1099TwoSumLessThanK implements IAlgorithm {

    @Override
    public void exec() {
       int[] A = {34,23,1,24,75,33,54,8};
       int K = 60;

        Assert.assertEquals(58, twoSumLessThanK(A, K));
    }

    public int twoSumLessThanK(int[] numbers, int target) {
        Arrays.sort(numbers);
        int len = numbers.length;
        int s = 0;
        int e = len - 1;
        while (s < e) {
            if (numbers[s] + numbers[e] < target) {
                s++;
            } else {
                e--;
            }
        }
        System.out.println(s+"-"+ e);
        return numbers[s] + numbers[e];
    }
}
