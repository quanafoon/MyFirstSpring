package first.spring.demo.controllers;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class Encryption {

    public static String ALGORITHM = "AES";

    private static SecretKeySpec createKey(String key){
        StringBuilder secretKeyBuilder = new StringBuilder(key);
        while(secretKeyBuilder.length() < 16){
            secretKeyBuilder.append('0');
        }
        String secretKey = secretKeyBuilder.substring(0,16);
        return new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
    }

    public static String encrypt(String text, String secretKey) throws Exception {
        SecretKey key = createKey(secretKey);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes()));
    }

    public static String decrypt(String encryptedText, String secretKey) throws Exception {
        SecretKey key = createKey(secretKey);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText)));
    }
}
