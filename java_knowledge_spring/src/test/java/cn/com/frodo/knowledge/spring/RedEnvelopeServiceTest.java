package cn.com.frodo.knowledge.spring;

import cn.com.frodo.knowledge.spring.design.redenvelope.RedEnvelopeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApplication.class)
public class RedEnvelopeServiceTest {

    @Autowired
    private RedEnvelopeService redEnvelopeService;

    @Test
    public void testSendAndGrab() {
        Long userId = 123456L;
        Long packageId = redEnvelopeService.sendPackage(userId, 100L, 20);

        ExecutorService executor = Executors.newFixedThreadPool(1000);
        for (int i = 0; i < 10000; i++) {
            final long fi = i;
            executor.execute(() -> redEnvelopeService.grabPackage(fi, packageId));
        }
        executor.shutdown();
    }

}
