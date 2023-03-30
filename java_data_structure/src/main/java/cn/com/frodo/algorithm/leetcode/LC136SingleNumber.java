package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 任何数和 00 做异或运算，结果仍然是原来的数，即 a \oplus 0=aa⊕0=a。
 * 任何数和其自身做异或运算，结果是 00，即 a \oplus a=0a⊕a=0。
 * 异或运算满足交换律和结合律，即 a \oplus b \oplus a=b \oplus a \oplus a=b \oplus (a \oplus a)=b \oplus0=ba⊕b⊕a=b⊕a⊕a=b⊕(a⊕a)=b⊕0=b。
 * <p>
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/single-number/solution/zhi-chu-xian-yi-ci-de-shu-zi-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 *
 *  @see LC540SingleElementInASortedArray
 *
 * @author frodoking
 * @ClassName: LC136SingleNumber
 * @date 2020/7/11
 */
public class LC136SingleNumber implements IAlgorithm {

    public static final int[] array = {1, 2, 2};

    @Override
    public void exec() {
        int single = 0;
        for (int i = 0; i < array.length; i++) {
            single ^= array[i];
        }
        System.out.println(single);
    }
}
