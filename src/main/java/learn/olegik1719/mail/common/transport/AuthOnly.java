package learn.olegik1719.mail.common.transport;

import learn.olegik1719.mail.common.protocol.Protocolable;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class AuthOnly implements Connectable {
    private Properties properties;
    private Authenticator auth;
    private AuthOnly(){

    }
    public AuthOnly (Protocolable protocolable, Properties props){
        this();
        properties = new Properties();
        String login = props.getProperty("login");
        String password = props.getProperty("password");
        auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login,password);
            }
        };

        properties.put("mail.host", props.getProperty("server"+protocolable.getType().toUpperCase()));
        properties.put("mail."+protocolable.getType()+".socketFactory.port", props.getProperty("port"+protocolable.getType().toUpperCase(), protocolable.getPort(this)));
        properties.put("mail."+protocolable.getType()+".auth", "true");

    }

    @Override
    public String getCryptType() {
        return "AuthOnly";
    }

    @Override
    public Session getSession() {
        return Session.getDefaultInstance(properties, auth);
    }

}
