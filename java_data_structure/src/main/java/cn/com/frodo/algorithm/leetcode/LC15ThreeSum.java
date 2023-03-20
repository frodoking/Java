package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
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
@Deprecated
public class LC15ThreeSum implements IAlgorithm {

    public static final int[] array = {1,-1,-1,0};

    @Override
    public void exec() {
        Arrays.sort(array);

        List<List<Integer>> threeSum = threeSum(array, 0);
        System.out.println(threeSum.toString());
    }

    private List<List<Integer>> threeSum(int[] array, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] > sum) {
                return res;
            }
            if (i > 0 && array[i] == array[i - 1]) {
                continue;
            }
            int l = i + 1;
            int r = array.length - 1;
            while (l < r) {
                int sumT = array[i] + array[l] + array[r];
                if (sumT < sum) {
                    l++;
                } else if (sumT > sum) {
                    r--;
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(array[i]);
                    list.add(array[l]);
                    list.add(array[r]);
                    res.add(list);
                    while (l < r && array[l] == array[l + 1]) {
                        l++;
                    }
                    while (l < r && array[r] == array[r - 1]) {
                        r--;
                    }
                    l++;
                    r--;
                }
            }
        }

        return res;
    }

}
