package ua.nure.chernykh.practice3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part4 {


    /* Output should be
        912EC803B2CE49E4A541068D495AB570
        F0E4C2F76C58916EC258F246851BEA091D14D4247A2FC3E18694461B1816E13B
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(hash("asdf", "MD5"));
        System.out.println(hash("asdf", "SHA-256"));
    }

    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(input.getBytes());
        byte[] hash = digest.digest();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < hash.length; i++) {
            sb.append(String.format("%02X", hash[i]));
        }
        return sb.toString();
    }

}
