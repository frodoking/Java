package cn.com.frodo.concurrent.chapter_4;

import cn.com.frodo.concurrent.annotations.ThreadSafe;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 将线程安全性委托给多个状态变量
 * Created by xuwei19 on 2015/1/14.
 */
@ThreadSafe
public class VisualComponent {
    /**
     * CopyOnWriteArrayList(是线程安全的链表)的核心思想是利用高并发往往是读多写少的特性，对读操作不加锁
     * 对写操作，先复制一份新的集合，在新的集合上面修改，然后将新集合赋值给旧的引用
     * 并通过volatile 保证其可见性，当然写操作的锁是必不可少的了。
     */
    private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<KeyListener>();
    private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<MouseListener>();

    public void addKeyListener(KeyListener listener) {
        keyListeners.add(listener);
    }

    public void addMouseListener(MouseListener listener) {
        mouseListeners.add(listener);
    }

    public void removeKeyListener(KeyListener listener) {
        keyListeners.remove(listener);
    }

    public void removeMouseListener(MouseListener listener) {
        mouseListeners.remove(listener);
    }
}
