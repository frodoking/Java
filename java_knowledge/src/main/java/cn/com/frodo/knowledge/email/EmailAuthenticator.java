package cn.com.frodo.knowledge.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuthenticator extends Authenticator {
    private String username = "";
    private String password = "";

    public EmailAuthenticator() {
    }

    public EmailAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }

}
