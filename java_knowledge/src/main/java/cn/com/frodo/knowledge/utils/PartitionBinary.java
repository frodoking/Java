package cn.com.frodo.knowledge.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 二进制文件分割
 * Created by frodo on 2015/4/9.
 */
public class PartitionBinary extends PartitionFile {

    @Override
    public long getFileLength(File file) {
        return file.length();
    }

    @Override
    public String[] partitionFile(File srcFile, int partitionFileNum) throws IOException {
        FileInputStream fis = null;
        DataInputStream dis = null;
        FileOutputStream fos = null;
        DataOutputStream out = null;
        String[] partitions = new String[partitionFileNum];
        try {
            fis = new FileInputStream(srcFile);
            dis = new DataInputStream(fis);
            for (int i = 0; i < partitionFileNum; i++) {
                String name = null;
                if (srcFile.getName().indexOf(".") != -1) {
                    name = srcFile.getName().substring(0,
                            srcFile.getName().indexOf("."));
                } else {
                    name = srcFile.getName();
                }
                partitions[i] = srcFile.getParent() + "/" + name + "_" + i;
                fos = new FileOutputStream(partitions[i]);
                out = new DataOutputStream(fos);
                long transMaxByte = MAX_BYTE;
                while (transMaxByte > 0) {
                    byte[] b = null;
                    if (transMaxByte > 1024) {
                        b = new byte[1024];
                    } else {
                        b = new byte[(int) transMaxByte];
                    }
                    if ((dis.read(b)) != -1) {
                        fos.write(b);
                        transMaxByte = transMaxByte - b.length;
                    } else {
                        System.out.println("transMaxByte" + transMaxByte);
                        break;
                    }
                }
                out.flush();
                fos.flush();
                out.close();
                fos.close();
            }
        } catch (FileNotFoundException ex) {
            throw ex;
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (out != null) {
                out.close();
            }
        }
        return partitions;
    }

    @Override
    public void uniteFile(String[] files, String newFile) throws IOException {
        FileInputStream fis = null;
        DataInputStream dis = null;
        FileOutputStream fos = null;
        DataOutputStream out = null;
        try {
            fos = new FileOutputStream(newFile);
            out = new DataOutputStream(fos);
            for (int i = 0; i < files.length; i++) {
                fis = new FileInputStream(files[i]);
                dis = new DataInputStream(fis);
                byte[] b = new byte[1024];
                while ((dis.read(b)) != -1) {
                    fos.write(b);
                }
                out.flush();
                fos.flush();
                fis.close();
                dis.close();
            }
        } catch (FileNotFoundException ex) {
            throw ex;
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}


