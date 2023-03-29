package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.*;

/**
 * 面试题 17.15. 最长单词
 * 给定一组单词words，编写一个程序，找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。若有多个长度相同的结果，返回其中字典序最小的一项，若没有符合要求的单词则返回空字符串。
 *
 * 示例：
 *
 * 输入： ["cat","banana","dog","nana","walk","walker","dogwalker"]
 * 输出： "dogwalker"
 * 解释： "dogwalker"可由"dog"和"walker"组成。
 * 提示：
 *
 * 0 <= len(words) <= 200
 * 1 <= len(words[i]) <= 100
 *
 * @author frodoking
 * @ClassName: LongestWord
 * @date 2022/3/18
 */
@AlgorithmPoint(tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.array,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.dfs))
public class LCLongestWord implements IAlgorithm {

    @Override
    public void exec() {
        //        String[] words = new String[] {"apple","iOS","dog","nana","man","good","gooan"};
        String[] words = new String[]{"ikjkikkki","ikjkikkknlkllkklni","lkliki","ininjkkkk","ikjkikkk","i","ininjkkkknlkllkklni","nlkllkklni","j"};
        String s = longestWord(words);
        System.out.println("args = " + s);

    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param words string字符串一维数组
     * @return string字符串
     */
    public String longestWord(String[] words) {
        System.out.println("words = " + Arrays.deepToString(words));
        List<String> list = Arrays.asList(words);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }
                return o2.length() - o1.length();
            }
        });

        Set<String> set = new HashSet<>(list);
        for (String word : list) {
            set.remove(word);
            if (checkContains(word, set)) {
                return word;
            }
        }

        return "";
    }

    private boolean checkContains(String word, Set<String> words) {
        for (String s : words) {
            if (word.equals(s)) {
                return true;
            }
            if (word.startsWith(s) && checkContains(word.substring(s.length()), words) ) {
                return true;
            }
        }
        return false;
    }
}
