package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.Arrays;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：["()"]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author frodoking
 * @ClassName: LC22GenerateParentheses
 * @date 2022/3/8
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.medium,
        company = {AlgorithmPoint.Company.huawei},
        category = AlgorithmPoint.Category.array)
public class LC22GenerateParentheses implements IAlgorithm {
    @Override
    public void exec() {
        Arrays.show(generateParenthesis(3), "");
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        dfs("", n, n, res);
        return res;
    }

    private void dfs(String c, int left, int right, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(c);
            return;
        }

        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs(c + "(", left - 1, right, res);
        }
        if (right > 0) {
            dfs(c + ")", left, right - 1, res);
        }
    }
}
