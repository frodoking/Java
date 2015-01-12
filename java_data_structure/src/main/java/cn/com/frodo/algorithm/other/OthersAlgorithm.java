package cn.com.frodo.algorithm.other;

public class OthersAlgorithm {

	/**
	 * 数组a[N]，1至N-1这N-1个数存放在a[N]中，其中某个数重复一次。写一个函数，找出被重复的数字。时间复杂度不超过O(n)。
	 * 
	 * @param data
	 * @param num
	 * @return
	 */
	public static int findRepeatData(int num) {
		int[] data  = new int[num];
		data[0] = num % 4 + 5;
		for (int i = 1; i < num; i++) {
			data[i] = i;
		}
		int tmp = 0;
		//方法一 ： 标志位算法
		/*
		int[] flag = new int[num];
		for (int i = 0; i < num; i++) {
			flag[i] = 0;
		}
		
		for (int i = 0; i < num; i++) {
			if (flag[data[i]] == 0) {
				flag[data[i]] = 1;
			} else {
				tmp = i;
				break;
			}
		}
		*/
		//节省空间算法
		int K = 0;
		for (int i = 0; i < num; i++) {
			if(data[i]>=num){
				K = data[i]-num;
			}else{
				K=data[i];
			}
			
			if (data[K] < num) {
				data[K] += num;
			} else {
				tmp = i;
				break;
			}
			
		}
		return data[tmp];
	}

	public static void main(String[] args) {
		int tmpData = findRepeatData(101);
		System.out.println("find repeat data :  " + (101 % 4 + 5) + "  actually " + tmpData);
	}
}
