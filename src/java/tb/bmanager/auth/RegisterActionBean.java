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
import java.util.Date;
import javax.ejb.EJB;
import tb.bmanager.entity.UserEntity;
import tb.bmanager.entitymanager.UserEntityFacade;
import tb.bmanager.util.BCrypt;

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
    
    @EJB
    private UserEntityFacade userFacade;
    
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
     * Preforms the registration of a user filling in some empty information before
     * pushing the UserEntity to the database.
     * 
     * @param u - UserEntity to push to the database.
     */
    public void preformRegistration(UserEntity u) {
        u.setFollowers(0);
        u.setFollows(0);
        u.setProjects(0);
        u.setJoindate(new Date());
        
        //Hash the password
        u.setPassword(BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));
        
        //Post the record.
        userFacade.create(u);
    }
}
