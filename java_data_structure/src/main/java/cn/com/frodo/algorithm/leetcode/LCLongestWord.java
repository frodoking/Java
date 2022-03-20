package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

import java.util.*;

/**
 * @author frodoking
 * @ClassName: LongestWord
 * @date 2022/3/18
 */
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
