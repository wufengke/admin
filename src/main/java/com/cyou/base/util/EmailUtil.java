package com.cyou.base.util;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class EmailUtil {

	static String emailServerIp = "";
	static String emailFrom = "";
	static String emailTo = "";
	static String emailUserName = "";
	static String emailPassword = "";

	static {
		emailServerIp = PropertyUtil.getProperty("email_server_ip");
		emailFrom = PropertyUtil.getProperty("email_from");
		emailTo = PropertyUtil.getProperty("email_to");
		emailUserName = PropertyUtil.getProperty("email_name");
		emailPassword = PropertyUtil.getProperty("email_password");
	}

	// 发送邮件
	public static void sendEmail(String content, String subject) {
		MyAuthenticator authenticator = null;
		try {
			authenticator = new MyAuthenticator(emailUserName, emailPassword);
			boolean sessionDebug = false;
			String[] emailTos = emailTo.split(";");
			// String emailServerIp="192.168.95.47";
			// String emailServerIp="smtp.cyou-inc.com";
			// 固定不变
			// String from="newsletter.cyou@sohu.com";
			Properties props = System.getProperties();
			props.put("mail.smtp.localhost", "127.0.0.1");
			props.put("mail.smtp.auth", "true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransport");
			props.put("mail.smtp.host", emailServerIp);
			props.put("mail.mime.charset", "utf-8");
			Session session = Session.getDefaultInstance(props, authenticator);
			session.setDebug(sessionDebug);

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(emailFrom));
			InternetAddress[] address = new InternetAddress[emailTos.length];

			for (int i = 0; i < emailTos.length; i++) {
				address[i] = new InternetAddress(emailTos[i]);
			}

			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSentDate(new Date());
			msg.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));
			msg.setContent(new String(content.getBytes("utf-8"), "iso-8859-1"),
					"text/html");
			msg.addHeader("Content-Transfer-Encoding", "base64");
			msg.addHeader("Content-Type", "text/html;charset=utf-8");
			msg.saveChanges();
			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		EmailUtil.sendEmail("wufengke", "wufengke");
	}
}

class MyAuthenticator extends Authenticator {
	private String userName;
	private String password;

	public MyAuthenticator() {

	}

	public MyAuthenticator(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}
