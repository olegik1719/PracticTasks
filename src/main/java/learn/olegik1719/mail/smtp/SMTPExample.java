package learn.olegik1719.mail.smtp;

import learn.olegik1719.mail.common.transport.MailProtocols;
import learn.olegik1719.mail.common.transport.SSL;

import javax.mail.Session;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SMTPExample {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/learn/olegik1719/mail/config.properties"));
        SSL ssl = new SSL(MailProtocols.SMTP,properties);
        Session session = ssl.getSession();
        System.out.println("Session created");
        EmailUtil.sendEmail(session, properties.getProperty("from"), properties.getProperty("to"), "SSLEmail Testing Subject", "SSLEmail Testing Body");
    }
}

