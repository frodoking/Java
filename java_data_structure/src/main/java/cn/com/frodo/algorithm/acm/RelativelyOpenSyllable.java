package cn.com.frodo.algorithm.acm;

import cn.com.frodo.algorithm.AlgorithmPoint;

import java.util.Scanner;

/**
 * 相对开音节
 * https://dream.blog.csdn.net/article/details/129364918
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.easy,
        category = AlgorithmPoint.Category.thread,
        company = AlgorithmPoint.Company.huawei)
public class RelativelyOpenSyllable {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] array = line.split(" ");

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].length() < 4) {
                continue;
            }
            if (!array[i].matches("[a-z]+")) {
                continue;
            }
            String item = new StringBuilder(array[i]).reverse().toString();
            for (int j = 0; j < item.length() - 3; j++) {
                // 第一个匹配到元音就不对
                if ((item.charAt(j) + "").matches("[aeiou]")) {
                    continue;
                }
                // 第二个匹配到辅音就不对
                if (!(item.charAt(j + 1) + "").matches("[aeiou]")) {
                    continue;
                }
                // 第三个匹配到元音+r就不对
                if ((item.charAt(j + 2) + "").matches("[aeiour]")) {
                    continue;
                }
                // 第四个匹配到e
                if (item.charAt(j + 3) != 'e') {
                    continue;
                }
                count++;
            }
        }
        System.out.println(count);
    }
}
