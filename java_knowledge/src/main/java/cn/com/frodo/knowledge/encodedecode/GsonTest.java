package cn.com.frodo.knowledge.encodedecode;

import cn.com.frodo.MockInterface;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * Description TODO
 *
 * @author frodoking
 * @version [V1, 2019/8/15 11:29]
 */
public class GsonTest implements MockInterface {

    private int runLoop = 1000;

    public GsonTest() {
    }

    public GsonTest(int runLoop) {
        this.runLoop = runLoop;
    }

    @Override
    public void doTest() {
        URL url = getClass().getResource("/test.json");
        try {
            File file = new File(url.toURI());
            String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

            long min = 0;
            long max = 0;
            long avg = 0;

            for (int i = 0; i < runLoop; i++) {
                long timestamp = System.nanoTime();
                GsonUtils.fromJson(content, HashMap.class);
                long takeTime = System.nanoTime() - timestamp;
                if (avg == 0) {
                    avg = takeTime;
                    min = takeTime;
                    max = takeTime;
                }
                avg = (avg + takeTime) / 2;
                min = min < takeTime ? min : takeTime;
                max = max > takeTime ? max : takeTime;
            }
            System.out.println("D RunLoop: " + runLoop + ",  min: " + min + ", max: " + max + ", avg: " + avg);
            avg = 0;
            min = 0;
            max = 0;
            HashMap hashMap = GsonUtils.fromJson(content, HashMap.class);
            for (int i = 0; i < runLoop; i++) {
                long timestamp = System.nanoTime();
                GsonUtils.toJson(hashMap);
                long takeTime = System.nanoTime() - timestamp;
                if (avg == 0) {
                    avg = takeTime;
                    min = takeTime;
                    max = takeTime;
                }
                avg = (avg + takeTime) / 2;
                min = min < takeTime ? min : takeTime;
                max = max > takeTime ? max : takeTime;
            }

            System.out.println("S RunLoop: " + runLoop + ",  min: " + min + ", max: " + max + ", avg: " + avg);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

}
