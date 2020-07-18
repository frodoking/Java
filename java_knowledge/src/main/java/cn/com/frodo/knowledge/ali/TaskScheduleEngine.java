package cn.com.frodo.knowledge.ali;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by frodoking on 2019/4/29 下午8:48.
 * Description:
 */
public class TaskScheduleEngine {


    private static final int PARALLEL_SIZE = 5;
    private static final int MAX_QUEUE_SIZE = 100;

    /**
     * 并发处理因子
     */
    private final int parallelSize;
    private final int queueSize;

    /**
     * 线程池
     */
    private final ExecutorService threadPool;
    private final BlockingQueue<String> queue;
    private final Thread[] preducers;
    private final Thread[] consumers;
    private boolean running = false;


    public TaskScheduleEngine(int producerNum, int consumerNum, int parallelSize, int queueSize) {
        if (parallelSize <= 0) {
            this.parallelSize = PARALLEL_SIZE;
        } else {
            this.parallelSize = parallelSize;
        }
        if (queueSize <= 0) {
            this.queueSize = MAX_QUEUE_SIZE;
        } else {
            this.queueSize = queueSize;
        }

        queue = new LinkedBlockingQueue<>(queueSize);

        this.threadPool = new ThreadPoolExecutor(parallelSize, parallelSize + 5,
                30L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(queueSize), new ThreadFactory() {

            private final AtomicInteger tag = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("调度器线程：" + tag.getAndIncrement());
                return thread;
            }
        }, new ThreadPoolExecutor.CallerRunsPolicy());

        preducers = new Thread[producerNum];
        consumers = new Thread[consumerNum];

        for (int i = 0; i < producerNum; i++) {
            preducers[i] = new Thread(() -> {
                while (running) {
                    List<String> data = getDataFromDB();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (String dataItem : data) {
                        input(dataItem);
                    }
                }
            }, "Preducer-" + i);
        }
        for (int i = 0; i < consumerNum; i++) {
            consumers[i] = new Thread(() -> {
                while (running || !queue.isEmpty()) {
                    List<String> resultList = handle();
                    batchSaveDataIntoDB(resultList);
                }
            }, "Consumer-" + i);
        }
    }


    private void start() {
        running = true;
        for (int i = 0; i < preducers.length; i++) {
            preducers[i].start();
        }
        for (int i = 0; i < consumers.length; i++) {
            consumers[i].start();
        }
    }

    public List<String> getDataFromDB() {
        List<String> dataList = new ArrayList<>();
        long timestamp = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            dataList.add("data:" + timestamp + ":" + i);

        }
        return dataList;
    }

    public void batchSaveDataIntoDB(List<String> values) {
        System.out.println("do insert data ..." + Arrays.toString(values.toArray()));
    }

    public void input(String task) {
        try {
            queue.put(task);
            System.out.println(task + " 加入队列");
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
        try {
            String next = this.queue.take();
            System.out.println("Queue Size: " + queue.size() + ", Get task: " + next);
            for (int i = 1; i <= 5; i++) {
                String subTask = next + ":" + i;
                Future<String> future = dispatch(subTask);
                futureMap.put(subTask, future);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String nextTask = null;
        try {
            List<String> result = new ArrayList<>();
            for (String task : futureMap.keySet()) {
                nextTask = task;
                result.add(futureMap.get(task).get());
            }
            return result;
        } catch (InterruptedException | ExecutionException e) {
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

    private String doTask(String task) throws InterruptedException {
        System.out.println("do task: " + task);
        Thread.sleep(3000);
        return task + " - another name";
    }

    public static void main(String[] args) {
        TaskScheduleEngine taskScheduleEngine = new TaskScheduleEngine(2, 10, 5, 100);
        taskScheduleEngine.start();
    }
}
