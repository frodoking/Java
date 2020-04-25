package cn.com.frodo.algorithm.sort;

/**
 * 快速排序
 *
 * @author frodoking
 */
public class QuickSort extends Sort {

    @Override
    public void sort(int[] data) {
        quickSort(data, 0, data.length - 1);
    }

    private void quickSort(int[] data, int i, int j) {
        int index = partition(data, i, j);
        if (index - 1 > i)
            quickSort(data, i, index - 1);
        if (index + 1 < j)
            quickSort(data, index + 1, j);
    }

    private int partition(int[] data, int i, int j) {
        int tmp = data[i];
        while (i < j) {
            while (i < j && data[j] >= tmp) {
                j--;
            }
            swap(data, i, j);
            while (i < j && data[i] <= tmp) {
                i++;
            }
            swap(data, i, j);
        }
        return i;
    }
}
