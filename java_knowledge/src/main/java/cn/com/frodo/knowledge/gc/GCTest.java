package cn.com.frodo.knowledge.gc;

import cn.com.frodo.MockInterface;
import cn.com.frodo.knowledge.encrypt.UnifiedManager;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Description 3YGC ==> 2FGC ==> 2YGC
 * Start Args -Xms748m -Xmx748m -Xmn256m -XX:NewRatio=4 -XX:SurvivorRatio=4 -XX:MaxTenuringThreshold=3 -XX:+PrintGCDetails
 * eden 128, s0/s1 32, old = (eden+s0)*3 = 480, tenure=64 (fixed)
 *
 * @author frodoking
 */
//@Slf4j
public class GCTest implements MockInterface {

    private static Logger log = LoggerFactory.getLogger(UnifiedManager.class);

    @Override
    public void doTest() {
        int _1MB = 1024 * 1024;
        List<byte[]> caches = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            caches.add(new byte[32 * _1MB]);
            log.info("ygc.---" + i);
        }

        for (int i = 0; i < 4; i++) {
            caches.add(new byte[32 * _1MB]);
            log.info("fgc 2.---" + i);
        }

        for (int i = 0; i < 19; i++) {
            caches.remove(0);
        }

        for (int i = 0; i < 10; i++) {
            caches.add(new byte[32 * _1MB]);
            log.info("ygc 2.---" + i);
        }
        caches.add(new byte[8 * _1MB]);

    }
}
