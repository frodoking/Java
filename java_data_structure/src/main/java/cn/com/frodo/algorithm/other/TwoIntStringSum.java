package cn.com.frodo.algorithm.other;

import cn.com.frodo.algorithm.IAlgorithm;

/**
 * @author frodoking
 * @ClassName: TwoIntStringSum
 * @date 2020/10/11
 */
public class TwoIntStringSum implements IAlgorithm {

    @Override
    public void exec() {
        String str1 = "873456";
        String str2 = "543751";
        System.out.println(twoIntStringSum(str1, str2));
    }

    private String twoIntStringSum(String str1, String str2) {
        if (str1 == null || str1 == "") {
            return str2;
        }
        if (str2 == null || str2 == "") {
            return str1;
        }
        int maxLen = Math.max(str1.length(), str2.length());
        int indexA = maxLen - str1.length();
        int indexB = maxLen - str2.length();
        int carry = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = maxLen - 1; i >= 0; i--) {
            int charA = 0;
            if (i - indexA >= 0) {
                charA = Integer.parseInt(str1.charAt(i - indexA) + "");
            }
            int charB = 0;
            if (i - indexB >= 0) {
                charB = Integer.parseInt(str2.charAt(i - indexB) + "");
            }
            int sum = charA + charB + carry;
            int curr = sum % 10;
            carry = sum / 10;
            stringBuilder.append(curr);
        }
        if (carry != 0) {
            stringBuilder.append(carry);
        }
        return stringBuilder.reverse().toString();
    }
}
