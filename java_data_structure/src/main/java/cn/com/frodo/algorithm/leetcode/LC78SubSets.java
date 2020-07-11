package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author frodoking
 * @ClassName: SubSets
 * @date 2020/7/11
 */
public class LC78SubSets implements IAlgorithm {

    public static final int[] array = {1, 2, 3};

    @Override
    public void exec() {
        List<List<Integer>> res = Lists.newArrayList();
        backtrack(0, array, res, Lists.newArrayList());
    }

    private void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
        String key = System.nanoTime() + "";
        System.out.println(key + " " + Arrays.toString(tmp.toArray()));
        res.add(tmp);
        System.out.println(key + " " + Arrays.toString(tmp.toArray()));
        for (int j = i; j < nums.length; j++) {
            tmp.add(nums[j]);
            backtrack(j + 1, nums, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
}
