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
package tb.bmanager.auth;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateful;
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
@Stateful
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
