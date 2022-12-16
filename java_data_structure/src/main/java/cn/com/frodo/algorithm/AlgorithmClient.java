package cn.com.frodo.algorithm;

import cn.com.frodo.algorithm.leetcode.*;
import cn.com.frodo.algorithm.other.FindMaxLengthArrayAlgorithm;
import cn.com.frodo.algorithm.other.RandomTip;
import cn.com.frodo.algorithm.other.ReverseKGroup;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class AlgorithmClient {

    static int j=1;
    public static void main(String[] args) {
        IAlgorithm _alAlgorithm = null;
        //_alAlgorithm = new LCS();
        //_alAlgorithm = new Kruskal();
//          arrayAlgorithm = new FindMaxLengthArrayAlgorithm();
//        String[] src = {"abc", "cde", "cdf", "ex", "lmn", "xyz"};
//        List<String> list = Arrays.asList(src);
//        Collections.sort(list);
//        String[] array = arrayAlgorithm.find(src, "a", "z");
//        System.out.println("array:  " + Arrays.toString(array));

//        _alAlgorithm = new LC46Permutation();
//        _alAlgorithm.exec();

//        _alAlgorithm = new LC78SubSets();
//        _alAlgorithm.exec();
        _alAlgorithm = new ReverseKGroup();
        _alAlgorithm.exec();
    }
}
