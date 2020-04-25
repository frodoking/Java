package cn.com.frodo.algorithm.common.consistenthash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Description 一致性hash算法
 *
 * @author frodoking
 * @version [2019/8/1 16:59]
 */
public class ConsistentHash {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsistentHash.class);

    // 每个真实节点负责多少个虚拟节点
    private int virtualNodesPerRealNode;

    private int totalVirtualNodes;

    // 真实结点列表
    private List<String> realNodes = new LinkedList<String>();

    // 真实结点与各虚拟的映射关系
    private HashMap<String, LinkedList<String>> mapping = new HashMap<>();

    // 虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称，采用平衡二叉搜索树结构存储
    private SortedMap<Integer, String> virtualNodes = new TreeMap<Integer, String>();

    public ConsistentHash(String[] nodes, int virtualNodesPerRealNode) {
        this.virtualNodesPerRealNode = virtualNodesPerRealNode;
        addNode(nodes);
    }

    public int getRealNodeSize() {
        return realNodes.size();
    }

    // 使用FNV1_32_HASH算法计算服务器的Hash值，hash空间为[0,2^32-1],程序控制实现逻辑的环形结构
    private int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

    // 根据某个key，首先访问到虚拟节点，再访问到真实节点。
    public String visit(String key) {
        // 得到该key的hash值
        int hash = getHash(key);
        // 得到大于该hash值的所有Map
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
        String virtualNode = null;
        if (subMap.isEmpty()) {
            // 如果没有比该key的hash值更大的，表明该hash值刚好是一致性hash环的尾端
            // 此时从0开始，顺时针取第一个虚拟节点
            Integer i = virtualNodes.firstKey();
            // 返回对应的虚拟节点
            virtualNode = virtualNodes.get(i);
        } else {
            // 按顺时针方向当前最近的虚拟结点
            Integer i = subMap.firstKey();
            // 返回对应的虚拟节点
            virtualNode = subMap.get(i);
        }
        // 截取virtualNode的前缀，获得真实节点
        if (virtualNode != null) {
            return virtualNode.substring(0, virtualNode.indexOf("##"));
        }
        return null;
    }

    // 增加节点，模拟服务器上线的情况。
    public void addNode(String[] nodes) {
        // 维护元数据，包括真实节点信息，虚拟节点信息
        for (String node : nodes) {
            // 维护真实节点信息
            realNodes.add(node);
            LinkedList<String> list = new LinkedList<>();
            // 维护虚拟节点信息，key为hash值，value的前缀为真实节点
            for (int count = 0, sequence = 0; count < virtualNodesPerRealNode; ) {
                String virtualNodeName = node + "##VN" + sequence++;
                int hash = getHash(virtualNodeName);

                // 一般来讲，当虚拟节点数量<<hash空间时，hash函数碰撞的可能性比较小，但严谨其见，此处应该考虑冲突。
                if (!virtualNodes.containsKey(hash)) {
                    virtualNodes.put(hash, virtualNodeName);
                    count++;
                    list.add(virtualNodeName);//维护虚拟节点与真实节点的映射关系
                }
            }
            mapping.put(node, list);
        }
        // 维护虚拟节点总数
        totalVirtualNodes = realNodes.size() * virtualNodesPerRealNode;
    }

    // 删除节点，模拟服务器下线的情况。
    public void removeNode(String[] nodes) {
        for (String node : nodes) {
            if (realNodes.contains(node)) {
                realNodes.remove(node);
            }
            if (mapping.containsKey(node)) {
                LinkedList<String> list = mapping.remove(node);
                for (String virtual : list) {
                    virtualNodes.remove(getHash(virtual));
                }
            }
        }
        totalVirtualNodes = realNodes.size() * virtualNodesPerRealNode;
    }

    // 获取元数据
    public void getMetaData() {
        LOGGER.info("真实节点：");
        for (int i = 0; i < realNodes.size(); i++) {
            LOGGER.info(realNodes.get(i));
        }
        LOGGER.info("虚拟节点数量：" + totalVirtualNodes);
        for (String str : mapping.keySet()) {
            LOGGER.info("{} - {} ", str, mapping.get(str).size());
        }
    }

    // 测试增删节点后各节点的负载
    public void testLoadBalance(String[] keys) {
        LOGGER.info("真实节点数量：" + realNodes.size());
        LOGGER.info("虚拟节点数量：" + totalVirtualNodes);
        LOGGER.info("各节点负载情况：");
        int keyNumber = keys.length;
        int realNodeNumber = realNodes.size();
        String hitNode = "";
        int[] count = new int[realNodeNumber];
        for (int i = 0; i < keyNumber; i++) {
            hitNode = visit(keys[i]);
            for (int j = 0; j < realNodeNumber; j++) {
                if (hitNode.equals(realNodes.get(j))) {
                    count[j] += 1;
                }
            }
        }
        for (int i = 0; i < realNodeNumber; i++) {
            LOGGER.info("[Node" + i + "-" + realNodes.get(i) + "]" + " : " + count[i]);
        }
    }
}
