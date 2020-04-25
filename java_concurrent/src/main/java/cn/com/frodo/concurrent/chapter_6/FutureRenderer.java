package cn.com.frodo.concurrent.chapter_6;

import cn.com.frodo.concurrent.chapter_5.LaunderThrowable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by frodoking on 2015/1/15.
 */
public abstract class FutureRenderer {
    private final ExecutorService executor = Executors.newCachedThreadPool();

    void renderPage(CharSequence source) {
        renderText(source);

        final List<ImageInfo> imageInfos = scanForImageInfo(source);

        Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
            @Override
            public List<ImageData> call() throws Exception {
                List<ImageData> result = new ArrayList<ImageData>();
                for (ImageInfo imageInfo : imageInfos) {
                    result.add(imageInfo.downloadImage());
                }
                return result;
            }
        };
        Future<List<ImageData>> future = executor.submit(task);

        try {
            List<ImageData> imageDatas = future.get();
            for (ImageData data : imageDatas) {
                renderImage(data);
            }
        } catch (InterruptedException e) {
            // 重新设置线程的中断状态
            Thread.currentThread().interrupt();
            // 由于不需要结果，因此取消任务
            future.cancel(true);
        } catch (ExecutionException e) {
            throw LaunderThrowable.launderThrowable(e.getCause());
        }

    }

    abstract void renderText(CharSequence s);

    abstract List<ImageInfo> scanForImageInfo(CharSequence s);

    abstract void renderImage(ImageData i);

    interface ImageData {
    }

    interface ImageInfo {
        ImageData downloadImage();
    }
}
