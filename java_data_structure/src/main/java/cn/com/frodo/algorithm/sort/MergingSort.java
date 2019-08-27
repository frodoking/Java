package cn.com.frodo.algorithm.sort;
/**
 * 归并排序
 * @author frodoking
 *
 */
public class MergingSort extends Sort {

	@Override
	public void sort(int[] data) {
		mergeSort(data, 0, data.length - 1);
	}

	private void mergeSort(int[] data, int start, int end) {
		if (end > start) {
			int pos = (start + end) / 2;
			mergeSort(data, start, pos);
			mergeSort(data, pos + 1, end);
			merge(data, start, pos, end);
		}
	}

	private void merge(int[] data, int start, int pos, int end) {
		show(data, "======merge=====start  " + start + "  pos " + pos + " end  " + end);
		int len1 = pos - start + 1;
		int len2 = end - pos;
		int[] A = new int[len1 + 1];
		A[len1] = Integer.MAX_VALUE;
		int[] B = new int[len2 + 1];
		B[len2] = Integer.MAX_VALUE;
		for (int i = 0; i < len1; i++) {
			A[i] = data[i + start];
		}
		for (int i = 0; i < len2; i++) {
			B[i] = data[pos + 1 + i];
		}

		int m = 0, n = 0;
		for (int i = start; i <= end; i++) {
			if (A[m] > B[n]) {
				data[i] = B[n];
				n++;
			} else {
				data[i] = A[m];
				m++;
			}
		}
	}
}
