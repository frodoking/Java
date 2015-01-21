package cn.com.frodo.concurrent.chapter_7;

import cn.com.frodo.concurrent.annotations.GuardedBy;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 使用TrackingExecutorService来保存未完成的任务以备后续执行
 * Created by frodo on 2015/1/19.
 */
public abstract class WebCrawler {
    private volatile TrackingExecutor exec;
    @GuardedBy("this")
    private final Set<URL> urlsToCrawl = new HashSet<URL>();

    private final ConcurrentMap<URL, Boolean> seen = new ConcurrentHashMap<URL, Boolean>();
    private static final long TIMEOUT = 500;
    private static final TimeUnit UNIT = TimeUnit.MILLISECONDS;

    public WebCrawler(URL startUrl) {
        urlsToCrawl.add(startUrl);
    }

    public synchronized void start() {
        exec = new TrackingExecutor(Executors.newCachedThreadPool());
        for (URL url : urlsToCrawl)
            submitCrawlTask(url);

        urlsToCrawl.clear();
    }

    public synchronized void stop() throws InterruptedException {
        try {
            saveUncrawled(exec.shutdownNow());
            if (exec.awaitTermination(TIMEOUT, UNIT)) {
                saveUncrawled(exec.getCancelledTasks());
            }
        } finally {
            exec = null;
        }
    }

    protected void saveUncrawled(List<Runnable> uncrawled) {
        for (Runnable r : uncrawled) {
            urlsToCrawl.add(((CrawlTask) r).getPage());
        }
    }

    protected void submitCrawlTask(URL url) {
        exec.execute(new CrawlTask(url));
    }

    protected abstract List<URL> processPage(URL url);

    private class CrawlTask implements Runnable {
        private final URL url;
        private int count = 1;

        private CrawlTask(URL url) {
            this.url = url;
        }

        boolean alreadyCrawled() {
            return seen.putIfAbsent(url, true) != null;
        }

        void markUnCrawled() {
            seen.remove(url);
            System.out.printf("marking %s uncrawled%n", url);
        }

        @Override
        public void run() {
            for (URL link : processPage(url)) {
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
                submitCrawlTask(link);
            }
        }

        public URL getPage() {
            return url;
        }
    }
}
