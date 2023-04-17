package cn.com.frodo.algorithm.sort;

import static cn.com.frodo.Arrays.swap;

/**
 * 从当前位置往后找最小的值，不稳定
 */
public class SelectionSort extends Sort {

    @Override
    public void sort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            int index = i;
            int min = data[index];
            for (int j = i; j < data.length; j++) {
                if (min > data[j]) {
                    min = data[j];
                    index = j;
                }
            }
            swap(data, i, index);
        }
    }
}
