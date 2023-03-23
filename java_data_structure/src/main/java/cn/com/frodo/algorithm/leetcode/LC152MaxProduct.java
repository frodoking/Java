package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

@Deprecated
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.array)
public class LC152MaxProduct implements IAlgorithm {
    @Override
    public void exec() {
        int[] nums = {-4,-3,-2};
        Assert.assertEquals(12, maxProduct(nums));
    }

    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int imax = 1;
        int imin = 1;
        for (int i = 0; i < nums.length; i++) {
            int cimax = Math.max(Math.max(nums[i], nums[i] * imax), nums[i] * imin);
            int cimin = Math.min(Math.min(nums[i], nums[i] * imin), nums[i] * imax);

            max = Math.max(max, cimax);

            imax = cimax;
            imin = cimin;
        }

        return max;
    }
}
