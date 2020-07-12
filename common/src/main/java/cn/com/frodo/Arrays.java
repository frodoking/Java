package cn.com.frodo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author frodoking
 * @ClassName: Arrays
 * @date 2020/7/12
 */
public class Arrays {
    //private static final Logger logger = LoggerFactory.getLogger("");
    public static void swap(int[] x, int a, int b) {
        if (x[a] == x[b]) {
            return;
        }
        int t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    public static void show(int[] data, String flag) {
        System.out.println("=======" + flag + "======");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + "\t");
        }
        System.out.println();
    }
}
