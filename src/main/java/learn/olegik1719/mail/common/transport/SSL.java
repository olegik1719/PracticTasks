package learn.olegik1719.mail.common.transport;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SSL implements Connectable{
    Properties properties;
    Authenticator auth;
    public SSL(Protocolable protocolable, Properties props){
        properties = new Properties();
        String login = (String) props.remove("login");
        String password = (String) props.remove("password");
        auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login,password);
            }
        };
        properties.put("mail.host", properties.getProperty("server"));
        properties.put("mail."+protocolable.getType()+".socketFactory.port", "465");
    }

    @Override
    public String getCryptType() {
        return "SSL";
    }

    @Override
    public Session getSession() {
        return null;
    }
}
