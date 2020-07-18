package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.Arrays;
import cn.com.frodo.algorithm.IAlgorithm;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author frodoking
 * @ClassName: LC15ThreeSum
 * @date 2020/7/13
 */
public class LC15ThreeSum implements IAlgorithm {

    public static final int[] array = {-1, 0, 1, 2, -1, -4};

    @Override
    public void exec() {
        java.util.Arrays.sort(array);

        List<List<Integer>> threeSum = threeSum(array, 0);
        System.out.println(threeSum.toString());
    }

    private List<List<Integer>> threeSum(int[] array, int sum) {
        List<List<Integer>> res = Lists.newArrayList();
        for (int i = 0; i < array.length; i++) {
            int tSum = sum - array[i];
            if (tSum > 0) {
                List<List<Integer>> list = twoSum(array, i, tSum);
                list.forEach(l-> l.add(array[0]));
                res.addAll(list);
            }
            Arrays.swap(array, i, 0);
        }

        return res;
    }

    private List<List<Integer>> twoSum(int[] array, int index, int sum) {
        int i = 0;
        int j = array.length - 1;

        List<List<Integer>> res = Lists.newArrayList();
        while (i < j) {
            int s = i + j;
            if (s> sum) {
                j--;
            } else if (s<sum) {
                i++;
            } else {
                res.add(Lists.newArrayList(i, j));
            }
        }
        return res;
    }
}
