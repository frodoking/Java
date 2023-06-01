package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**1
 * @author frodoking
 * @ClassName: SubSets
 * @date 2020/7/11
 * @see LC46Permutation
 */
@Deprecated
@AlgorithmPoint(
        tag = { AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.array,
        company = {AlgorithmPoint.Company.xiapi},
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.backtrack))
public class LC78SubSets implements IAlgorithm {

    public static final int[] array = {1, 2, 3};

    @Override
    public void exec() {
        List<List<Integer>> res = Lists.newArrayList();
        backtrack(0, array, res, Lists.newArrayList());

        System.out.println(res);
    }

    private void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> path) {
        res.add(new ArrayList<>(path));
        for (int j = i; j < nums.length; j++) {
            path.add(nums[j]);
            backtrack(j + 1, nums, res, path);
            path.remove(path.size() - 1);
        }
    }
}
