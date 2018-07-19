package cn.com.frodo.knowledge.loadbalance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frodoking on 2018/7/5 上午10:15.
 * Description:
 * 轮询法的优点在于：试图做到请求转移的绝对均衡。
 * <p>
 * 轮询法的缺点在于：为了做到请求转移的绝对均衡，必须付出相当大的代价，因为为了保证pos变量修改的互斥性，需要引入重量级的悲观锁synchronized，这将会导致该段轮询代码的并发吞吐量发生明显的下降。
 */
public class Random implements LoadBalance {
    @Override
    public String getServer() {
        List<String> serverIpList = new ArrayList<>(serverWeightMap.keySet());
        return serverIpList.get(new java.util.Random().nextInt(serverIpList.size()));
    }
}
