package cn.com.frodo.knowledge.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(10000);
		Socket socket = server.accept();
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

		PrintWriter out = new PrintWriter(socket.getOutputStream());

		while (true) {
			String msg = in.readLine();
			System.out.println("server 接收到： " + msg);
			out.println("server ok!");
			out.flush();
			if (msg.equals("bye"))
				break;
		}

		socket.close();
	}
}
