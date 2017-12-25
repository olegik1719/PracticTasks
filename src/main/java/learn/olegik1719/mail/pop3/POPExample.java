package learn.olegik1719.mail.pop3;

import learn.olegik1719.mail.common.protocol.MailProtocols;
import learn.olegik1719.mail.common.protocol.Protocolable;
import learn.olegik1719.mail.common.transport.Connectable;
import learn.olegik1719.mail.common.transport.SSL;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class POPExample {
    //private static boolean textIsHtml = true;

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/learn/olegik1719/mail/common/config.properties"));
        Protocolable protocolable = MailProtocols.IMAP;
        Connectable connect = new SSL(protocolable,properties);
        Session session = connect.getSession();
        Store store = session.getStore(protocolable.getType());
        store.connect();
        Folder folder = store.getFolder("inbox");
        //store.
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


            //if (msg.isMimeType("text/*"))
                System.out.println(getText(msg));


            //msg.getH
            System.out.println();
        }

        Folder[] f = store.getDefaultFolder().list();
        for(Folder fd:f)
            System.out.println(">> "+fd.getName());

        folder.close(true);
        store.close();
    }
    private static String getText(Part p) throws
            MessagingException, IOException {
        if (p.isMimeType("text/*")) {
            String s = (String)p.getContent();
            //textIsHtml = p.isMimeType("text/html");
            return s;
        }

        if (p.isMimeType("multipart/alternative")) {
            // prefer html text over plain text
            Multipart mp = (Multipart)p.getContent();
            String text = null;
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain")) {
                    if (text == null) text = getText(bp);
                    continue;
                } else if (bp.isMimeType("text/html")) {
                    String s = getText(bp);
                    if (s != null)
                        return s;
                } else {
                    return getText(bp);
                }
            }
            return text;
        } else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart)p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String s = getText(mp.getBodyPart(i));
                if (s != null)
                    return s;
            }
        }

        return null;
    }
}
