/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb.bmanager.util;

/**
 *
 * @author oskarmendel
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
