package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import static cn.com.frodo.Arrays.swap;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 复杂度分析：
 * <p>
 * 时间复杂度：O(N)，这里 N 是数组的长度，理由可以参考本题解下用户 @ZLW 的评论，需要使用主定理进行分析。
 * 空间复杂度：O(1)，原地排序，没有借助额外的辅助空间。
 *
 * @author frodoking
 * @ClassName: LC215FindKthLargest
 * @date 2020/10/17
 */
@Deprecated
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium, company = AlgorithmPoint.Company.tencent,
        category = AlgorithmPoint.Category.array)
public class LC215FindKthLargest2 implements IAlgorithm {

    @Override
    public void exec() {
        int[] array = {3, 2, 1, 5, 6, 4};
        int k = 2;

        Assert.assertEquals(5, findKthLargest(array, k));
    }

    public int findKthLargest(int[] nums, int k) {
        int size = nums.length;
        int targetIndex = size - k;

        int left = 0;
        int right = size - 1;
        while (true) {
            int partition = partition(nums, left, right);
            if (partition == targetIndex) {
                break;
            } else if (partition > targetIndex) {
                right = partition - 1;
            } else {
                left = partition + 1;
            }
        }
        return nums[targetIndex];
    }

    public int partition(int[] nums, int s, int e) {
        int val = nums[s];
        while (s < e) {
            while (nums[e] >= val && s < e) {
                e--;
            }
            swap(nums, s, e);
            while (nums[s] <= val && s < e) {
                s++;
            }
            swap(nums, s, e);
        }
        return s;
    }
}
