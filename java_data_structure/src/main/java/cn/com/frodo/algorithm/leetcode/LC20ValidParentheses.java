package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.Utils;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;

/**
 * @author frodoking
 * @ClassName: LC20ValidParentheses
 * @date 2022/3/8
 */
@Deprecated
public class LC20ValidParentheses implements IAlgorithm {
    @Override
    public void exec() {
    }

    public boolean isValid(String s) {
        ArrayDeque<Character> deque = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (deque.isEmpty()) {
                deque.push(c);
            } else {
                if (Utils.isPair(deque.peek(),c)) {
                    deque.pop();
                } else {
                    deque.push(c);
                }
            }
        }

        return deque.isEmpty();
    }
}
