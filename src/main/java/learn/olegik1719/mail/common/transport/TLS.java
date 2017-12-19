package learn.olegik1719.mail.common.transport;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class TLS implements Connectable{
    Properties properties;
    Authenticator auth;
    private TLS(){

    }
    public TLS(Protocolable protocolable, Properties props){
        this();
        properties = new Properties();
        String login = (String) props.remove("login");
        String password = (String) props.remove("password");
        auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login,password);
            }
        };
        properties.put("mail.host", props.getProperty("server"+protocolable.getType().toUpperCase()));
        properties.put("mail."+protocolable.getType()+".socketFactory.port", props.getProperty("port"+protocolable.getType().toUpperCase(), protocolable.getPort(this)));
        properties.put("mail."+protocolable.getType()+".socketFactory.class",             "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        properties.put("mail."+protocolable.getType()+".starttls.enable", "true"); //enable STARTTLS
        properties.put("mail."+protocolable.getType()+".auth", "true");

    }

    @Override
    public String getCryptType() {
        return "TLS";
    }

    @Override
    public Session getSession() {
        return Session.getDefaultInstance(properties, auth);
    }
}