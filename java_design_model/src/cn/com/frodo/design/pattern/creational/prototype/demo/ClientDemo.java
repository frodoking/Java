package cn.com.frodo.design.pattern.creational.prototype.demo;

import java.util.Random;

public class ClientDemo {
	private static int MAX_COUNT = 6;

	public static void main(String[] args) {
		int i = 0;
		Mail mail = new Mail("抽奖活动", "五一活动通知...");
		mail.setTail("商场解释权...");
		while (i < MAX_COUNT) {
			Mail cloneMail = mail.clone();
			cloneMail.setAppellation(getRandomString(8) + "先生（女士）");
			cloneMail.setReceiver(getRandomString(8) + "@" + getRandomString(5)
					+ ".com");
			sendMail(cloneMail);
			i++;
		}
	}

	private static void sendMail(Mail mail) {
		System.out.println("标题  " + mail.getSubject() + "\t 收件人  "
				+ mail.getReceiver() + "\t .... 发送成功！！！");
	}

	public static String getRandomString(int maxLength) {
		String source = "abcdefghijklmnopqrstuvwxyz";
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		for (int i = 0; i < maxLength; i++) {
			sb.append(source.charAt(r.nextInt(source.length())));
		}
		return sb.toString();
	}
}
