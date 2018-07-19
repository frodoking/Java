package cn.com.frodo.knowledge.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class TestEmail {
	public static void sentEmail(MailInfo mailInfo) {
		Email email = new SimpleEmail();
		email.setHostName(mailInfo.getMailServerHost());
		email.setSmtpPort(mailInfo.getMailServerPort());
		email.setAuthenticator(new DefaultAuthenticator(mailInfo.getUserName(), mailInfo.getPassword()));
		email.setSSLOnConnect(true);
		try {
			email.setFrom(mailInfo.getFromAddress());
			email.setSubject(mailInfo.getSubject());
			email.setMsg(mailInfo.getContent());
			for (String toAddress : mailInfo.getToAddress()) {
				email.addTo(toAddress);
			}
			String result = email.send();
			System.out.println(result);
		} catch (EmailException e) {
			System.out.println("Connection timed out: connect");
			e.printStackTrace();
		}
	}
	public static void client(){
	}
	

	public static void main(String[] args) {
		MailInfo mailInfo = new MailInfo();
		mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setMailServerPort(993);
		mailInfo.setUserName("1158916897");
		mailInfo.setPassword("460642557");
		mailInfo.setFromAddress("1158916897@qq.com");
		mailInfo.setSubject("TestMail");
		mailInfo.setContent("This is a test mail ... :-)");
		String[] toAddress = { "frodoking@kingsoft.com" };
		mailInfo.setToAddress(toAddress);
		TestEmail.sentEmail(mailInfo);
	}
}
