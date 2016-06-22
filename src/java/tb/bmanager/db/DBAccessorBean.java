/*
 * Copyright (C) 2016 oskarmendel
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package tb.bmanager.db;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.ejb.Stateful;

/**
 * Database wrapper for manually performing queries to the database 
 * through JDBC.
 * 
 * @author oskarmendel
 * @version 0.00.00
 * %name DBAccessorBean.java
 * %date 15:24:36 PM, Jun 15, 2016
 */
@Stateful
public class DBAccessorBean implements DBAccessorBeanLocal {

    
    protected Connection conn = null;
    protected Statement stmt = null;
    protected ResultSet rs = null;
    
    /**
     * Constructor initializing the connection to specified Database.
     * When finished a connection should be established.
     */
    public DBAccessorBean (){
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
                        String last = rs.getString("username");

                        //Display values
                        System.out.print("ID: " + id);
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
    
    /**
     * Manual insert statement.
     */
    private void insert(){
        
    }
    
    /**
     * Manual update statement.
     */
    private void update(){
        
    }
    
    /**
     * Manual save statement.
     */
    private void save(){
        
    }
    
    /**
     * Manual delete statement.
     */
    private void delete(){
        
    }
}
