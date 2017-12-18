package learn.olegik1719.mail.common.transport;

import javax.mail.Authenticator;
import javax.mail.Session;
import java.util.Properties;

interface Connectable {
    default Session getSession(Properties props, Authenticator auth) {
        return Session.getInstance(props, auth);
    }
}
