package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.DoubleLinkedList;
import cn.com.frodo.DoubleNode;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.HashMap;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 *  
 * <p>
 * 进阶:
 * <p>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * LRUCache cache = new LRUCache( 2 // 缓存容量);
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回  1
 * cache.put(3,3);    // 该操作会使得关键字 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4,4);    // 该操作会使得关键字 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author frodoking
 * @ClassName: LCLRUCache
 * @date 2020/9/8
 */
public class LC146LRUCache implements IAlgorithm {

    // key -> Node(key, val)
    private HashMap<Integer, DoubleNode> map;
    // Node(k1, v1) <-> Node(k2, v2)
    private DoubleLinkedList cache;
    // 最大容量
    private int capacity;

    @Override
    public void exec() {

    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        return map.get(key).val;
    }

    private void makeRecently(int key) {
        DoubleNode node = map.get(key);
        cache.remove(node);
        cache.addLast(node);
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            deleteKey(key);
            addRecently(key, value);
            return;
        }
        if (cache.size() == capacity) {
            // 删除最久未使用的元素
            removeLeastRecently();
        }
        addRecently(key, value);
    }

    private void removeLeastRecently() {
        DoubleNode node = cache.removeFirst();
        map.remove(node.key);
    }

    private void addRecently(int key, int value) {
        DoubleNode node = new DoubleNode(key, value);
        cache.addLast(node);
        map.put(key, node);
    }

    private void deleteKey(int key) {
        DoubleNode node = map.get(key);
        cache.remove(node);
        map.remove(key);
    }
}
