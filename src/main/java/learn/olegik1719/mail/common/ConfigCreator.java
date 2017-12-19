package learn.olegik1719.mail.common;

import java.io.IOException;
import java.util.Properties;

public class ConfigCreator {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("serverSMTP","smtp.srv");
        properties.setProperty("serverPOP3","pop.srv");
        properties.setProperty("serverIMAP","imap.srv");
        properties.setProperty("login","post.login");
        properties.setProperty("password","post.pass");
        properties.setProperty("from","post.from");
        properties.setProperty("to","post.to");
        properties.setProperty("portPOP3","port.pop3");
        properties.setProperty("portIMAP","port.imap");
        properties.setProperty("portSMTP","port.smtp");
        properties.store(System.out,"Comments");
    }
}
