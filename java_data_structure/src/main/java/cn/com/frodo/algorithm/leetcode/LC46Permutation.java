package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.*;


/**
 * @author frodoking
 * @ClassName: AllRange
 * @date 2020/7/10
 */
@Deprecated
@AlgorithmPoint(
        tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.array,
        company = {AlgorithmPoint.Company.bytedance},
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.backtrack))
public class LC46Permutation implements IAlgorithm {

    @Override
    public void exec() {
        int[] array = {1, 2, 3};
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

    public void dfs(List<List<Integer>> all, Queue<Integer> path, int[] nums, boolean[] status, int depth) {
        if (depth == nums.length) {
            all.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!status[i]) {
                path.add(nums[i]);
                status[i] = true;
                dfs(all, path, nums, status, depth + 1);
                path.remove(nums[i]);
                status[i] = false;
            }
        }
    }
}
