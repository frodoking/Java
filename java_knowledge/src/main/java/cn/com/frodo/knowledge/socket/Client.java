package cn.com.frodo.knowledge.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 10000);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));

		PrintWriter out = new PrintWriter(socket.getOutputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
		while (true) {
			String msg = reader.readLine();
			out.println(msg);
			out.flush();
			if (msg.equals("bye")) {
				break;
			}
			System.out.println(in.readLine());

		}
		socket.close();
	}
}