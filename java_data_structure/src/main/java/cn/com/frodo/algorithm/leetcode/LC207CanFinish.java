package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.*;

/**
 * 207. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 示例 2：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 */
@Deprecated
@LCPoint(difficulty = LCPoint.Difficulty.medium,
        category = LCPoint.Category.graph)
public class LC207CanFinish implements IAlgorithm {
    @Override
    public void exec() {
        int[][] prerequisites = {{1,0},{0,1},{1,2}};
        int numCourses = 3;

        Assert.assertFalse(canFinish(numCourses, prerequisites));
    }

    /**
     * 通过构建图，利用入度进行层次遍历，如果最后入度没有完全消除，则说明有环
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] states = new int[numCourses];
        Map<Integer, List<Integer>> depend = new HashMap<>();

        Arrays.fill(states, 0);
        // 构建入度
        for (int i = 0; i < prerequisites.length; i++) {
            states[prerequisites[i][0]]++;
            List<Integer> list = depend.getOrDefault(prerequisites[i][1], new ArrayList<>());
            list.add(prerequisites[i][0]);
            depend.put(prerequisites[i][1], list);
        }

        Queue<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < states.length; i++) {
            if (states[i] == 0) {
                deque.add(i);
            }
        }

        while (!deque.isEmpty()) {
            int clazz = deque.poll();
            List<Integer> dep = depend.get(clazz);
            if (dep == null) {
                continue;
            }
            for (Integer nextClazz : dep) {
                states[nextClazz]--;
                if (states[nextClazz] == 0) {
                    deque.add(nextClazz);
                }
            }
            depend.remove(clazz);
        }

        return Arrays.stream(states).allMatch(i -> i == 0);
    }

}
