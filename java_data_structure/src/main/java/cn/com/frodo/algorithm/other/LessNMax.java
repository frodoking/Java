package cn.com.frodo.algorithm.other;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 字节面试题，求大佬们看看，数组A中给定可以使用的1~9的数，返回由A数组中的元素组成的小于n的最大数。
 * 例如A={1, 2, 4, 9}，x=2533，返回2499
 * <p>
 * 作者：Damon
 * 链接：https://leetcode.cn/circle/discuss/fbhhev/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.hard,
        company = {AlgorithmPoint.Company.bytedance},
        category = AlgorithmPoint.Category.array)
public class LessNMax implements IAlgorithm {

    @Override
    public void exec() {
        int[] nums = {2, 3, 5, 5};
        int target = 2351;

        Assert.assertEquals(2335, lessNMax(nums, target));
    }

    /**
     * 这道题贪心就好了吧，答案只有两种可能，
     * <p>
     * 1.位数相同，从高位到低位选相同或者小于的数字，如果前一位选了小于的，后面直接全部取最大。
     * <p>
     * 2.位数比target少一位，直接用最大的数字构建。
     * <p>
     * 作者：cruelsummer
     * 链接：https://leetcode.cn/circle/discuss/fbhhev/view/D9izOg/
     * 来源：力扣（LeetCode）
     */
    public int lessNMax(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(nums[i], min);
            max = Math.max(nums[i], max);
        }

        char[] targetChars = (target + "").toCharArray();
        int bit = targetChars.length;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bit; i++) {
            boolean exist = false;
            int curDigit = targetChars[i] - '0';
            for (int j = 0; j < nums.length; j++) {
                if (curDigit == nums[j]) {
                    exist = true;
                }
            }
            // 如果存在就一直往里边加
            if (exist) {
                sb.append(curDigit);
            } else {
                // 如果当前数字比给的数组都大，那么直接返回最大的值即可
                if (curDigit > max) {
                    sb.append(buildMax(max, bit - i));
                    break;
                }

                // 如果当前数字比最小值都小，那么需要回溯
                if (curDigit < min) {
                    // 如果是第一个字符，那么直接小一位组最大数字
                    if (sb.length() == 0) {
                        sb.append(buildMax(max, bit - 1));
                    } else {
                        // 否则找前一个数字最接近的数字，并用后续最大数字链接
                        curDigit = targetChars[i - 1] - '0';
                        int minNum = findMin(nums, curDigit);
                        sb.deleteCharAt(sb.length() - 1);
                        sb.append(minNum).append(buildMax(max, bit - i));
                    }
                    break;
                }

                // 如果当前数字就在最大最小之间，找到最接近的数字串联即可
                int minNum = findMin(nums, curDigit);
                sb.append(minNum).append(buildMax(max, bit - i - 1));
                break;
            }
        }

        return Integer.parseInt(sb.toString());
    }

    private int findMin(int[] nums, int curDigit) {
        int fm = Integer.MAX_VALUE;
        int minNum = 0;
        for (int num : nums) {
            if (curDigit - num > 0 && curDigit - num < fm) {
                fm = curDigit - num;
                minNum = num;
            }
        }
        return minNum;
    }

    private int buildMax(int max, int bit) {
        int num = 0;
        for (int i = 0; i < bit; i++) {
            num = num * 10 + max;
        }
        return num;
    }
}
