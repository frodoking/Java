package cn.com.frodo.algorithm.other;

import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 实现3次方跟
 */
public class ThreeSqrt implements IAlgorithm {

    @Override
    public void exec() {
        Assert.assertEquals(2, threeSqrt(9));
    }

    public int threeSqrt(int n) {
        int left = 0;
        int right = n;
        while (left < right) {
            int mid = (right + left) / 2;
            if (mid == left) {
                return mid;
            }
            int res = mid * mid * mid;
            if (res < n) {
                left = mid;
            } else if (res > n) {
                right = mid;
            } else {
                return mid;
            }
        }

        return left;
    }
}
