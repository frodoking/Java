package cn.com.frodo.knowledge.utils;

import java.io.File;
import java.io.IOException;

/**
 * 拼接器测试端
 * Created by frodo on 2015/4/9.
 */
public class PartitionFileClient {
    public static void main(String[] args) {
        PartitionBinary partitionBinary = new PartitionBinary();
        String[] files = null;
        try {
            files = partitionBinary.partitionFile(new File("D:\\5.mp4"), 5);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("===================================");
        try {
            partitionBinary.uniteFile(files, "D:\\5_new.mp4");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
