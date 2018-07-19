package cn.com.frodo.knowledge.loadbalance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frodoking on 2018/7/5 上午10:11.
 * Description: 通过系统随机函数，根据后端服务器列表的大小值来随机选择其中一台进行访问。由概率统计理论可以得知，随着调用量的增大，其实际效果越来越接近于平均分配流量到每一台后端服务器，也就是轮询的效果。
 */
public class RoundRobin implements LoadBalance {

    private static Integer position;

    @Override
    public String getServer() {
        List<String> serverIpList = new ArrayList<>(serverWeightMap.keySet());
        String serverIp = null;
        synchronized (position) {
            if (position >= serverIpList.size()) {
                position = 0;
            }
            serverIp = serverIpList.get(position);
            position++;
        }
        return serverIp;
    }
}
