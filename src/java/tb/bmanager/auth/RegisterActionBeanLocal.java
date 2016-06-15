/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb.bmanager.auth;

import javax.ejb.Local;

/**
 * Allows the business methods of RegisterActionBean to be publicly used locally 
 * through enterprise bean injection.
 * 
 * @author oskarmendel
 * @version 0.00.00
 * %name RegisterActionBeanLocal.java
 * %date 15:19:30 PM, Jun 15, 2016
 */
@Local
public interface RegisterActionBeanLocal {
    public void getConnection();
}
