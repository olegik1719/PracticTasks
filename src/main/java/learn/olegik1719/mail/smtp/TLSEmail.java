package learn.olegik1719.mail.smtp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class TLSEmail {

	/**
	   Outgoing Mail (SMTP) Server
	   requires TLS or SSL: smtp.gmail.com (use authentication)
	   Use Authentication: Yes
	   Port for TLS/STARTTLS: 587
	 */
	public static void main(String[] args) throws IOException {
//		final String fromEmail = "myemailid@gmail.com"; //requires valid gmail id
//		final String password = "mypassword"; // correct password for gmail id
//		final String toEmail = "myemail@yahoo.com"; // can be any email id

		Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/java/learn/olegik1719/mail/smtp/config.properties"));
		final String login = properties.getProperty("login");//"myemailid@gmail.com"; //requires valid gmail id
		final String password = properties.getProperty("password");//"mypassword"; // correct password for gmail id
		final String toEmail = properties.getProperty("to");

		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		//props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
                //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(login, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		EmailUtil.sendEmail(session,properties.getProperty("from"), toEmail,"TLSEmail Testing Subject", "TLSEmail Testing Body");
		
	}

	
}