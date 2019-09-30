package cn.com.frodo.knowledge;


import cn.com.frodo.MockInterface;
import cn.com.frodo.knowledge.concurrent.*;
import cn.com.frodo.knowledge.encodedecode.GsonMultiThreadTest;
import cn.com.frodo.knowledge.encodedecode.GsonTest;
import cn.com.frodo.knowledge.gc.GCTest;
import cn.com.frodo.knowledge.multiThread.ThreadPoolTest;
import com.frodo.thread.ThreadYieldTest;

/**
 * Description TODO
 * @author frodoking
 * @version [V1, 2019/7/27 17:55]
 */
public class Main
{

    public static void main(String[] args)
    {
        Object flag = Configuration.get("main.default");
        Object object = Configuration.get("main.list." + flag.toString());
        try
        {
            MockInterface test = null;
            String testClassName = ((LinkedHashMap) object).get("name").toString();
            System.out.println(testClassName);
            ((LinkedHashMap) object).remove("name");
            Class clazz = Class.forName(testClassName);
            if (((LinkedHashMap) object).isEmpty())
            {
                test = (Test) clazz.newInstance();
            }
            else
            {
                Constructor[] constructors = clazz.getConstructors();
                for (Constructor constructor : constructors)
                {
                    if (constructor.getParameters().length == ((LinkedHashMap) object).size())
                    {
                        Parameter[] p = constructor.getParameters();
                        Object[] argss = new Object[p.length];
                        int index = 0;
                        for (Object argKey : ((LinkedHashMap) object).keySet())
                        {
                            Object argValue = ((LinkedHashMap) object).get(argKey);
                            argss[index] = argValue;
                            index++;
                        }
                        test = (Test) constructor.newInstance(argss);
                    }
                }
            }
            if (test != null)
            {
                test.doTest();
            }
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }
}
