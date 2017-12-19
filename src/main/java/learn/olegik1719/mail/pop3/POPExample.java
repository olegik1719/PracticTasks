package learn.olegik1719.mail.pop3;

import learn.olegik1719.mail.common.protocol.MailProtocols;
import learn.olegik1719.mail.common.protocol.Protocolable;
import learn.olegik1719.mail.common.transport.Connectable;
import learn.olegik1719.mail.common.transport.SSL;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

public class POPExample {
    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/learn/olegik1719/mail/common/config.properties"));
        Protocolable protocolable = MailProtocols.IMAP;
        Connectable connect = new SSL(protocolable,properties);
        Session session = connect.getSession();
        Store store = session.getStore(protocolable.getType());
        store.connect();
        Folder folder = store.getFolder("inbox");
        folder.open(Folder.READ_ONLY);
        Message[] messages = folder.getMessages();
        int i=0;
        for (Message msg:messages){
            System.out.println("------------ Message " + (++i) + " ------------");
            String from = InternetAddress.toString(msg.getFrom());
            if (from != null) {
                System.out.println("From: " + from);
            }
            String replyTo = InternetAddress.toString(
                    msg.getReplyTo());
            if (replyTo != null) {
                System.out.println("Reply-to: " + replyTo);
            }
            String to = InternetAddress.toString(
                    msg.getRecipients(Message.RecipientType.TO));
            if (to != null) {
                System.out.println("To: " + to);
            }
            String cc = InternetAddress.toString(
                    msg.getRecipients(Message.RecipientType.CC));
            if (cc != null) {
                System.out.println("Cc: " + cc);
            }
            String bcc = InternetAddress.toString(
                    msg.getRecipients(Message.RecipientType.BCC));
            if (bcc != null) {
                System.out.println("Bcc: " + to);
            }
            String subject = msg.getSubject();
            if (subject != null) {
                System.out.println("Subject: " + subject);
            }
            Date sent = msg.getSentDate();
            if (sent != null) {
                System.out.println("Sent: " + sent);
            }
            Date received = msg.getReceivedDate();
            if (received != null) {
                System.out.println("Received: " + received);
            }

            //msg.getH
            System.out.println();
        }

        folder.close(true);
        store.close();
    }
}
