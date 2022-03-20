package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * <p>
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 * <p>
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 * <p>
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author frodoking
 * @ClassName: LC93RestoreIpAddresses
 * @date 2022/3/19
 */
public class LC93RestoreIpAddresses implements IAlgorithm {
    @Override
    public void exec() {
        String s = "0000";
        List<String> l = restoreIpAddresses(s);
        for (String str : l) {
            System.out.println(str);
        }
    }

    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        ArrayList<String> res = new ArrayList<>();
        if (len < 4 || len > 12) return res;
        for (int i = 1; i <= len - 3; i++) {
            if (i > 3) break;
            for (int j = i + 1; j <= len - 2; j++) {
                if (j > 6) break;
                for (int k = j + 1; k <= len - 1; k++) {
                    if (k > 9) break;
                    String str1 = s.substring(0, i);
                    if (!isValidNum(str1)) continue;
                    String str2 = s.substring(i, j);
                    if (!isValidNum(str2)) continue;
                    String str3 = s.substring(j, k);
                    if (!isValidNum(str3)) continue;
                    String str4 = s.substring(k, len);
                    if (!isValidNum(str4)) continue;
                    res.add(String.join(".", str1, str2, str3, str4));
                }
            }
        }
        return res;
    }

    private boolean isValidNum(String str) {
        if (str.length() == 0) return false;
        if (str.length() > 1 && str.charAt(0) == '0') return false;
        return Integer.parseInt(str) <= 255;
    }
}
