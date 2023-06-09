package cn.com.frodo.algorithm.acm;


import cn.com.frodo.algorithm.AlgorithmPoint;

import java.util.Scanner;

/**
 * 矩形相交面积
 * https://dream.blog.csdn.net/article/details/130937992
 * https://blog.csdn.net/haichuanchuanmei/article/details/124015123
 */
@AlgorithmPoint(
        tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.easy,
        category = AlgorithmPoint.Category.str,
        company = AlgorithmPoint.Company.huawei)
public class RectangleIntersectionArea {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = new int[3][4];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        int[] res = intersection(intersection(matrix[0], matrix[1]), matrix[2]);
        if (res.length == 0) {
            System.out.println(0);
            return;
        }
        System.out.println(res[2] * res[3]);
    }

    public static int[] intersection(int[] rect1, int[] rect2) {
        if (rect1.length == 0 || rect2.length == 0) {
            return new int[0];
        }
        Rect re1 = new Rect(rect1);
        Rect re2 = new Rect(rect2);
        if (re1.x >= re2.r || re2.x >= re1.r || re1.y <= re2.d || re2.y <= re1.d) {
            return new int[0];
        }

        int x = Math.max(re1.x, re2.x);
        int y = Math.min(re1.y, re2.y);
        int r = Math.min(re1.r, re2.r);
        int d = Math.max(re1.d, re2.d);

        return new int[]{x, y, r - x, y - d};
    }

    public static class Rect {
        int x;
        int y;
        int w;
        int h;
        int r;
        int d;

        public Rect(int[] rect) {
            this.x = rect[0];
            this.y = rect[1];
            this.w = rect[2];
            this.h = rect[3];
            // 需要注意r,d计算方式
            this.r = x + w;
            this.d = y - h;
        }
    }
}
