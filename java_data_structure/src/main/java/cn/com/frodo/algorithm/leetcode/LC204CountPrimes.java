package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

import java.util.Arrays;

/**
 * 统计所有小于非负整数 n 的质数的数量。
 * <p>
 * 示例:
 * <p>
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 *
 * @author frodoking
 * @ClassName: CountPrimes
 * @date 2020/7/4
 */
public class LC204CountPrimes implements IAlgorithm {

    private static final int NUMBER = 100;

    @Override
    public void exec() {
        int count = countPrimes(NUMBER);
        System.out.println("-----------------> " + count);
    }

    private int countPrimes(int number) {
        boolean[] flags = new boolean[number];
        Arrays.fill(flags, true);

        for (int i = 2; i * i < number; i++) {
            if (flags[i]) {
                for (int j = i * i; j < number; j += i) {
                    flags[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 1; i < number; i++) {
            if (flags[i]) {
                count++;
                System.out.println("shusu = " + i);
            }
        }
        return count;
    }

}
