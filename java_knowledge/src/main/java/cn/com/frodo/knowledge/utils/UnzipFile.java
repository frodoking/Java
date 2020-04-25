package cn.com.frodo.knowledge.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by frodoking on 2018/1/12 下午3:39.
 * Description:
 */
public class UnzipFile {
    public static void main(final String[] args) throws IOException {
        final String fileZip = "/Users/frodoking/Downloads/pictures.zip";
        final byte[] buffer = new byte[1024];
        File tmpDir = new File("tmp/");
        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }
        final ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            final String fileName = zipEntry.getName();
            System.out.println("args = [" + fileName + "]");
            final File newFile = new File("tmp/" + fileName);
            final FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }
}
