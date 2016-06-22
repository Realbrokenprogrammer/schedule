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
     * Creates a new instance of IndexManagedBean.
     */
    public IndexManagedBean() {
        
    }
    
    public String helloMsg(){
        return "Welcome from TeamB";
    }
    
}
