package learn.olegik1719.mail.common.transport;

public enum MailProtocols implements Protocolable{
    SMTP{
        @Override
        public String getType(){
            return "smtp";
        }

        @Override
        public String getPort(Connectable connectable) {
            return null;
        }
    }
}
