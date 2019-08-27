package cn.com.frodo.knowledge.ali;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by frodoking on 2019/4/29 下午8:48.
 * Description:
 */
public class TaskScheduleEngine {

    private BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);

    private static final int PARALLEL_SIZE = 5;

    /**
     * 并发处理因子
     */
    private int parallelSize;

    /**
     * 线程池
     */
    private ExecutorService threadPool;


    public TaskScheduleEngine(int parallelSize) {
        if (parallelSize <= 0) {
            this.parallelSize = PARALLEL_SIZE;
        } else {
            this.parallelSize = parallelSize;
        }
        this.threadPool = Executors.newFixedThreadPool(this.parallelSize, new ThreadFactory() {

            private AtomicInteger tag = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("调度器线程：" + tag.getAndIncrement());
                return thread;
            }
        });
    }

    public List<String> getDataFromDB() {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            dataList.add("data:" + (i + 1));
        }
        return dataList;
    }

    public void batchSaveDataIntoDB(List<String> values) {
        System.out.println("do insert data ...");
    }

    public void input(String task) {
        try {
            if (!queue.offer(task, 3, TimeUnit.SECONDS)) {
                System.err.println(" 加入队列失败");
            } else {
                System.out.println(task + " 加入队列");
            }
        } catch (InterruptedException e) {
            int retry = 3;
            while (retry-- > 0) {
                if (queue.offer(task)) {
                    break;
                }
            }
        }
    }

    public List<String> handle() {
        Map<String, Future<String>> futureMap = new HashMap<>();
        String next;
        try {
            while ((next = this.queue.poll(3, TimeUnit.SECONDS)) != null) {
                System.out.println("Get task: " + next);
                Future<String> future = dispatch(next);
                futureMap.put(next, future);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String nextTask = null;
        try {
            List<String> result = new ArrayList<>();
            for (String task : futureMap.keySet()) {
                nextTask = task;
                result.add(futureMap.get(task).get(3, TimeUnit.SECONDS));
            }
            return result;
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            int retry = 3;
            while (retry-- > 0) {
                if (queue.offer(nextTask)) {
                    break;
                }
            }
        }
        return null;
    }

    private Future<String> dispatch(String task) {
        return this.threadPool.submit(() -> doTask(task));
    }

    private String doTask(String task) {
        System.out.println("do task: " + task);
        return task + " - another name";
    }

    private void end() {
        threadPool.shutdown();
    }

    public static void main(String[] args) {
        TaskScheduleEngine taskScheduleEngine = new TaskScheduleEngine(5);
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> data = taskScheduleEngine.getDataFromDB();
                for (String dataItem : data) {
                    taskScheduleEngine.input(dataItem);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> resultList = taskScheduleEngine.handle();
                taskScheduleEngine.batchSaveDataIntoDB(resultList);
                taskScheduleEngine.end();
            }
        }).start();
    }
}
