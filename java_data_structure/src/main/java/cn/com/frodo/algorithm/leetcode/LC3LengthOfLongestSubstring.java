package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author frodoking
 * @ClassName: LC3LengthOfLongestSubstring
 * @date 2020/7/13
 */
@Deprecated
public class LC3LengthOfLongestSubstring implements IAlgorithm {

    @Override
    public void exec() {
        String s = "pwwke";
        System.out.println(lengthOfLongestSubstring(s));
    }

    private int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int res = 0;
        for (int start = 0, end = 0; end < s.length(); end++) {
            char t = s.charAt(end);
            if (map.containsKey(t)) {
                start = Math.max(map.get(t) + 1, start);
            }
            map.put(s.charAt(end), end);
            res = Math.max(res, end - start + 1);
        }
        return res;
    }
}
