package cn.com.frodo.concurrent.chapter_5;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 桌面搜索应用程序中的生产者任务和消费者任务
 * <p/>
 * <p/>
 * PS: 第七章的毒丸对象来关闭服务
 * 通过“毒丸”对象来关闭服务
 * {
 * "毒丸"是指一个放在队列上的对象：
 * 当得到这个对象时，立即停止。
 * <p/>
 * 在FIFO队列中，“毒丸”对象将确保消费者在关闭之前首先完成队列中的所有工作
 * 在提交“毒丸”对象之前提交的所有工作都会被处理，而生产者在提交了“毒丸”对象后，将不会再提交任何工作。
 * <p>
 * 只有在生产者和消费者的数量都一直的情况下，才可以使用“毒丸”对象。
 * }
 * <p/>
 * Created by frodo on 2015/1/14.
 */
public class IndexingService {
    private static final int CAPACITY = 1000;
    private static final File POISON = new File("");
    private final IndexerThread consumer = new IndexerThread();
    private final CrawlerThread producer = new CrawlerThread();
    private final BlockingDeque<File> fileQueue;
    private final FileFilter fileFilter;
    private final File root;

    public IndexingService(File root, final FileFilter fileFilter) {
        this.root = root;
        this.fileQueue = new LinkedBlockingDeque<File>(CAPACITY);
        this.fileFilter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || fileFilter.accept(f);
            }
        };
    }

    public void start() {
        producer.start();
        consumer.start();
    }

    public void stop() {
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException {
        consumer.join();
    }

    private boolean alreadyIndexed(File f) {
        return false;
    }

    class IndexerThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    File file = fileQueue.take();
                    if (file == POISON) {
                        break;
                    } else {
                        indexFile(file);
                    }
                }
            } catch (InterruptedException e) {
                // 恢复被中断的状态
                Thread.currentThread().interrupt();
            }
        }

        private void indexFile(File file) {
            /*...*/
        }
    }

    class CrawlerThread extends Thread {
        @Override
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) {/* fall through */
                e.printStackTrace();
            } finally {
                while (true) {
                    fileQueue.push(POISON);/* retry */
                    break;
                }
            }
        }

        private void crawl(File root) throws InterruptedException {
            File[] entries = root.listFiles(fileFilter);
            if (entries != null) {
                for (File entry : entries) {
                    if (entry.isDirectory()) {
                        crawl(entry);
                    } else if (!alreadyIndexed(entry)) {
                        fileQueue.put(entry);
                    }
                }
            }
        }
    }
}
