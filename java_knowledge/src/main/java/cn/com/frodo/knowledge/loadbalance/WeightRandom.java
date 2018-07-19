package cn.com.frodo.knowledge.loadbalance;

import java.util.*;

/**
 * Created by frodoking on 2018/7/5 上午10:42.
 * Description:
 */
public class WeightRandom implements LoadBalance {

    @Override
    public String getServer() {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(serverWeightMap);

        Iterator<String> iterator = serverMap.keySet().iterator();
        List<String> serverList = new ArrayList<String>();
        while (iterator.hasNext()) {
            String server = iterator.next();
            int weight = serverMap.get(server);
            for (int i = 0; i < weight; i++) {
                serverList.add(server);
            }
        }

        return serverList.get(new java.util.Random().nextInt(serverList.size()));
    }
}
