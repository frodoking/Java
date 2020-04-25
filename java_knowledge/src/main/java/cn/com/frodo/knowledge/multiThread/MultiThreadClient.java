package cn.com.frodo.knowledge.multiThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadClient {
    public static void main(String[] args) {
        int numTasks = 10;

        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < numTasks; i++) {
            exec.execute(new Task(i));
        }
    }

    // 定义一个简单任务
    static class Task implements Runnable {
        int taskID;
        private Socket socket = null;
        private int port = 8821;

        public Task(int taskID) {
            this.taskID = taskID;
        }

        @Override
        public void run() {
            System.out.println("Task  " + taskID + " : start ");
            try {
                socket = new Socket("localhost", port);
                // 发送关闭命令
                OutputStream out = socket.getOutputStream();
                out.write("shutdown/r/n".getBytes());
                // 接受服务器的反馈
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String msg = null;
                while ((msg = br.readLine()) != null) {
                    System.out.println(msg);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
