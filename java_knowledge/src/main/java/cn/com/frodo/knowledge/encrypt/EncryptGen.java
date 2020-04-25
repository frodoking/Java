package cn.com.frodo.knowledge.encrypt;

import cn.com.frodo.knowledge.common.FileHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 加密解密
 *
 * @author frodoking
 */
public class EncryptGen {
    // static boolean debug =false ;
    // 加密KEY不能随便改动
    static final byte[] KEYVALUE = "6^)(9-p35@%3#4S!4S0)$Y%%^&5(j.&^&o(*0)$Y%!#O@*GpG@=+@j.&6^)(0-=+".getBytes();
    static final int BUFFERLEN = 512;

    public EncryptGen() {
    }

    /**
     * 对文件进行加密
     *
     * @param String oldFile 原始要加密的文件
     * @param String newFile 加密后的文件
     * @return
     */
    public static void encryptFile(String oldFile, String newFile) throws Exception {
        FileInputStream in = new FileInputStream(oldFile);
        File file = new File(newFile);
        if (!file.exists())
            file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        int c, pos, keylen;
        pos = 0;
        keylen = KEYVALUE.length;
        byte buffer[] = new byte[BUFFERLEN];
        while ((c = in.read(buffer)) != -1) {
            for (int i = 0; i < c; i++) {
                buffer[i] ^= KEYVALUE[pos];
                out.write(buffer[i]);
                pos++;
                if (pos == keylen)
                    pos = 0;
            }
        }
        in.close();
        out.close();
    }

    /**
     * 对文件进行解密
     *
     * @param String oldFile 原始要解密的文件
     * @param String newFile 解密后的文件
     * @return
     */
    public static void decryptFile(String oldFile, String newFile) throws Exception {
        FileInputStream in = new FileInputStream(oldFile);
        File file = new File(newFile);
        if (!file.exists())
            file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        int c, pos, keylen;
        pos = 0;
        keylen = KEYVALUE.length;
        byte buffer[] = new byte[BUFFERLEN];
        while ((c = in.read(buffer)) != -1) {
            for (int i = 0; i < c; i++) {
                buffer[i] ^= KEYVALUE[pos];
                out.write(buffer[i]);
                pos++;
                if (pos == keylen)
                    pos = 0;
            }
        }
        in.close();
        out.close();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            String oldFile = new String("F:/111/aa.txt");
            String newFile_en = new String("F:/111/aa_en.txt");

            String newFile_x = new String("F:/111/aa_x.txt");

            System.out.println("-----------------------原始文件------------------");
            FileHelper.readTxtFile(oldFile);
            System.out.println("-----------------------开始加密------------------");
            encryptFile(oldFile, newFile_en);
            System.out.println("-----------------------加密 后的文件------------------");
            FileHelper.readTxtFile(newFile_en);

            System.out.println("///////////ok////////////////");

            System.out.println("-----------------------开始加密------------------");
            decryptFile(newFile_en, newFile_x);
            System.out.println("-----------------------解密 后的文件------------------");
            FileHelper.readTxtFile(newFile_x);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
