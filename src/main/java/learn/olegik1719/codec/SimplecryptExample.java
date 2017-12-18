package learn.olegik1719.codec;

public class SimplecryptExample {
    public static void main(String[] args) {
        Cryptable cryptable = new Simplecrypt(5);
        String toCrypt = "Hello world!";
        String toDecrypt = cryptable.cryptString(toCrypt);
        System.out.printf("%s%n",toCrypt);
        System.out.printf("%s%n",toDecrypt);
        System.out.printf("%s%n",cryptable.decryptString(toDecrypt));
    }
}
