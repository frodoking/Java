package cn.com.frodo.concurrent.chapter_7;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 不支持关闭的生产者--消费者日志服务
 * (
 * 如果要实现终止日志线程的方法，从而避免使JVM无法正常关闭。
 * 要停止日志线程是很容易的，因为它会反复调用take，而take能响应中断。
 * 如果将日志线程修改为当捕获到InterruptedException时退出，那么只需要中断日志线程就能停止服务。
 * )
 * Created by frodo on 2015/1/16.
 */
public class LogWriter {
    private static final int CAPACITY = 100;
    private final BlockingQueue<String> queue;
    private LoggerThread logger;

    public LogWriter(Writer writer) {
        this.queue = new LinkedBlockingQueue<String>(CAPACITY);
        this.logger = new LoggerThread(writer);
    }

    public void start() {
        logger.start();
    }

    public void log(String msg) throws InterruptedException {
        queue.put(msg);
    }

    private class LoggerThread extends Thread {
        private final PrintWriter writer;

        private LoggerThread(Writer writer) {
            this.writer = new PrintWriter(writer, true); // autoflush
        }

        @Override
        public void run() {
            try {
                while (true)
                    writer.println(queue.take());
            } catch (InterruptedException e) {
            } finally {
                writer.close();
            }
        }
    }
}
