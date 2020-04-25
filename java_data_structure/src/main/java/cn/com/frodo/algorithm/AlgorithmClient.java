package cn.com.frodo.algorithm;

import cn.com.frodo.algorithm.common.stringarrange.StringArrange;
import cn.com.frodo.algorithm.other.FindMaxLengthArrayAlgorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    }
}
