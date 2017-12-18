package learn.olegik1719.codec;

import java.util.Random;

public class Simplecrypt implements Cryptable {
    private static final String[] dictionary ={ "abcdefghijklmnopqrstuvwxyz",
                                                "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
                                                "абвгдеёжзийклмнопрстуфхцчшщъыьэюя",
                                                "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ",
                                                "0123456789"};
    private static final String letters_sgn = "@#$%^&*()_+=-";

    private int module  = 7;

    public Simplecrypt(){
    }

    public Simplecrypt(int module){
        this.module = module;
    }

    private StringBuilder randomString(int length, boolean begin){
        StringBuilder stringBuilder = new StringBuilder(length);
        StringBuilder stringBuilderTemp = new StringBuilder();
        for (int i = 0; i < dictionary.length ; i++) {
            stringBuilderTemp.append(dictionary[i]);
        }
        stringBuilderTemp.append(letters_sgn);
        if (!begin) stringBuilderTemp.append('!');
        Random random = new Random();
        int length_temp = stringBuilderTemp.length();
        for (int i = 0; i < length; i++){
            stringBuilder.append(stringBuilderTemp.charAt(random.nextInt(length_temp)));
        }

        return stringBuilder;
    }

    private StringBuilder randomString(int length){
        return randomString(length, false);
    }

    private StringBuilder randomString(){
        return randomString(100);
    }

    private int getAddPosition(char last){
        int result = 0;
        for (int i = 0; i < dictionary.length; i++) {
            result = dictionary[i].indexOf(last) + 1;
        }
        if (result > 0) {
            result %= module;
            result++;
        }else{
            result = 4;
        }
        return result;
    }

    private StringBuilder cryptMain(String original){
        StringBuilder result = randomString();
        int position = 0;
        for (int i = 0; i < original.length(); i++) {
            if (position + 1 > result.length()) result.append(randomString());
            char current = original.charAt(i);
            result.setCharAt(position,current);
            position += getAddPosition(current);
        }
        return result;
    }

    private String decryptMain(String cryptString, int length){
        StringBuilder result = new StringBuilder(length);
        int position = 1;
        char current;
        for (int i = 0; (i < length)&&(position < cryptString.length()); i++) {
            current = cryptString.charAt(position);
            result.append(current);
            position += getAddPosition(current);

        }
        return result.toString();
    }

    @Override
    public String cryptString(String original, boolean needCrypt) {
        return needCrypt?cryptString(original):original;
    }

    @Override
    public String cryptString(boolean needCrypt) {
        return cryptString("",needCrypt);
    }

    public String cryptString(String original) {
        StringBuilder result = randomString(original.length(),true)
                .append('!')
                .append(cryptMain(original));
        return result.toString();
    }

    public String cryptString() {
        return cryptString("");
    }

    @Override
    public String decryptString(String cryptString, boolean needDeCrypt) {
        return needDeCrypt?decryptString(cryptString):cryptString;
    }

    @Override
    public String decryptString(boolean needDecrypt) {
        return decryptString("",needDecrypt);
    }

    public String decryptString(String cryptString) {
        int position = cryptString.indexOf('!');
        if ((position > 0) && (position < cryptString.length())) {
            return decryptMain(cryptString.substring(position),position);
        }else{
            return "";
        }

    }

    public String decryptString() {
        return decryptString("");
    }
}
