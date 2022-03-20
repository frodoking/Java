package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

import static cn.com.frodo.Arrays.show;
import static cn.com.frodo.Arrays.swap;


/**
 * @author frodoking
 * @ClassName: AllRange
 * @date 2020/7/10
 */
@Deprecated
public class LC46Permutation implements IAlgorithm {

    public static final int[] array = {1, 2, 3};

    @Override
    public void exec() {
        perum(array, 0, array.length - 1);
    }

    // 对数组arr进行全排列
    public void perum(int[] arr, int p, int q) {
        // for循环将数组中所有的元素和第一个元素进行交换。然后进行全排列。
        // 递归结束条件
        if (p == q) {
            show(arr, getClass().getName());
            return;
        }
        for (int i = p; i <= q; i++) {
            swap(arr, i, p);
            // 把剩下的元素都做全排列。
            perum(arr, p + 1, q);
            swap(arr, i, p);
        }
    }
}
