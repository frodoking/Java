package com.frodo.btrace;

import com.frodo.Test;

import java.util.concurrent.TimeUnit;

/**
 * exec
 * btrace 26796 D:\work\workspaces\workspace_tmp\Java\src\main\java\com\frodo\btrace\TraceMethodArgsAndReturn.java
 */
public class BTraceTest implements Test
{

    @Override public void doTest()
    {
        try
        {
            TimeUnit.SECONDS.sleep(60);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        long ms = intern(1000000);
        System.out.println(ms);
    }

    public long intern(int times)
    {
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++)
        {
            String.valueOf(i).intern();
        }
        return System.currentTimeMillis() - start;
    }
}
