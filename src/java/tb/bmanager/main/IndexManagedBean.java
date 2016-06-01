/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb.bmanager.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author oskarmendel
 */
@Named(value = "indexManagedBean")
@Dependent
public class IndexManagedBean {

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    /**
     * Creates a new instance of IndexManagedBean
     */
    public IndexManagedBean() {
        //THIS WORKS.
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee?" +
                    "user=root&password=qwer1234");
            
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        } finally {
            if (conn != null) {
               try {
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery("SELECT * FROM USER");

                    // or alternatively, if you don't know ahead of time that
                    // the query will be a SELECT...

                    //if (stmt.execute("SELECT foo FROM bar")) {
                    //    rs = stmt.getResultSet();
                    //}

                    // Now do something with the ResultSet ....
                    while(rs.next()){
                        //Retrieve by column name
                        int id  = rs.getInt("id");
                        String first = rs.getString("user_type");
                        String last = rs.getString("username");

                        //Display values
                        System.out.print("ID: " + id);
                        System.out.print(", User Type: " + first);
                        System.out.println(", Username: " + last);
                     }
                } catch (SQLException ex){
                    // handle any errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                } finally {
                    // it is a good idea to release
                    // resources in a finally{} block
                    // in reverse-order of their creation
                    // if they are no-longer needed

                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException sqlEx) { } // ignore

                        rs = null;
                    }

                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException sqlEx) { } // ignore

                        stmt = null;
                    }
                }
            }
        }
    }
    
    public String helloMsg(){
        return "Welcome from TeamB";
    }
    
}
