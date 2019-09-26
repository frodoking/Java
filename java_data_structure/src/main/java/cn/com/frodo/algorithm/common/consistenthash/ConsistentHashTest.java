package com.frodo.algorithm;

import com.frodo.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsistentHashTest implements Test
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsistentHashTest.class);

    // 产生随机字符串，视为key
    public static String[] genKeys(int number)
    {
        String[] ary = new String[number];
        int length = 0;
        for (int j = 0; j < number; j++)
        {
            String temp = "";
            length = (int) (Math.random() * 8 + 2);
            for (int i = 0; i < length; i++)
            {
                int intValue = (int) (Math.random() * 26 + 97);
                temp += (char) intValue;
            }
            ary[j] = temp;
        }
        return ary;
    }

    @Override public void doTest()
    {
        String[] nodes =
                {"10.10.25.11:6379", "10.10.25.12:6379", "10.10.25.13:6379", "10.10.25.14:6379", "10.10.25.15:6379"};

        int keyCount = 10000;
        String[] keys = genKeys(keyCount);
        String visitKey = "fasdfwe4fazxg";

        LOGGER.info("--------初始状态-------");
        ConsistentHash ch = new ConsistentHash(nodes, 200);

        LOGGER.info("RingSize {}, map {}", ch.getRealNodeSize(), visitKey + " -> " + ch.visit("visitKey"));
        ch.testLoadBalance(keys);

        LOGGER.info("--------模拟上线-------");
        String[] onLine = {"10.10.25.20:6379", "10.10.25.21:6379"};
        ch.addNode(onLine);

        LOGGER.info("RingSize {}, map {}", ch.getRealNodeSize(), visitKey + " -> " + ch.visit("visitKey"));
        ch.testLoadBalance(keys);

        LOGGER.info("--------模拟下线-------");
        String[] offLine = {"10.10.25.11:6379", "10.10.25.14:6379"};
        ch.removeNode(offLine);

        LOGGER.info("RingSize {}, map {}", ch.getRealNodeSize(), visitKey + " -> " + ch.visit("visitKey"));
        ch.testLoadBalance(keys);

        LOGGER.info("--------获取元数据-------");
        ch.getMetaData();
    }
}
