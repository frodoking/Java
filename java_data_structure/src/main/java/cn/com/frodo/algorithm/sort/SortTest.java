package cn.com.frodo.algorithm.sort;

public class SortTest {
	public static int[] data = { 32, 14, 25, 8, 12, 45, 30, 17, 3 };

	public static void main(String[] args) {
		//Sort sort = new InsertSort();
		Sort sort = new BubbleSort();
		// Sort sort = new SelectionSort();
		// Sort sort = new QuickSort();
		// Sort sort = new MergingSort();

		sort.sort(data);
		sort.show(data, sort.toString());
	}
}
