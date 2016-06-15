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
import javax.inject.Named;
import javax.faces.bean.ViewScoped;

/**
 * Controller for the index view.
 * 
 * @author oskarmendel
 * @version 0.00.00
 * %name IndexManagedBean.java
 * %date 15:33:43 PM, Jun 15, 2016
 */
@Named(value = "indexManagedBean")
@ViewScoped
public class IndexManagedBean implements Serializable{
    
    //Injecting EJBs
    //@EJB 
    //private RegisterActionBeanLocal register;
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    /**
     * Creates a new instance of IndexManagedBean
     */
    public IndexManagedBean() {
        
    }
    
    public String helloMsg(){
        return "Welcome from TeamB";
    }
    
}
