package cn.com.frodo.performance.optimization.concurrent;

import java.util.concurrent.Semaphore;

/**
 * Created by xuwei19 on 2015/7/20.
 */
public class Pool {
    private static final int MAX_AVAILABLE = 100;
    private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

    public Object getItem() throws InterruptedException {
        available.acquire();
        // 申请一个许可
        // 同时只能有100个线程进入取得可用项，
        // 超过100个则需要等待
        return getNextAvailableItem();
    }

    public void putItem(Object x) {
        // 将给定项放回池内，标记为未被使用
        if (markAsUnused(x)) {
            available.release();
            // 新增了一个可用项，释放一个许可，请求资源的线程被激活一个
        }
    }

    // 仅作示例参考，非真实数据
    protected Object[] items = new Object[MAX_AVAILABLE]; // 用于对象池复用对象
    protected boolean[] used = new boolean[MAX_AVAILABLE]; // 标记作用

    protected synchronized Object getNextAvailableItem() {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (!used[i]) {
                used[i] = true;
                return items[i];
            }
        }
        return null;
    }

    protected synchronized boolean markAsUnused(Object item) {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (item == items[i]) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
