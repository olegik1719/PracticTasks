package learn.olegik1719.mail.smtp;

import learn.olegik1719.mail.common.EmailUtil;
import learn.olegik1719.mail.common.protocol.MailProtocols;
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
        properties.load(new FileInputStream("src/main/java/learn/olegik1719/mail/common/config.properties"));
        String fromSend = (String)properties.remove("from");
        String toSend = (String)properties.remove("to");
        Connectable connectSSL = new SSL(MailProtocols.SMTP,properties);
        Session sessionSSL = connectSSL.getSession();
        //System.out.println("Session SSL created");
        EmailUtil.sendEmail(sessionSSL, fromSend, toSend, "Testing Subject1", "SSLEmail Testing Body");
        EmailUtil.sendEmail(sessionSSL, fromSend, toSend, "Testing Subject2", "SSLEmail Testing Body");
    }
}

