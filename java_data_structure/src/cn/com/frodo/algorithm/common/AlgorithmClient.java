package cn.com.frodo.algorithm.common;

import cn.com.frodo.algorithm.common.LCS.LCS;
import cn.com.frodo.algorithm.common.kruskal.Kruskal;
import cn.com.frodo.algorithm.common.prim.Prim;

public class AlgorithmClient {

	public static void main(String[] args) {
		IAlgorithm _alAlgorithm = null;
		//_alAlgorithm = new LCS();
		//_alAlgorithm = new Kruskal();
		_alAlgorithm = new Prim();
		_alAlgorithm.exec();
	}
}
