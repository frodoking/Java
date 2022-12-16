package cn.com.frodo.knowledge.generics;

import cn.com.frodo.MockInterface;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author frodoking
 * @ClassName: GenericsTest
 * @date 2022/3/20
 */
public class GenericsTest implements MockInterface {
    @Override
    public void doTest() {
        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(12);

        try {
            Class<?> l = Class.forName("java.util.ArrayList");
            Method m = l.getMethod("add", Object.class);
            m.invoke(list, "abc");
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(list);
    }
}
