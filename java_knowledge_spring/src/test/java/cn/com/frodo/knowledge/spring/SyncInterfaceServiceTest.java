package cn.com.frodo.knowledge.spring;

import cn.com.frodo.knowledge.spring.design.syncinterface.SyncInterfaceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApplication.class)
public class SyncInterfaceServiceTest {

    @Autowired
    private SyncInterfaceService syncInterfaceService;

    @Test
    public void testBigInterfaceQuery() {
        try {
            String result = syncInterfaceService.bigInterfaceQuery();
            System.out.println(result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
