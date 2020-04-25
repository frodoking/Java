package cn.com.frodo.algorithm.sort;

/**
 * 插入排序
 *
 * @author frodoking
 */
public class InsertSort extends Sort {

    @Override
    public void sort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = i; j > 0 && data[j] < data[j - 1]; j--) {
                swap(data, j, j - 1);
            }
            show(data, String.valueOf(i));
        }
    }
}
