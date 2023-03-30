package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.Arrays;
import cn.com.frodo.algorithm.Algorithm;
import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 739. 每日温度
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 * <p>
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 * <p>
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 */
@Deprecated
@AlgorithmPoint(difficulty = AlgorithmPoint.Difficulty.medium,
        category = AlgorithmPoint.Category.array,
        algorithm = @Algorithm(value = Algorithm.AlgorithmEnum.monotonicStack))
public class LC739DailyTemperatures implements IAlgorithm {
    @Override
    public void exec() {
        int[] temperatures = {34, 80, 80, 34, 34, 80, 80, 80, 80, 34};

        Arrays.show(dailyTemperatures(temperatures), "");

        int[] expectedArray = {1, 0, 0, 2, 1, 0, 0, 0, 0, 0};

        Assert.assertArrayEquals(expectedArray, dailyTemperatures(temperatures));
    }

    /**
     * 单调减队列来维持大的值
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] res = new int[len];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            while (!deque.isEmpty() && temperatures[i] > temperatures[deque.peek()]) {
                int index = deque.pop();
                res[index] = i - index;
            }
            deque.push(i);
        }

        return res;
    }
}
