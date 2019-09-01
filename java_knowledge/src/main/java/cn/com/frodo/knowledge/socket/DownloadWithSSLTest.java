package cn.com.frodo.knowledge.socket;

import cn.com.frodo.MockInterface;
import cn.com.frodo.knowledge.callback.CallMe;
import cn.com.frodo.knowledge.callback.EventNotifier;
import cn.com.frodo.knowledge.rxjava.Rxjava;

import javax.net.ssl.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DownloadWithSSLTest implements MockInterface {

    @Override
    public void doTest() {
        CallMe call = new CallMe();
        call.registerEvent();
        EventNotifier.getInstance().doWork();

        HashMap<String, Object> hash = new HashMap<String, Object>();
        hash.put("abc", String.valueOf(0));
        hash.keySet();
        hash.values();


        Set<Map.Entry<String, Object>> entrys = hash.entrySet();
        Iterator<Map.Entry<String, Object>> iter = entrys.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Object> entry = iter.next();
            entry.getKey();
            entry.getValue();
        }

        new Rxjava().doTest();

        try {
            downloadFile("http://g-cdn.quyiyuan.com/img/website/desktop/common/default/default-health-info.png", "default-health-info.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TrustManager[] TRUST_ALL_CERTS = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }
    };

    /**
     * TODO 下载文件到本地
     *
     * @param fileUrl   远程地址
     * @param fileLocal 本地路径
     * @throws Exception
     */
    public static void downloadFile(String fileUrl, String fileLocal) throws Exception {
        SSLContext sslcontext = SSLContext.getInstance("SSL");
        sslcontext.init(null, TRUST_ALL_CERTS, new java.security.SecureRandom());
        URL url = new URL(fileUrl);
        HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
            public boolean verify(String s, SSLSession sslsession) {
                System.out.println("WARNING: Hostname is not matched for cert.");
                return true;
            }
        };

        HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
        HttpURLConnection connection;
        if (url.getProtocol().equalsIgnoreCase("https")) {
            HttpsURLConnection urlCon = (HttpsURLConnection) url.openConnection();
            urlCon.setConnectTimeout(6000);
            urlCon.setReadTimeout(6000);
            connection = urlCon;
        } else {
            connection = (HttpURLConnection) url.openConnection();
        }
        int code = connection.getResponseCode();
        if (isRedirect(code)) {
            String location = connection.getHeaderField("location");

            if (location == null) {
                return;
            }

            if (location.startsWith("/")) {
                location = url.getHost() + location;
            }
            downloadFile(location, fileLocal);
            return;

        }
        if (code != HttpURLConnection.HTTP_OK) {
            throw new Exception("文件读取失败");
        }

        // 读文件流
        DataInputStream in = new DataInputStream(connection.getInputStream());
        DataOutputStream out = new DataOutputStream(new FileOutputStream(fileLocal));
        byte[] buffer = new byte[2048];
        int count = 0;
        while ((count = in.read(buffer)) > 0) {
            out.write(buffer, 0, count);
        }
        out.close();
        in.close();
    }

    public static boolean isRedirect(final int statusCode) {
        return statusCode >= 300 && statusCode < 400;
    }

}
