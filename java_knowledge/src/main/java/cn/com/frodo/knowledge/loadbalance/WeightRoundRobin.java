package cn.com.frodo.knowledge.loadbalance;

import java.util.*;

/**
 * Created by frodoking on 2018/7/5 上午10:29.
 * Description:
 */
public class WeightRoundRobin implements LoadBalance {

    private static Integer position;

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

        String serverIp = null;
        synchronized (position) {
            if (position >= serverList.size()) {
                position = 0;
            }
            serverIp = serverList.get(position);
            position++;
        }
        return serverIp;
    }
}
