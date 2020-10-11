package cn.com.frodo.algorithm;

import cn.com.frodo.algorithm.leetcode.*;
import cn.com.frodo.algorithm.other.ABCABC;
import cn.com.frodo.algorithm.other.ABCABC2;
import cn.com.frodo.algorithm.other.FindMaxLengthArrayAlgorithm;

import java.util.*;

public class AlgorithmClient {

    public static void main(String[] args) {
        IAlgorithm _alAlgorithm = null;
        //_alAlgorithm = new LCS();
        //_alAlgorithm = new Kruskal();
        FindMaxLengthArrayAlgorithm arrayAlgorithm = new FindMaxLengthArrayAlgorithm();
        String[] src = {"abc", "cde", "cdf", "ex", "lmn", "xyz"};
        List<String> list = Arrays.asList(src);
        Collections.sort(list);
        String[] array = arrayAlgorithm.find(src, "a", "z");
        System.out.println("array:  " + Arrays.toString(array));

        _alAlgorithm = new LC46Permutation();
        _alAlgorithm.exec();

        _alAlgorithm = new LC78SubSets();
        _alAlgorithm.exec();
        _alAlgorithm = new ABCABC2();
        _alAlgorithm.exec();
    }
}
