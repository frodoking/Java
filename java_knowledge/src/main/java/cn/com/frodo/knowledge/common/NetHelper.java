package cn.com.frodo.knowledge.common;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetHelper {
    /**
     * 下载网络图片到指定的目录
     *
     * @param webFileUrl
     * @param path
     * @return
     */
    public static boolean downloadWebFile(String webFileUrl, String fileName, String parentPath) {
        InputStream in = null;
        OutputStream out = null;
        try {
            // 如果路径中含有中文,则进行转码操作
            Pattern pa = Pattern.compile("[\\u4e00-\\u9fa5]", Pattern.DOTALL);
            Matcher ma = pa.matcher(webFileUrl);
            String t = null;
            while (ma.find()) {
                t = ma.group();
                webFileUrl = webFileUrl.replace(t, URLEncoder.encode(t, "UTF-8"));
            }
            // 构造URL
            URL url = new URL(webFileUrl);
            // 打开连接
            URLConnection con = url.openConnection();
            // 设置请求超时为5s
            con.setConnectTimeout(5 * 1000);
            // 输入流
            in = con.getInputStream();

            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            File sf = new File(parentPath);
            if (!sf.exists()) {
                sf.mkdirs();
            }
            out = new FileOutputStream(parentPath + fileName);
            // 开始读取
            while ((len = in.read(bs)) != -1) {
                out.write(bs, 0, len);
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                return false;
            }
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "http://www.xzuan.com/Upload/Files/11770504316710.jpg";
        System.out.println(downloadWebFile(s, "a.jpg", "D:\\"));
    }
}
