package cn.com.frodo.concurrent.chapter_7;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 通过改写interrupt方法将非标准的取消操作封装在Thread中
 * Created by frodo on 2015/1/16.
 */
public class ReaderThread extends Thread {
    private static final int BUFSZ = 512;
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[BUFSZ];
            while (true) {
                int count = in.read(buf);
                if (count < 0) {
                    break;
                } else if (count > 0) {
                    processBuffer(buf, count);
                }
            }
        } catch (IOException e) {
            /* Allow thread to exit */
        }
    }

    private void processBuffer(byte[] buf, int count) {
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            super.interrupt();
        }
    }
}
