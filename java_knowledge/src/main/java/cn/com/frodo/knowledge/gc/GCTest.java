package cn.com.frodo.knowledge.gc;

import cn.com.frodo.MockInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Description 3YGC ==> 2FGC ==> 2YGC
 * Start Args -Xms748m -Xmx748m -Xmn256m -XX:NewRatio=4 -XX:SurvivorRatio=4 -XX:MaxTenuringThreshold=3 -XX:+PrintGCDetails
 * eden 128, s0/s1 32, old = (eden+s0)*3 = 480, tenure=64 (fixed)
 * @author frodoking
 */
public class GCTest implements MockInterface
{
    @Override public void doTest()
    {
        int _1MB = 1024 * 1024;
        List caches = new ArrayList();
        for (int i = 0; i < 15; i++)
        {
            caches.add(new byte[32 * _1MB]);
            // System.out.println("ygc.---" + i);
        }

        for (int i = 0; i < 4; i++)
        {
            caches.add(new byte[32 * _1MB]);
            // System.out.println("fgc 2.---" + i);
        }

        for (int i = 0; i < 19; i++)
        {
            caches.remove(0);
        }

        for (int i = 0; i < 10; i++)
        {
            caches.add(new byte[32 * _1MB]);
            // System.out.println("ygc 2.---" + i);
        }
        caches.add(new byte[8 * _1MB]);

    }
}
