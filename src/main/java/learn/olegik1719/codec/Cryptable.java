package learn.olegik1719.codec;

public interface Cryptable {
    String cryptString(String original);
   /**
     * @return crypt EmptyString
     */
    String cryptString();

    /**
     *
     * @param original -- String for crypt
     * @param needCrypt -- Sign for crypting, if you didn't want use crypt, send false for it.
     * @return String result = @needCrypt? cryptString(original) : original;
     */
    String cryptString(String original,boolean needCrypt);


    /**
     * @see #cryptString()
     * @see #cryptString(String, boolean)
     */
    String cryptString(boolean needCrypt);


    /**
     * @see #cryptString(String)
     * @see #cryptString()
     * @see #cryptString(String, boolean)
     * @see #cryptString(boolean)
     */
    String decryptString(String cryptString);
    String decryptString();
    String decryptString(String cryptString,boolean needDeCrypt);
    String decryptString(boolean needDeCrypt);
}
