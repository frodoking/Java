package cn.com.frodo.algorithm.common.stringarrange;

import cn.com.frodo.algorithm.IAlgorithm;

/**
 * Created by frodoking on 2019/3/5 下午7:20.
 * Description: 全排列
 */
public class StringArrange implements IAlgorithm {

    @Override
    public void exec() {
        String A = "abc";
        char[] arrayA = A.toCharArray();
        recursionArrange(arrayA, 0, arrayA.length - 1);
    }

    /*
     * 参数arrayA:给定字符串的字符数组
     * 参数start:开始遍历字符与其后面各个字符将要进行交换的位置
     * 参数end:字符串数组的最后一位
     * 函数功能：输出字符串数字的各个字符全排列
     */
    void recursionArrange(char[] arrayA, int start, int end) {
        if (end <= 1)
            return;
        if (start == end) {
            for (int i = 0; i < arrayA.length; i++)
                System.out.print(arrayA[i]);
            System.out.println();
        } else {
            for (int i = start; i <= end; i++) {
                swap(arrayA, i, start);
                recursionArrange(arrayA, start + 1, end);
                swap(arrayA, i, start);
            }
        }
    }

    //交换数组m位置和n位置上的值
    void swap(char[] arrayA, int m, int n) {
        char temp = arrayA[m];
        arrayA[m] = arrayA[n];
        arrayA[n] = temp;
    }

}
