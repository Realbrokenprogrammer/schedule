/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb.bmanager.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import tb.bmanager.auth.RegisterActionBean;

/**
 *
 * @author oskarmendel
 */
@Named(value = "indexManagedBean")
@Dependent
public class IndexManagedBean {
    
    RegisterActionBean register = new RegisterActionBean();
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    /**
     * Creates a new instance of IndexManagedBean
     */
    public IndexManagedBean() {
        register.getConnection();
    }
    
    public String helloMsg(){
        return "Welcome from TeamB";
    }
    
}
