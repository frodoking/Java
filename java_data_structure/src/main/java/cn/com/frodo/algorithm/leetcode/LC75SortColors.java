package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

import java.util.Arrays;

/**
 * @author frodoking
 * @ClassName: LC75SortColors
 * @date 2020/7/11
 */
public class LC75SortColors implements IAlgorithm {

    public static final int[] array = {1, 2, 2, 0, 1, 0};

    @Override
    public void exec() {
        int left = 0;
        int right = array.length - 1;
        for (int i = 0; i <= right; i++) {
            if (array[i] == 0) {
                swap(array, i, left);
                left++;
                System.out.println("i:" + i + " " + " left:" + left + " " + Arrays.toString(array));
            } else if (array[i] == 2) {
                swap(array, i, right);
                right--;
                System.out.println("i:" + i + " " + " right:" + right + " " + Arrays.toString(array));
            }
        }
        System.out.println(Arrays.toString(array));
    }


    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
