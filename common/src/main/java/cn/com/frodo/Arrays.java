package cn.com.frodo;


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

    public static void swap2(int[] x, int a, int b) {
        if (x[a] == x[b]) {
            return;
        }
        int m = x[a];
        int n = x[b];
        m = m + n;
        n = m - n;
        m = m - n;
        x[a] = m;
        x[b] = n;
    }

    public static void show(int[] data, String flag) {
        if (flag != null)
            System.out.println("=======" + flag + "======");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + "\t");
        }
        System.out.println();
    }


    public static void show(String text, String flag) {
        if (flag != null)
            System.out.println("=======" + flag + "======");
        System.out.println(text);
    }
}
