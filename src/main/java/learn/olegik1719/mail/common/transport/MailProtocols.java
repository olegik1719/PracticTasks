package learn.olegik1719.mail.common.transport;

public enum MailProtocols implements Protocolable{
    SMTP{
        @Override
        public String getType(){
            return "smtp";
        }

        @Override
        public boolean canSend(){
            return true;
        }

        @Override
        public String getPort(Connectable connectable) {
            switch(connectable.getCryptType()){
                case "SSL":return  "465";
                case  "TLS":return "965";
                default: return "25";
            }
        }
    }
}
