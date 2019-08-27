package cn.com.frodo.algorithm.other;

import cn.com.frodo.algorithm.IAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by frodoking on 2019/4/28 下午5:01.
 * Description: 计算[100000000, 101000000]之间的素数，并打印出来。 hw
 */
public class Sushu implements IAlgorithm {

    private static final int PARALLEL_SIZE = 100;

    /**
     * 并发处理因子
     */
    private int parallelSize;

    /**
     * 线程池
     */
    private ExecutorService threadPool;

    public Sushu(int parallelSize) {
        if (parallelSize <= 0) {
            this.parallelSize = PARALLEL_SIZE;
        } else {
            this.parallelSize = parallelSize;
        }
        threadPool = Executors.newFixedThreadPool(this.parallelSize, new ThreadFactory() {

            private AtomicInteger tag = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("调度器线程：" + tag.getAndIncrement());
                return thread;
            }
        });
    }

    /**
     * 检查某个区间的数据是否是素数
     *
     * @param startValue 区间开始值
     * @param endValue   区间结算值
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    private void checkSuShu(Long startValue, Long endValue)
            throws InterruptedException, ExecutionException, TimeoutException {
        if (startValue == null || startValue < 0 ||
                endValue == null || endValue < 0) {
            System.out.println("start value or end value error, please check again. ");
            return;
        }

        Long segmentLength = (endValue - startValue + 1) / this.parallelSize;
        List<Future<List<Long>>> futures = new ArrayList<>();

        for (Long i = startValue; i < endValue; i += segmentLength) {
            final Long start = i;
            final Long end = i + segmentLength - 1;
            Future<List<Long>> future = threadPool.submit(() -> {
                List<Long> resultList = new ArrayList<>();
                for (Long j = start; j <= end; j++) {
                    if (isSuShu(j)) {
                        resultList.add(j);
                    }
                }
                return resultList;
            });
            futures.add(future);
        }
        for (int i = 0; i < futures.size(); i++) {
            Future<List<Long>> future = futures.get(i);
            List<Long> list = future.get(3, TimeUnit.SECONDS);
            if (list != null && !list.isEmpty()) {
                for (Long suShu : list) {
                    System.out.println(suShu);
                }
            }
        }
        threadPool.shutdown();
    }


    /**
     * 检查单个数是否是素数
     *
     * @param value
     * @return
     */
    private boolean isSuShu(Long value) {
        for (int i = 2; i <= Math.sqrt(value); i++) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * test result：
     * times  use time
     * 40    3337
     * 50    3687
     * 60    3560
     * 70    3195
     * 80    3371
     * 90    3369
     * 100   3092
     */
    @Override
    public void exec() {
        try {
            long startTime = System.currentTimeMillis();
            // new Sushu(80).checkSuShu(100000000L, 101000000L);
            checkSuShu(100000000L, 101000000L);
            long endTime = System.currentTimeMillis();
            System.out.println("Take time: " + (endTime - startTime) + " ms");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
