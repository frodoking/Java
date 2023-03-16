package cn.com.frodo.algorithm.leetcode;

import cn.com.frodo.algorithm.IAlgorithm;
import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU 缓存
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *
 * @author frodoking
 * @ClassName: LCLRUCache
 * @date 2020/9/8
 */
@Deprecated
@LCPoint(difficulty = LCPoint.Difficulty.medium,
        category = LCPoint.Category.array)
public class LC146LRUCache implements IAlgorithm {

    private Map<Integer, Integer> map = new HashMap<>();
    private int[] list;

    public LC146LRUCache(int capacity) {
        list = new int[capacity];
        Arrays.fill(list, Integer.MAX_VALUE);
    }

    @Override
    public void exec() {
        LC146LRUCache l = new LC146LRUCache(2);
        l.put(1, 1);
        l.put(2, 2);
        Assert.assertEquals(1, l.get(1));
        l.put(3, 3);
        Assert.assertEquals(-1, l.get(2));
        l.put(4, 4);
        Assert.assertEquals(-1, l.get(1));
        Assert.assertEquals(3, l.get(3));
        Assert.assertEquals(4, l.get(4));

        l = new LC146LRUCache(1);
        l.put(2, 1);
        l.get(2);

        l = new LC146LRUCache(1);
        l.put(2, 1);
        l.put(2, 2);
        l.get(2);
        Assert.assertEquals(2, l.get(2));
        l.put(1, 1);
        l.put(4, 1);
        l.get(2);
    }

    public int get(int key) {
        int result = map.getOrDefault(key, -1);
        if (result == -1) {
            return -1;
        }
        int index = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == key) {
                index = i;
                break;
            }
        }

        int latest = list[index];
        int updateIndex = index;
        for (int i = index + 1; i < list.length; i++) {
            if (list[i] != Integer.MAX_VALUE) {
                list[i - 1] = list[i];
                updateIndex = i;
            }
        }
        list[updateIndex] = latest;

        return result;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            get(key);
            map.put(key, value);
        } else {
            int existIndex = -1;
            for (int i = 0; i < list.length; i++) {
                if (list[i] != Integer.MAX_VALUE) {
                    existIndex = i;
                }
            }
            if (existIndex + 1 < list.length) {
                list[existIndex + 1] = key;
                map.put(key, value);
            } else {
                map.remove(list[0]);
                for (int i = 1; i < list.length; i++) {
                    list[i - 1] = list[i];
                }
                list[list.length - 1] = key;
                map.put(key, value);
            }
        }
    }

}
