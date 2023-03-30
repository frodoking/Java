package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


/**
 * @author frodoking
 * @ClassName: AllRange
 * @date 2020/7/10
 */
@Deprecated
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.leetcode,
        difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.array,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.backtrack))
public class LC46Permutation implements IAlgorithm {

    public static final int[] array = {1, 2, 3};

    @Override
    public void exec() {
        List<List<Integer>> permute = permute(array);

        System.out.println(permute);
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permute = new ArrayList<>();

        boolean[] status = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            status[i] = false;
        }
        dfs(permute, new ArrayDeque<>(), nums, status, 0);
        return permute;
    }

    public void dfs(List<List<Integer>> all, Deque<Integer> path, int[] nums, boolean[] status, int depth) {
        if (depth == nums.length) {
            all.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!status[i]) {
                path.push(nums[i]);
                status[i] = true;
                dfs(all, path, nums, status, depth + 1);
                path.pop();
                status[i] = false;
            }
        }
    }
}
