package cn.com.frodo.knowledge.utils;

import java.io.File;
import java.io.IOException;

/**
 * Created by frodo on 2015/4/9.
 */
public abstract class PartitionFile {
    /**
     * 单个文件设置的字节数
     */
    public static long MAX_BYTE = 1024 * 1024 * 1024;

    /**
     * 获取可以分割的文件数
     *
     * @param filePath
     * @param fileByte
     * @return
     */
    public int getPartitionFileNum(long fileByte, String filePath) {
        if (MAX_BYTE < fileByte) {
            if (fileByte % MAX_BYTE == 0) {
                return (int) (fileByte / MAX_BYTE);
            } else {
                return (int) (fileByte / MAX_BYTE) + 1;
            }
        }
        return 1;
    }

    /**
     * 获取文件长度
     *
     * @param file
     * @return
     * @throws IOException
     */
    public abstract long getFileLength(File file) throws IOException;

    /**
     * 分割Byte文件
     *
     * @param srcFile
     * @throws IOException
     * @throws IOException
     */
    public abstract String[] partitionFile(File srcFile, int partitionFileNum) throws IOException;

    /**
     * 合并文件
     *
     * @param files
     * @param newFile
     * @throws IOException
     */
    public abstract void uniteFile(String[] files, String newFile) throws IOException;
}
