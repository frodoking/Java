package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;
import org.apache.logging.log4j.util.Strings;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 *
 *
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 *
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 * @author frodoking
 * @ClassName: LC48Rotate
 * @date 2023/3/14
 */
@Deprecated
public class LC49GroupAnagrams implements IAlgorithm {
    @Override
    public void exec() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str:strs) {
            char[] key = str.toCharArray();
            Arrays.sort(key);

            String k2 = new String(key);

            List<String> list = map.getOrDefault(k2, new ArrayList<>());
            list.add(str);

            if (!map.containsKey(k2)) {
                map.put(k2, list);
            }
        }

        return new ArrayList<>(map.values());
    }
}
