/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb.bmanager.main;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import tb.bmanager.auth.RegisterActionBean;
import tb.bmanager.auth.RegisterActionBeanLocal;

/**
 *
 * @author oskarmendel
 */
@Named(value = "indexManagedBean")
@Dependent
@ManagedBean
public class IndexManagedBean implements Serializable{
    
    @EJB 
    private RegisterActionBeanLocal register;
    //RegisterActionBean register = new RegisterActionBean();
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    /**
     * Creates a new instance of IndexManagedBean
     */
    public IndexManagedBean() {
        
    }
    
    //@PostConstruct 
    //public void init() { 
    //      This used to be what i had to do but now works in the hellomsg method
    //     register.getConnection();
    //}
    
    public String helloMsg(){
        register.getConnection();
        return "Welcome from TeamB";
    }
    
}
