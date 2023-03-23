package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.Objects;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 */
@Deprecated
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium, company = AlgorithmPoint.Company.tencent,
        category = AlgorithmPoint.Category.array)
public class LC43Multiply implements IAlgorithm {
    @Override
    public void exec() {

        String num1 = "0";
        String num2 = "0";

        Assert.assertEquals("0", multiply(num1, num2));
    }

    public String multiply(String num1, String num2) {
        if (Objects.equals(num1, "0") || Objects.equals(num2, "0")) {
            return "0";
        }
        int len1 = num1.length();
        int len2 = num2.length();

        int[] nums = new int[len1 + len2];

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                int nextIndex = i + j;
                int num = (num1.charAt(len1 - 1 - i) - '0') * (num2.charAt(len2 - 1 - j) - '0') + nums[nextIndex];
                int ret = num / 10;
                nums[nextIndex] = num % 10;
                nums[nextIndex + 1] += ret;
            }
        }
        StringBuilder str = new StringBuilder();
        for (int i = nums.length - 1; i >= 0; i--) {
            str.append(nums[i]);
        }

        int index = 0;
        char zero = str.charAt(index);
        while (zero == '0') {
            index++;
            zero = str.charAt(index);
        }

        return str.substring(index);
    }
}
