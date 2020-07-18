package cn.com.frodo.algorithm;

import cn.com.frodo.algorithm.common.stringarrange.StringArrange;
import cn.com.frodo.algorithm.leetcode.*;
import cn.com.frodo.algorithm.other.FindMaxLengthArrayAlgorithm;

import java.util.*;

public class AlgorithmClient {

    public static void main(String[] args) {
        IAlgorithm _alAlgorithm = null;
        //_alAlgorithm = new LCS();
        //_alAlgorithm = new Kruskal();
        _alAlgorithm = new StringArrange();
        _alAlgorithm.exec();

        FindMaxLengthArrayAlgorithm arrayAlgorithm = new FindMaxLengthArrayAlgorithm();
        String[] src = {"abc", "cde", "cdf", "ex", "lmn", "xyz"};
        List<String> list = Arrays.asList(src);
        Collections.sort(list);
        String[] array = arrayAlgorithm.find(src, "a", "z");
        System.out.println("array:  " + Arrays.toString(array));

        _alAlgorithm = new LC15ThreeSum();
        _alAlgorithm.exec();

        int a = 36;
        int len = 15;
        System.out.println("args = " + (a & len));
    }
}
