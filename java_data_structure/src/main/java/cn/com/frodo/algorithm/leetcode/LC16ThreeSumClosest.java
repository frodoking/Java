package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 16. 最接近的三数之和
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 * <p>
 * 返回这三个数的和。
 * <p>
 * 假定每组输入只存在恰好一个解。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * 示例 2：
 * <p>
 * 输入：nums = [0,0,0], target = 1
 * 输出：0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author frodoking
 * @ClassName: LC16ThreeSumClosest
 * @date 2022/3/22
 */
@Deprecated
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium, company = AlgorithmPoint.Company.tencent,
        category = AlgorithmPoint.Category.array)
public class LC16ThreeSumClosest implements IAlgorithm {
    @Override
    public void exec() {

        int[] nums = {-1, 2, 1, -4};

        Assert.assertEquals(2, threeSumClosest(nums, 1));
    }

    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        int num = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (Math.abs(nums[i] + nums[j] + nums[k] - target) < min) {
                        num = nums[i] + nums[j] + nums[k];
                        min = Math.abs(nums[i] + nums[j] + nums[k] - target);
                        System.out.println("i = " + nums[i] + ", j = " + nums[j] + ", k = " + nums[k] + ", min = " + min);
                    }
                }
            }
        }
        return num;
    }
}
