package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.Arrays;
import cn.com.frodo.algorithm.IAlgorithm;

import static cn.com.frodo.Arrays.swap;

/**
 * @author frodoking
 * @ClassName: LC75SortColors
 * @date 2020/7/11
 */
@Deprecated
public class LC75SortColors implements IAlgorithm {

    public static final int[] array = {1, 2, 0};

    @Override
    public void exec() {
        sortColors(array);
        Arrays.show(array, "LC75SortColors");
    }

    public void sortColors(int[] nums) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] == 0) {
                continue;
            }

            for (int j = i + 1; j < size; j++) {
                if (nums[j] < nums[i]) {
                    swap(nums, i, j);
                }
            }
        }
    }
}
