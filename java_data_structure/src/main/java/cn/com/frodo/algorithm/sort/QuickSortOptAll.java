package cn.com.frodo.algorithm.sort;

/**
 * 优化步骤：
 * 1、每次递归选取哨兵pivot（第一歩选取一个随机数）;
 * 2、双向搜索替代单项搜索;
 * 3、当某一段数组长度小于某一个经验值的时候就采用插入排序进行排序（因为当小数组排序是插入排序效率最高）;
 * 4、在最后一步也可以对整个数组进行一次插入排序，效率会有所提高;
 * 5、第一步的选取哨兵的随机数产生效率会受到影响，采用中位数方式来获取（什么时候采用median-of-nine去选择pivot，这里也有个数组大小的阀值，这个值也完全是经验值，
 *   设定在40，大小大于40的数组使用median-of-nine选择pivot，大小在7到40之间的数组使用median-of-three选择中数，大小等于7的数组直接选择中数，大小小于7的数组则直接使用插入排序）;
 * 6、为了避免重复数的交换比较，采用分区方式（四段分区方式：两端存放相等的数，第二段存放小于，第三段存放大于）
 *    同样是进行两端扫描，但是遇到equals的元素不是进行互换，而是各自交换到两端。当扫描结束，还要将两端这些跟pivot equals的元素交换到中间位置，不相同的元素交换到两端，左边仍然是比pivot小的，右边是比pivot大的，分别进行递归的快速排序处理
 * 7、另外枢轴可以变化，当搜索到第一个大于哨兵值就将当前位置存入low，当搜索到第一个小于哨兵值就将当前位置存入high，最后让哨兵落入当前位置。
 * @author XuWei4
 *
 */
public class QuickSortOptAll extends Sort {

	/**
	 * 定义一个分界点常量
	 */
	private final int DIVISION = 50;

	@Override
	public void sort(int[] data) {
		quickSort(0, data.length, data);
	}

	private void quickSort(int low, int high, int... array) {
		if (high - low > DIVISION) {
			while (low < high) {
				int pivot = partition(low, high, array);
				quickSort(low, pivot, array);// 对低子表进行递归排序
				low = pivot + 1;
			}
		} else {
			new InsertSort().sort(array);
		}
	}

	/**
	 * 优化后快速排序的核心程序,枢轴记录用了三取一，去前中后三个数中间那个
	 * 
	 * @param low
	 * @param high
	 * @param array
	 * @return 返回枢轴记录
	 */
	private int partition(int low, int high, int[] array) {
		int mid = (low + high) / 2;
		// 枢轴记录三取一，优化的代码，用于待排序数组元素不是很大的时候
		{
			if (array[low] > array[high]) {
				swap(array, low, high);
			}
			if (array[mid] > array[high]) {
				swap(array, mid, high);
			}
			if (array[mid] > array[low]) {
				swap(array, mid, low);
			}
		}
		int pivotKey = array[low]; // 将三取一后的中间值作为枢轴记录
		while (low < high) {
			while (low < high && array[high] >= pivotKey) {
				high--;
			}
			array[low] = array[high]; // 把比枢轴记录小的值赋给low下标的记录
			while (low < high && array[low] <= pivotKey) {
				low++;
			}
			array[high] = array[low];// 把比枢轴记录大的值赋给high下标的记录
			array[low] = pivotKey;// 然后将枢轴记录赋给low
		}
		return low;// 返回枢轴记录的下标
	}
}
