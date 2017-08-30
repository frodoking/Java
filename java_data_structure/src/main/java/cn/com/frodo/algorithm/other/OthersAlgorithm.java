package cn.com.frodo.algorithm.other;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OthersAlgorithm {

	public static void main(String[] args) {
		FindMaxLengthArrayAlgorithm arrayAlgorithm = new FindMaxLengthArrayAlgorithm();
		String[] src = {"abc","cde","cdf","ex","lmn", "xyz"};
		List<String> list = Arrays.asList(src);
		Collections.sort(list);
		String[] array = arrayAlgorithm.find(src, "a", "z");
		System.out.println("array:  " + Arrays.toString(array));
	}
}
