package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author frodoking
 * @ClassName: LC56MergeIntervals
 * @date 2022/3/8
 */
@Deprecated
public class LC56MergeIntervals implements IAlgorithm {
    @Override
    public void exec() {

        List<List<Integer>> result = new ArrayList<>();
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] curr = intervals[i];
           if (result.isEmpty()) {
               result.add(curr);
           } else {
               int[] last = result.get(result.size()-1);
               if (curr[0] < last[1]) {
                   last[1] = Math.max(curr[1], last[1]);
               }else {
                   result.add(curr);
               }
           }
        }
        return result.toArray(new int[result.size()][]);
    }

}
