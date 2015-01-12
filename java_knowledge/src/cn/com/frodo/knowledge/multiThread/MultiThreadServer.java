package cn.com.frodo.knowledge.multiThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer {
	private int port = 8821;
	private ServerSocket serverSocket;
	// 线程池
	private ExecutorService executorService;
	private final int POOL_SIZE = 10;

	public MultiThreadServer() throws IOException {
		serverSocket = new ServerSocket(port);
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
		printLog("服务器启动");
	}

	public void service() {
		while (true) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				executorService.execute(new Handler(socket));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new MultiThreadServer().service();
	}

	class Handler implements Runnable {
		private Socket socket;

		public Handler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				printLog("New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
				BufferedReader br = getReader(socket);
				PrintWriter pw = getWriter(socket);
				String msg = null;
				while ((msg = br.readLine()) != null) {
					printLog(msg);
					pw.println(echo(msg));
					if (msg.equals("bye"))
						break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (socket != null) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		//发送消息
		private PrintWriter getWriter(Socket socket) throws IOException {
			OutputStream out = socket.getOutputStream();
			return new PrintWriter(out, true);
		}

		//接受消息
		private BufferedReader getReader(Socket socket) throws IOException {
			InputStream in = socket.getInputStream();
			return new BufferedReader(new InputStreamReader(in));
		}

		public String echo(String msg) {
			return "echo: " + msg;
		}
	}

	public void printLog(String log) {
		System.out.println("[echo : " + log + "]");
	}
}
