package cn.com.frodo.algorithm.sort;

/**
 * 冒泡排序
 * @author XuWei4
 *
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
