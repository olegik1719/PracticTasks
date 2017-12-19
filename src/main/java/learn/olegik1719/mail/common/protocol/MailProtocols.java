package learn.olegik1719.mail.common.protocol;

import learn.olegik1719.mail.common.transport.Connectable;

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
                case "TLS":return "587";
                default: return "25";
            }
        }
    },
    POP3{
        @Override
        public String getType(){
            return "pop3";
        }

        @Override
        public boolean canSend(){
            return false;
        }

        @Override
        public String getPort(Connectable connectable) {
            switch(connectable.getCryptType()){
                case "SSL":
                case "TLS":return "995";
                default: return "110";
            }
        }
    },
    IMAP{
        @Override
        public String getType(){
            return "imap";
        }

        @Override
        public boolean canSend(){
            return false;
        }

        @Override
        public String getPort(Connectable connectable) {
            switch(connectable.getCryptType()){
                case "SSL":
                case "TLS":return "993";
                default: return "143";
            }
        }
    }
}
