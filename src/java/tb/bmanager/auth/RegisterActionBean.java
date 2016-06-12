/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb.bmanager.auth;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import tb.bmanager.db.DBAccessorBean;

/**
 *
 * @author oskarmendel
 */
@Stateless
public class RegisterActionBean implements RegisterActionBeanLocal {
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    /**
     * Gets the connection used for manual queries to the DB. 
     */
    public void getConnection(){
        //DBAccessorBean a = new DBAccessorBean();
    }
}
