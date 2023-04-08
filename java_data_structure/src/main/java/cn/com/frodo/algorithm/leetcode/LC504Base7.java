package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 504. 七进制数
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: num = 100
 * 输出: "202"
 * 示例 2:
 * <p>
 * 输入: num = -7
 * 输出: "-10"
 */
@AlgorithmPoint(tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.interview},
        difficulty = AlgorithmPoint.Difficulty.easy,
        category = AlgorithmPoint.Category.digit,
        company = AlgorithmPoint.Company.jd)
public class LC504Base7 implements IAlgorithm {

    @Override
    public void exec() {
        String res2 = convertToBase2(100);
        System.out.println(res2);
        int res22 = convertBaseNToDecimal(res2, 2);
        System.out.println(res22);
        System.out.println(convertDecimalToBaseN(180, 16));
        System.out.println(convertBaseNToDecimal("64", 16));

        Assert.assertEquals("10", convertToBase7(7));
    }

    public String convertToBase2(int num) {
        return convertDecimalToBaseN(num, 2);
    }

    public String convertToBase7(int num) {
        return convertDecimalToBaseN(num, 7);
    }

    /**
     * 十进制转任意进制
     */
    public String convertDecimalToBaseN(int num, int n) {
        if (num == 0) {
            return "0";
        }

        boolean isNeg = num < 0;
        num = isNeg ? -num : num;
        int bit = 0;
        while (num >= Math.pow(n, bit)) {
            bit++;
        }
        bit--;
        StringBuilder sb = new StringBuilder();
        if (isNeg) sb.append("-");
        while (bit >= 0) {
            int ret = (int) (num / Math.pow(n, bit));
            num -= ret * Math.pow(n, bit);
            if (ret >= 10) {
                char ch = (char) ('A' + ret - 10);
                sb.append(ch);
            } else {
                sb.append(ret);
            }
            bit--;
        }

        return sb.toString();
    }

    /**
     * 任意进制转十进制
     */
    public int convertBaseNToDecimal(String base, int bit) {
        base.toUpperCase();
        int res = 0;
        int len = base.length();
        for (int i = 0; i < len; i++) {
            res *= bit;
            char ch = base.charAt(i);
            if (ch >= 'A' && ch <= 'F') {
                res += (ch - 'A' + 10);
            } else {
                res += (ch - '0');
            }
        }
        return res;
    }

}
