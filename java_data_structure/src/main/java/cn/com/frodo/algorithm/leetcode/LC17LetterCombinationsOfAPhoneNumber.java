package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 *
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 *
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 *
 * 提示：
 *
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 *
 * @author frodoking
 * @ClassName: LC17LetterCombinationsOfAPhoneNumber
 * @date 2022/3/7
 */
@Deprecated
public class LC17LetterCombinationsOfAPhoneNumber implements IAlgorithm {
    @Override
    public void exec() {
        List<String> result = letterCombinations("23");
        System.out.println(result);
    }

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        findCombination(digits, 0, "", combinations, phoneMap);

        return combinations;
    }

    private void findCombination(String digits, int level, String beforeStr, List<String> combinations, Map<Character, String> phoneMap) {
        if (level == digits.length()) {
            combinations.add(beforeStr);
            return;
        }

        String letters = phoneMap.get(digits.charAt(level));
        for (int i = 0; i < letters.length(); i++) {
            findCombination(digits, level + 1, beforeStr + letters.charAt(i), combinations, phoneMap);
        }
    }

}
