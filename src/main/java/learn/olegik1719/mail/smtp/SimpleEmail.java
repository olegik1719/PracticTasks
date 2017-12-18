package learn.olegik1719.mail.smtp;

import java.util.Properties;

import javax.mail.Session;

public class SimpleEmail {
	
	public static void main(String[] args) {
		
	    System.out.println("SimpleEmail Start");
		
	    String smtpHostServer = "smtp.journaldev.com";
	    String emailID = "pankaj@journaldev.com";
	    
	    Properties props = System.getProperties();

	    props.put("mail.smtp.host", smtpHostServer);

	    Session session = Session.getInstance(props, null);
	    
	    EmailUtil.sendEmail(session,"nobody", emailID,"SimpleEmail Testing Subject", "SimpleEmail Testing Body");
	}

}