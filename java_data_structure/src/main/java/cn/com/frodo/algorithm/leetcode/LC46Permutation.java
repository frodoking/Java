package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;


/**
 * @author frodoking
 * @ClassName: AllRange
 * @date 2020/7/10
 */
public class LC46Permutation implements IAlgorithm {

    public static final int[] array = {1, 2, 3};

    @Override
    public void exec() {
        perum(array, 0, array.length - 1);
    }

    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 对数组arr进行全排列
    public void perum(int[] arr, int p, int q) {
        // for循环将数组中所有的元素和第一个元素进行交换。然后进行全排列。
        // 递归结束条件
        if (p == q) {
            printArray(arr);
        }
        for (int i = p; i <= q; i++) {
            swap(arr, i, p);
            // 把剩下的元素都做全排列。
            perum(arr, p + 1, q);
            swap(arr, i, p);
        }
    }
}
