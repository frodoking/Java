package cn.com.frodo.knowledge.spring.design.syncinterface;

import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class SyncInterfaceService {

    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    public String bigInterfaceQuery() throws ExecutionException, InterruptedException {
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("userFuture >> 11 >>");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("userFuture >> 22 >>");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "userFuture";
        }, executor);
        CompletableFuture<String> logFuture = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("logFuture >> 11 >>");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("logFuture >> 22 >>");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "logFuture";
        }, executor);
        CompletableFuture<String> growthFuture = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("growthFuture >> 11 >>");
                TimeUnit.SECONDS.sleep(4);
                System.out.println("growthFuture >> 22 >>");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "growthFuture";
        }, executor);

        CompletableFuture.allOf(userFuture, logFuture, growthFuture).orTimeout(3, TimeUnit.SECONDS).join();

        return userFuture.get() + logFuture.get() + growthFuture.get();
    }
}
