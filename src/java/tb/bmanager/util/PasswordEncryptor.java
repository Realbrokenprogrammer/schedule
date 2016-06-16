/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb.bmanager.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Password encryption utility tools to generate salt and encrypt password.
 * 
 * @author oskarmendel
 * @version 0.00.00
 * %name PasswordEncryptor.java
 * %date 15:36:57 PM, Jun 15, 2016
 */
public class PasswordEncryptor {
    
    private static PasswordEncryptor instance = null;
    
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    
    protected PasswordEncryptor(){
        
    }
    
    public static PasswordEncryptor getInstance() {
        if (instance == null) {
            instance = new PasswordEncryptor();
        }
        return instance;
    }
    
    /**
     * Returns a random salt to be used to hash a password.
     * 
     * @return a 32 bytes random salt.
     */
    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[32];
        random.nextBytes(salt);
        return salt;
    }
    
    /**
     * Returns a salted and hashed password using the provided hash.
     * Note - side effect: the password is destroyed (the char[] is filled with zeros)
     * 
     * @param password
     * @param salt
     * 
     * @return 
     */
    public static byte[] hash(char[] password, byte[] salt) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, ITERATIONS, KEY_LENGTH );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            return res;
        }catch(NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException( e );
        }
    }
    
    /**
     * Returns true if the given password and salt match the hashed value, false otherwise.
     * Note - side effect: the password is destroyed (the char[] is filled with zeros)
     *
     * @param password     the password to check
     * @param salt         the salt used to hash the password
     * @param expectedHash the expected hashed value of the password
     *
     * @return true if the given password and salt match the hashed value, false otherwise
     */
    public static boolean isExpectedPassword(char[] password, byte[] salt, byte[] expectedHash) {
        byte[] pwdHash = hash(password, salt);
        Arrays.fill(password, Character.MIN_VALUE);
        if (pwdHash.length != expectedHash.length) return false;
        for (int i = 0; i < pwdHash.length; i++) {
        if (pwdHash[i] != expectedHash[i]) return false;
        }
        return true;
    }
}
