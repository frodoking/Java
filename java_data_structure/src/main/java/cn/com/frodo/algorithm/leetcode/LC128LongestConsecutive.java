package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * <p>
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 */
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.array,
        company = AlgorithmPoint.Company.bytedance)
public class LC128LongestConsecutive implements IAlgorithm {

    @Override
    public void exec() {
        int[] nums = {100, 4, 200, 2, 3, 1};
        System.out.println(longestConsecutive(nums));
    }

    private Map<Integer, Integer> map = new HashMap<>();

    public int longestConsecutive(int[] nums) {
        int maxLength = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                int left = map.getOrDefault(num - 1, 0);
                int right = map.getOrDefault(num + 1, 0);
                int length = left + right + 1;

                maxLength = Math.max(length, maxLength);

                map.put(num, length);
                // 为何要更新两边端点的值，是因为后续搜索肯定是连续搜索，不会从中间再来链接，所以不用更新中间的值
                map.put(num - left, length);
                map.put(num + right, length);
            }
        }
        return maxLength;
    }

}
