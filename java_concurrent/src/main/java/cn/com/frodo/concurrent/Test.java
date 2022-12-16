package cn.com.frodo.concurrent;


import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author frodoking
 * @ClassName: Test
 * @date 2022/3/22
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("hello world".lastIndexOf("l"));
        System.out.println("hello world".lastIndexOf("HE"));
        System.out.println("hello world word".lastIndexOf("or"));

        System.out.println(lastIndexOf("hello world","l"));
        System.out.println(lastIndexOf("hello world","HE"));
        System.out.println(lastIndexOf("hello world word","or"));

//        Assert.check("hello world".lastIndexOf("l")==lastIndexOf("hello world","l"));
//        Assert.check("hello world".lastIndexOf("HE")==lastIndexOf("hello world","HE"));
//        Assert.check("hello world word".lastIndexOf("or")==lastIndexOf("hello world word","or"));

    }

    /**
     * 编写代码
     */
    public static int lastIndexOf(String source,String target) {
        for (int i = source.length() -1-target.length() ; i >0; i--) {
            if (source.substring(i, i+target.length()).equals(target)) return i;
        }

        return -1;
    }
}
