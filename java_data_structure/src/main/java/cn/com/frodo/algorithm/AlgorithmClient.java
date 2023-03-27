package cn.com.frodo.algorithm;

import cn.com.frodo.algorithm.leetcode.LC39CombinationSum;
import cn.com.frodo.algorithm.leetcode.LC42Trap;
import cn.com.frodo.algorithm.leetcode.LC84LargestRectangleInHistogram;
import cn.com.frodo.algorithm.leetcode.LC85MaximalRectangle;
import cn.com.frodo.algorithm.offer.Offer51ReversePairs;
import cn.com.frodo.algorithm.other.ThreadExplainPrint;

public class AlgorithmClient {

    public static void main(String[] args) {
        IAlgorithm algorithm = new LC85MaximalRectangle();
        algorithm.exec();
    }
}
