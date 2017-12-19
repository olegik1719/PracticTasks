package learn.olegik1719.mail.smtp;

import learn.olegik1719.mail.common.transport.MailProtocols;
import learn.olegik1719.mail.common.transport.SSL;
import learn.olegik1719.mail.common.transport.Connectable;
import learn.olegik1719.mail.common.transport.TLS;

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
        Connectable connectTLS = new TLS(MailProtocols.SMTP,properties);
        for(Object str:properties.keySet()){
            System.out.printf("%s : %s%n", str, properties.getProperty((String) str));
        }
        Session sessionTLS = connectTLS.getSession();
        System.out.println("Session TLS created");
        EmailUtil.sendEmail(sessionTLS, fromSend, toSend, "SSLEmail Testing Subject", "SSLEmail Testing Body");
        for(Object str:properties.keySet()){
            System.out.printf("%s : %s%n", str, properties.getProperty((String) str));
        }
        Connectable connectSSL = new SSL(MailProtocols.SMTP,properties);
        Session sessionSSL = connectSSL.getSession();
        System.out.println("Session SSL created");
        EmailUtil.sendEmail(sessionSSL, fromSend, toSend, "SSLEmail Testing Subject", "SSLEmail Testing Body");
        for(Object str:properties.keySet()){
            System.out.printf("%s : %s%n", str, properties.getProperty((String) str));
        }
    }
}

