package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

/**
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 * <p>
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 */
@AlgorithmPoint(
        tag = {AlgorithmPoint.Tag.leetcode, AlgorithmPoint.Tag.interview, AlgorithmPoint.Tag.frequently},
        difficulty = AlgorithmPoint.Difficulty.easy,
        category = AlgorithmPoint.Category.str)
public class LC14LongestCommonPrefix implements IAlgorithm {
    @Override
    public void exec() {
        String[] strs = {"flower","flow","flight"};
        Assert.assertEquals(longestCommonPrefix(strs), "fl");
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        String ans = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < Math.min(strs[i].length(), ans.length()); j++) {
                if (ans.charAt(j) != strs[i].charAt(j)) {
                    break;
                }
            }
            ans = ans.substring(0, j);
            if (ans.equals("")) {
                return "";
            }
        }
        return ans;
    }

}
