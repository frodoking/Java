package cn.com.frodo.algorithm.acm;

import cn.com.frodo.algorithm.AlgorithmPoint;

import java.util.*;
import java.util.function.ToIntFunction;

/**
 * 题目描述
 * 贫如洗的樵夫阿里巴巴在去砍柴的路上，无意中发现了强盗集团的藏宝地，藏宝地有编号从0-N的箱子，每个箱子上面贴有箱子中藏有金币Q的数量。
 * 从金币数量中选出一个数字集合，并销毁贴有这些数字的每个箱子，如果能销毁一半及以上的箱子，则返回这个数字集合的最小大小。
 * 输入
 * 个数字字串，数字之间使用逗号分隔，例如:
 * 16,6,6,6,3,3,3,1,1,5
 * 字串中数字的个数为偶数，并且 个数>=1，<=100000 ; 每个数字>=1，<=100000
 * 输出
 * 这个数字集合的最小大小，例如: 2
 *
 * https://dream.blog.csdn.net/article/details/130962198
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.easy,
        category = AlgorithmPoint.Category.thread,
        company = AlgorithmPoint.Company.huawei)
public class AlibabaFindGoldII {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] array = scanner.nextLine().split(",");

        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            Integer intege=Integer.parseInt(array[i]);
            list.add(intege);
            map.put(intege, map.getOrDefault(intege, 0) + 1);
        }

        int half = (list.size() + 1) / 2;

        list.sort(Comparator.comparingInt(map::get).reversed());
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < half; i++) {
            res.add(list.get(i));
        }

        System.out.println(res.size());
    }

}
