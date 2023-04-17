package cn.com.frodo.algorithm.offer;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 *
 *
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 *
 *
 * 限制：
 *
 * 1 <= 数组长度 <= 50000
 */

@AlgorithmPoint(tag = AlgorithmPoint.Tag.offer,
        difficulty = AlgorithmPoint.Difficulty.easy,
        category = AlgorithmPoint.Category.array)
public class Offer39MajorityElement implements IAlgorithm {

    @Override
    public void exec() {
        int[] array = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        Assert.assertEquals(2, majorityElement(array));
    }

    /**
     * 众数，如果下一个数和当前这个数不一样那么就抵消，每一次的抵消后当前数一定是众数
     */
    public int majorityElement(int[] nums) {
        int x = 0;
        int vote = 0;
        for (int num:nums) {
            if (vote == 0) x = num;
            vote += x == num? 1:-1;
        }

        return x;
    }
}
