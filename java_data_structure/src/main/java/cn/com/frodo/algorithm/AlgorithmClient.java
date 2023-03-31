package cn.com.frodo.algorithm;

import cn.com.frodo.algorithm.leetcode.*;
import cn.com.frodo.algorithm.offer.Offer51ReversePairs;
import cn.com.frodo.algorithm.other.CPUSin;
import cn.com.frodo.algorithm.other.ThreadExplainPrint;

public class AlgorithmClient {

    public static void main(String[] args) {
        IAlgorithm algorithm = new LC416CanPartition();
        algorithm.exec();
    }
}
