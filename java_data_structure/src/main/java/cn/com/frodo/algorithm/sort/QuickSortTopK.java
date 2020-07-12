package cn.com.frodo.algorithm.sort;

import static cn.com.frodo.Arrays.show;
import static cn.com.frodo.Arrays.swap;

/**
 * @author frodoking
 * @ClassName: QuickSort2
 * @date 2020/7/12
 */
public class QuickSortTopK extends Sort {

    @Override
    public void sort(int[] data) {
        show(data, "QuickSort2 Origin Array");
        int topK = topK(data, 4, 0, data.length - 1);
        show(data, "QuickSort2 topK: " + topK);
    }

    private int topK(int[] data, int k, int i, int j) {
        if (i == j) {
            return data[i];
        }
        int index = partition(data, i, j);
        if (index - i >= k) {
            return topK(data, k, i, index - 1);
        } else {
            return topK(data, k - index, index + 1, j);
        }
    }

    private int partition(int[] data, int i, int j) {
        int temp = data[i];
        while (i < j) {
            while (i < j && data[j] > temp) {
                j--;
            }
            swap(data, i, j);
            while (i < j && data[i] < temp) {
                i++;
            }
            swap(data, i, j);
        }
        return i;
    }

}

