package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

import java.util.Arrays;

/**
 * @author frodoking
 * @ClassName: CountPrimes
 * @date 2020/7/4
 */
public class CountPrimes implements IAlgorithm {

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
        for (int i = 0; i < number; i++) {
            if (flags[i]) {
                count++;
                System.out.println("shusu = " + i);
            }
        }
        return count;
    }

}
