package cn.com.frodo.knowledge.loadbalance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frodoking on 2018/7/5 上午10:23.
 * Description: 源地址哈希的思想是获取客户端访问的IP地址值，通过哈希函数计算得到一个数值，用该数值对服务器列表的大小进行取模运算，得到的结果便是要访问的服务器的序号。
 */
public class Hash implements LoadBalance {
    @Override
    public String getServer() {
        List<String> serverIpList = new ArrayList<>(serverWeightMap.keySet());

        int position = getRemoteIp().hashCode() % serverIpList.size();

        return serverIpList.get(position);
    }

    private String getRemoteIp() {
        return "127.0.0.1";
    }
}
