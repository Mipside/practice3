package ua.nure.faryha.practice3;

import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.*;


public class Part4 { //part4- hashing a password using a hash algorithms

    public static final String p41 = "(?m)(^\\d{24})";
    public static final String p42 = "(?m)(\\d{4})\\s(\\d{4})";
    public static final String p43 = "(?m)(\\d{4})(\\d{4})";

    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(input.getBytes());
        byte[] hashb = digest.digest();
        return DatatypeConverter.printHexBinary(hashb);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("");
        System.out.println(hash("password", "SHA-256"));
        System.out.println(hash("passwort", "SHA-256"));

    }
}
