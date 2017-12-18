package learn.olegik1719.mail.common.transport;

import javax.mail.Session;

interface Connectable {
    String getCryptType();
    Session getSession();
}
