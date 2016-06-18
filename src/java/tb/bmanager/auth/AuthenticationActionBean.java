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

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tb.bmanager.entity.UserEntity;
import tb.bmanager.entitymanager.UserEntityFacade;
import tb.bmanager.util.BCrypt;

/**
 * Preforms the authentication of users, creating a new session if success.
 * 
 * @author oskarmendel
 * @version 0.00.00
 * %name AuthenticationActionBean.java
 * %date 17:23:53 PM, Jun 18, 2016
 */
@Stateless
public class AuthenticationActionBean implements AuthenticationActionBeanLocal {

    @EJB
    private UserEntityFacade userFacade;
    
    UserEntity user;
    
    /**
     * 
     * @param username
     * @param password
     */
    public void preformAuthentication(String username, String password) {
        System.out.println("Made it to perform Auth.");
        
        user = userFacade.findByUsername(username);
        
        if (user == null) {
            return;
        }
        
        if (user.getUsername().equals(username)) {
            System.out.println("Username matches");
            if(BCrypt.checkpw(password, user.getPassword())) {
                System.out.println("Password matches");
            } else {
                System.out.println("Password doesnt match!!");
            }
        }
    }
}
