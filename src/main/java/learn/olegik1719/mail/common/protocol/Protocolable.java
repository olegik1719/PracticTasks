package learn.olegik1719.mail.common.protocol;

import learn.olegik1719.mail.common.transport.Connectable;

public interface Protocolable {
    String getType();
    String getPort(Connectable connectable);
    boolean canSend();
    default boolean canGet(){
        return !canSend();
    }
}
