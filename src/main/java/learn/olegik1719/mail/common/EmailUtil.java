package learn.olegik1719.mail.common;

import java.util.Date;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

    /**
     * Utility method to send simple HTML email
     *
     * @param session -- connection
     * @param toEmail -- to address
     * @param subject -- subject of message
     * @param body -- body of message
     */
    public static void sendEmail(Session session, String fromEmail, String toEmail, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(fromEmail);

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            //System.out.println("Message is ready");
//            Enumeration<String> enumeration = msg.getAllHeaderLines();
//            while (enumeration.hasMoreElements()) {
//                System.out.printf("%s%n", enumeration.nextElement());
//            }
            Transport.send(msg);

            //System.out.println("EMail Sent Successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}