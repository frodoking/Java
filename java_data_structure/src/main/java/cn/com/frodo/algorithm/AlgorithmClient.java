package cn.com.frodo.algorithm;

import cn.com.frodo.algorithm.leetcode.LC22GenerateParentheses;
import cn.com.frodo.algorithm.leetcode.LC516LongestPalindromicSubsequence;
import cn.com.frodo.algorithm.leetcode.LC5LongestPalindrome;

/**
 * 3.31 leetcode面经地址: https://leetcode.cn/circle/interview/newest/?query=&page=7&tag=mian-shi-jing-yan
 * 4.03 leetcode面经地址: https://leetcode.cn/circle/interview/newest/?query=&page=9&tag=mian-shi-jing-yan
 * 4.06 https://leetcode.cn/circle/interview/newest/?query=&page=11&tag=mian-shi-jing-yan
 */
public class AlgorithmClient {

    public static void main(String[] args) {
        IAlgorithm algorithm = new LC516LongestPalindromicSubsequence();
        algorithm.exec();
    }

}
