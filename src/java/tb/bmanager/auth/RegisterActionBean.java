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
import tb.bmanager.entity.UserEntity;

/**
 * Preforms the registering of users, securing user password then pushing to 
 * database.
 * 
 * @author oskarmendel
 * @version 0.00.00
 * %name RegisterActionBean.java
 * %date 15:19:30 PM, Jun 15, 2016
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
    
    /**
     * Uses the UserEntityFacade to insert a new  user record.
     * 
     * @return - An URL to be redirected to.
     */
    public String add() {
        UserEntity u = new UserEntity();
        //u.setDisplayname(userBean.getDisplayName());
        //u.setUsername(userBean.getUsername());
        //u.setPassword(userBean.getPassword());
        
        //userFacade.create(u);
        
        return "index";//Url ?
    }
}
