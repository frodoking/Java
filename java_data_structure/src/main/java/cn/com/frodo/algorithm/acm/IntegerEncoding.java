package cn.com.frodo.algorithm.acm;

import cn.com.frodo.algorithm.AlgorithmPoint;

import java.util.Scanner;

/**
 * 整数编码
 * https://blog.csdn.net/hihell/article/details/129488088
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.easy,
        category = AlgorithmPoint.Category.matrix,
        company = AlgorithmPoint.Company.huawei)
public class IntegerEncoding {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            System.out.println(encoding(scanner.nextInt()));
        }
    }

    public static String encoding(int number) {
        String binary = Integer.toBinaryString(number);
        boolean more = false;
        StringBuilder res = new StringBuilder();
        while (binary.length() > 0) {
            if (binary.length() > 7) {
                more = true;
            } else {
                more = false;
                int index = 7 - binary.length();
                for (int i = 0; i < index; i++) {
                    binary = "0" + binary;
                }
            }

            String subStr = binary.substring(binary.length() - 7);
            binary = binary.substring(0, binary.length() - 7);

            if (more) {
                subStr = "1" + subStr;
            } else {
                subStr = "0" + subStr;
            }

            String result = Integer.toHexString(Integer.parseInt(subStr, 2));
            if (result.length() < 2) {
                result = "0" + result;
            }
            res.append(result);
        }
        return res.toString().toUpperCase();
    }
}
