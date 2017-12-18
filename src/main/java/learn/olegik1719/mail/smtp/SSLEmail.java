package learn.olegik1719.mail.smtp;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class SSLEmail {

    /**
     * Outgoing Mail (SMTP) Server
     * requires TLS or SSL: smtp.gmail.com (use authentication)
     * Use Authentication: Yes
     * Port for SSL: 465
     */
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/learn/olegik1719/mail/config.properties"));
        final String login = properties.getProperty("login");
        final String password = properties.getProperty("password");
        //final String toEmail = properties.getProperty("to");

        System.out.println("SSLEmail Start");
        Properties props = new Properties();
//        props.put("mail.smtp.host", properties.getProperty("server"));
//        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
//        props.put("mail.smtp.socketFactory.class",             "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
//        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
//        props.put("mail.smtp.port", "465"); //SMTP Port

        props.put("mail.host", properties.getProperty("server"));
        props.put("mail.socketFactory.port", "465"); //SSL Port
        props.put("mail.socketFactory.class",             "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.port", "465"); //SMTP Port

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);
        System.out.println("Session created");
        //EmailUtil.sendEmail(session, properties.getProperty("from"), properties.getProperty("to"), "SSLEmail Testing Subject", "SSLEmail Testing Body");
        EmailUtil.sendEmail(session, properties.getProperty("from"), properties.getProperty("to"), "SSLEmail Testing Subject", "SSLEmail Testing Body");
    }

}