package cn.com.frodo.algorithm.sort;

import static cn.com.frodo.Arrays.show;
import static cn.com.frodo.Arrays.swap;

/**
 * 冒泡排序
 *
 * @author frodoking
 */
public class BubbleSort extends Sort {

    @Override
    public void sort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = i; j < data.length; j++) {
                if (data[i] > data[j]) {
                    swap(data, i, j);
                }
            }
            show(data, String.valueOf(i));
        }
    }
}
