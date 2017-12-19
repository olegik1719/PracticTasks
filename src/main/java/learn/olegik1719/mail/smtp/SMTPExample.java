package learn.olegik1719.mail.smtp;

import learn.olegik1719.mail.common.transport.MailProtocols;
import learn.olegik1719.mail.common.transport.SSL;
import learn.olegik1719.mail.common.transport.Connectable;

import javax.mail.Session;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SMTPExample {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/learn/olegik1719/mail/config.properties"));
        String fromSend = (String)properties.remove("from");
        String toSend = (String)properties.remove("to");
        Connectable connectable = new SSL(MailProtocols.SMTP,properties);
        Session session = connectable.getSession();
        System.out.println("Session created");
        EmailUtil.sendEmail(session, fromSend, toSend, "SSLEmail Testing Subject", "SSLEmail Testing Body");
    }
}

