package learn.olegik1719.mail.common.transport;

import javax.mail.Session;

public interface Connectable {
    String getCryptType();
    Session getSession();
}
