package cn.com.frodo.algorithm.acm;

import cn.com.frodo.algorithm.AlgorithmPoint;

import java.util.*;

/**
 * 题目描述
 * 斗地主起源于湖北十堰房县，据说是一位叫吴修全的年轻人根据当地流行的扑克玩法“跑得快”改编的，如今已风靡整个中国，并流行于互联网上。
 * 牌型：
 * 单顺，又称顺子，最少5张牌，最多12张牌(3…A)不能有2，也不能有大小王，不计花色。
 * 例如：
 * 3-4-5-6-7-8，7-8-9-10-J-Q，3-4-5-6-7-8-9-10-J-Q-K-A
 * 可用的牌 3<4<5<6<7<8<9<10<J<Q<K<A<2<B(小王)<C(大王)，每种牌除大小王外有四种花色
 * (共有13×4+2张牌)
 * <p>
 * 输入描述
 * 输入的第一行为当前手中的牌
 * 输入的第二行为已经出过的牌(包括对手出的和自己出的牌)
 * <p>
 * 输出描述
 * 最长的顺子
 * 对手可能构成的最长的顺子(如果有相同长度的顺子，输出牌面最大的那一个)，
 * 如果无法构成顺子，则输出 NO-CHAIN。
 * <p>
 * 示例
 * 示例1
 * 输入
 * 3-3-3-4-4-5-5-6-7-8-9-10-J-Q-K-A-A-A-A
 * 4-5-6-7-8-8-8
 * 输出
 * 9-10-J-Q-K
 * <p>
 * 示例2
 * 输入
 * 3-3-3-3-8-8-8-8
 * K-K-K-K
 * 输出
 * NO-CHAIN
 * 说明
 * 剩余的牌无法构成顺子
 *
 * 参考：https://dream.blog.csdn.net/article/details/129246583
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.thread,
        company = AlgorithmPoint.Company.huawei)
public class MaxLargestStraight {

    public static final String[] p = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String my = scanner.nextLine();
            String over = scanner.nextLine();
            maxLargestStraight(parse(my), parse(over));
        }
    }

    public static Map<String, Integer> parse(String line) {
        Map<String, Integer> myMap = new HashMap<>();
        for (String key : line.split("-")) {
            myMap.put(key, myMap.getOrDefault(key, 0) + 1);
        }
        return myMap;
    }

    public static void maxLargestStraight(Map<String, Integer> myMap, Map<String, Integer> usedMap) {
        List<String> res = new ArrayList<>();
        List<String> curr = new ArrayList<>();

        for (int i = 0; i < p.length; i++) {
            String currP = p[i];

            if (myMap.getOrDefault(currP, 0) + usedMap.getOrDefault(currP, 0) == 4) {
                if (curr.size() >= 5 && curr.size() >= res.size()) {
                    res.clear();
                    res.addAll(curr);
                }
                curr.clear();
            } else {
                curr.add(currP);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            sb.append(res.get(i)).append("-");
        }
        if (sb.length() > 0) {
            System.out.println(sb.substring(0, sb.length()-1));
        }
    }
}
