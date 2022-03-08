package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;

/**
 * @author frodoking
 * @ClassName: LC20ValidParentheses
 * @date 2022/3/8
 */
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
                if (isPair(deque.peek(),c)) {
                    deque.pop();
                } else {
                    deque.push(c);
                }
            }
        }

        return deque.isEmpty();
    }

    private boolean isPair(char a, char b) {
        return (a == '(' && b == ')')
                || (a == '{' && b == '}')
                || (a == '[' && b == ']');
    }
}
