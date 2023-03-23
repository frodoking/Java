package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 * <p>
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 * <p>
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 * <p>
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 * <p>
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 * <p>
 * <p>
 * 提示：
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 * <p>
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author frodoking
 * @ClassName: LC4FindMedianSortedArrays
 * @date 2020/10/25
 */
@Deprecated
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium, company = { AlgorithmPoint.Company.xiaohongshu},
        category = AlgorithmPoint.Category.array)
public class LC4FindMedianSortedArrays implements IAlgorithm {
    @Override
    public void exec() {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        double d = findMedianSortedArrays2(nums1, nums2);
        System.out.println(d);
    }

    // 时间复杂度不够
    @Deprecated
    private double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int start1 = 0;
        int start2 = 0;
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        int left = -1, right = -1;
        for (int i = 0; i < len / 2 + 1; i++) {
            left = right;
            if (start1 < len1 && (start2 >= len2 || nums1[start1] <= nums2[start2])) {
                right = nums1[start1++];
            } else {
                right = nums2[start2++];
            }
        }

        if ((len & 1) == 0) {
            return (left + right) / 2.0;
        } else {
            return right;
        }
    }

    private double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays2(nums2, nums1);
        }

        int len1 = nums1.length;
        int len2 = nums2.length;

        int left = 0;
        int right = nums1.length;

        int index1 = 0;
        int index2 = 0;
        int totalLeft = (len1 + len2 + 1) >> 1;
        while (left < right) {
            // 下一轮搜索的游标等于开始位置+区间大小除2
            index1 = left + (right - left + 1) >> 1;
            index2 = totalLeft - index1;

            // 找小了
            if (nums1[index1 - 1] > nums2[index2]) {
                right = index1 - 1;
            } else {
                left = index1;
            }
        }

        index1 = left;
        index2 = totalLeft - index1;

        int L1 = index1 == 0 ? Integer.MIN_VALUE : nums1[index1 - 1];
        int R1 = index1 == len1 ? Integer.MAX_VALUE : nums1[index1];

        int L2 = index2 == 0 ? Integer.MIN_VALUE : nums2[index2 - 1];
        int R2 = index2 == len2 ? Integer.MAX_VALUE : nums2[index2];

        return (len1 + len2) % 2 == 1 ?
                Math.max(L1, L2) :
                ((double) (Math.max(L1, L2) + Math.min(R1, R2))) / 2;
    }
}
