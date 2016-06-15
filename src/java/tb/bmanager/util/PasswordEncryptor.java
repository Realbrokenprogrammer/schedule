/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb.bmanager.util;

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
    
    protected PasswordEncryptor(){
        
    }
    
    public static PasswordEncryptor getInstance() {
        if (instance == null) {
            instance = new PasswordEncryptor();
        }
        return instance;
    }
}
