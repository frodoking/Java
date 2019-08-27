package cn.com.frodo.algorithm.common.LCS;

import java.util.Random;

import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 最长公共子序列问题
 * 
 * @author frodoking
 * 
 */
public class LCS implements IAlgorithm {

	@Override
	public void exec() {
		String x = GetRandomStrings(10);
		String y = GetRandomStrings(10);
		System.out.println("substring1:" + x);
		System.out.println("substring2:" + y);
		String str = getLongestCommonSubsequence(x, y);
		System.out.println("LCS:" + str);
	}

	private String getLongestCommonSubsequence(String str1, String str2) {
		// 构造二维数组记录子问题x[i]和y[i]的LCS的长度
		int[][] opt = new int[str1.length() + 1][str2.length() + 1];
		// 动态规划计算所有子问题
		for (int i = str1.length() - 1; i >= 0; i--) {
			for (int j = str2.length() - 1; j >= 0; j--) {
				if (str1.charAt(i) == str2.charAt(j))
					opt[i][j] = opt[i + 1][j + 1] + 1;
				else
					opt[i][j] = Math.max(opt[i + 1][j], opt[i][j + 1]);
			}
		}
		System.out.println("<<------最优子结构------>>");
		for (int i = 0; i < str1.length(); i++) {
			for (int j = 0; j < str2.length(); j++) {
				System.out.print(opt[i][j] + "\t");
			}
			System.out.println();
		}

		int i = 0, j = 0;
		StringBuffer str = new StringBuffer();
		while (i < str1.length() && j < str1.length()) {
			if (str1.charAt(i) == str2.charAt(j)) {
				str.append(str1.charAt(i));
				i++;
				j++;
			} else if (opt[i + 1][j] >= opt[i][j + 1])
				i++;
			else
				j++;
		}
		return str.toString();
	}

	// 取得定长随机字符串
	private String GetRandomStrings(int length) {
		StringBuffer buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}
}
