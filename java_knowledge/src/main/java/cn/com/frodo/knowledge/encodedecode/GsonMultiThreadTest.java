package cn.com.frodo.knowledge.encodedecode;

import cn.com.frodo.MockInterface;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Description TODO
 *
 * @author frodoking
 * @version [V1, 2019/8/15 11:29]
 */
public class GsonMultiThreadTest implements MockInterface {

    private int runLoop = 1000;

    public GsonMultiThreadTest() {
    }

    public GsonMultiThreadTest(int runLoop) {
        this.runLoop = runLoop;
    }

    @Override
    public void doTest() {
        URL url = getClass().getResource("/test.json");
        try {
            File file = new File(url.toURI());
            String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

            sd(content, true);
            sd(content, false);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    private void sd(final String content, final boolean s) {
        final AtomicLong min = new AtomicLong(0);
        final AtomicLong max = new AtomicLong(0);
        final AtomicLong avg = new AtomicLong(0);
        final HashMap hashMap = GsonUtils.fromJson(content, HashMap.class);
        for (int i = 0; i < runLoop; i++) {
            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long timestamp = System.nanoTime();
                    if (s) {
                        GsonUtils.toJson(hashMap);
                    } else {
                        GsonUtils.fromJson(content, HashMap.class);
                    }
                    long takeTime = System.nanoTime() - timestamp;
                    if (avg.get() == 0) {
                        avg.set(takeTime);
                        min.set(takeTime);
                        max.set(takeTime);
                    }
                    avg.set((avg.get() + takeTime) / 2);
                    min.set(min.get() < takeTime ? min.get() : takeTime);
                    max.set(max.get() > takeTime ? max.get() : takeTime);
                    String prefix = s ? "S" : "D";
                    if (index == runLoop - 1) {
                        System.out.println(
                                prefix + " RunLoop: " + runLoop + ",  min: " + min.get() + ", max: " + max.get() + ", avg: "
                                        + avg.get());
                    }
                }
            }).start();
        }
    }
}
