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
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.backtrack))
public class LC46Permutation implements IAlgorithm {

    public static final int[] array = {1, 2, 3};

    @Override
    public void exec() {
        List<List<Integer>> permute = permute(array);

        System.out.println(permute);

        System.out.println(Arrays.toString(permutationDuplicate("qqa")));


        int[] nums = {1, 2, 3, 4, 5};
        permute = permuteLimits(nums, 3);
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

    public List<List<Integer>> permuteLimits(int[] nums, int limits) {
        List<List<Integer>> permute = new ArrayList<>();

        boolean[] status = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            status[i] = false;
        }
        dfsLimits(permute, new ArrayDeque<>(), nums, status, limits, 0);
        return permute;
    }

    public void dfsLimits(List<List<Integer>> all, Queue<Integer> path, int[] nums, boolean[] status, int limits, int depth) {
        if (depth == limits) {
            all.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!status[i]) {
                path.add(nums[i]);
                status[i] = true;
                dfsLimits(all, path, nums, status, limits, depth + 1);
                path.remove(nums[i]);
                status[i] = false;
            }
        }
    }

    /**
     * 字符有重复的情况
     */
    public String[] permutationDuplicate(String s) {
        Set<String> permute = new HashSet<>();

        boolean[] status = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            status[i] = false;
        }
        dfsDuplicate(permute, new ArrayDeque<>(), s.toCharArray(), status, 0);
        return permute.toArray(new String[0]);
    }


    public void dfsDuplicate(Set<String> permute, Deque<Character> path, char[] nums, boolean[] status, int depth) {
        if (depth == nums.length) {
            permute.add(path.toString());
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!status[i]) {
                path.push(nums[i]);
                status[i] = true;
                dfsDuplicate(permute, path, nums, status, depth + 1);
                path.pop();
                status[i] = false;
            }
        }
    }
}
