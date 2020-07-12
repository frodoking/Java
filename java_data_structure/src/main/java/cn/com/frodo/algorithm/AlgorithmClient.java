package cn.com.frodo.algorithm;

import cn.com.frodo.algorithm.common.stringarrange.StringArrange;
import cn.com.frodo.algorithm.leetcode.LC102LevelOrder;
import cn.com.frodo.algorithm.leetcode.LC136SingleNumber;
import cn.com.frodo.algorithm.leetcode.LC75SortColors;
import cn.com.frodo.algorithm.leetcode.LC94InorderTraversal;
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

        _alAlgorithm = new LC75SortColors();
        _alAlgorithm.exec();
        Deque<String> stack = new ArrayDeque<>();
    }
}
