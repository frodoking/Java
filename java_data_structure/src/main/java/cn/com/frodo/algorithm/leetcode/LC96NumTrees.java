package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

import java.util.HashMap;
import java.util.Map;

@LCPoint(difficulty = LCPoint.Difficulty.medium)
public class LC96NumTrees implements IAlgorithm {

    private Map<Integer, Integer> map= new HashMap<>();

    @Override
    public void exec() {
        System.out.println(numTrees(3));
    }

    public int numTrees(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {

            int left = numTrees(i - 1);

            int right = numTrees(n - i);

            count += left * right;
        }
        map.put(n, count);
        return count;
    }
}
