package cn.com.frodo.knowledge.loadbalance;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by frodoking on 2018/7/5 上午10:03.
 * Description: 负载均衡
 */
public interface LoadBalance {

    String getServer();

    public static Map<String, Integer> serverWeightMap = new HashMap<String, Integer>() {
        {
            put("192.168.1.100", 1);
            put("192.168.1.101", 1);
            // 权重为4
            put("192.168.1.102", 4);
            put("192.168.1.103", 1);
            put("192.168.1.104", 1);
            // 权重为3
            put("192.168.1.105", 3);
            put("192.168.1.106", 1);
            // 权重为2
            put("192.168.1.107", 2);
            put("192.168.1.108", 1);
            put("192.168.1.109", 1);
            put("192.168.1.110", 1);
        }
    };
}
