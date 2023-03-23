package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * <p>
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 * @author frodoking
 * @ClassName: LC42Trap
 * @date 2022/3/14
 */
@AlgorithmPoint
@Deprecated
public class LC42Trap implements IAlgorithm {

    @Override
    public void exec() {
        int[] height = {1000, 999, 998, 966, 966};
        int trap = trap(height);
        System.out.println(trap);
    }

    public int trap(int[] height) {
        int start = 0;
        int end = 0;
        int size = height.length;
        int area = 0;
        while (start <= end && end < size) {
            if (start == end) {
                end++;
                continue;
            }

            if (height[start] > height[end]) {
                end++;
            } else {
                int s = Math.min(height[start], height[end]) * (end - start - 1);
                int exist = 0;
                for (int i = start + 1; i < end; i++) {
                    exist += height[i];
                }
                System.out.println(start + " 1 " + end + " " + (s - exist));
                area += s - exist;

                start = end;
            }

            // 如果后边没有最高的点，那么需要从最高点找后边的次高点，继续搜索
            if (end == size - 1) {
                // 如果都到了最后一个就直接终止
                if (start == end) {
                    break;
                }
                int restMaxHeight = 0;
                for (int i = start + 1; i < size; i++) {
                    if (height[i] > restMaxHeight) {
                        restMaxHeight = height[i];
                        end = i;
                    }
                }
                int s = Math.min(height[start], height[end]) * (end - start - 1);
                int exist = 0;
                for (int i = start + 1; i < end; i++) {
                    exist += height[i];
                }
                System.out.println(start + " 2 " + end + " " + (s - exist));
                area += s - exist;

                start = end;
            }
        }

        return area;
    }
}
