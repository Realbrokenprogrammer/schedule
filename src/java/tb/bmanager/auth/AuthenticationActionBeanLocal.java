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

import javax.ejb.Local;

/**
 * Allows the business methods of AuthenticationActionBean to be publicly used locally 
 * through enterprise bean injection.
 * 
 * @author oskarmendel
 * @version 0.00.00
 * %name AuthenticationActionBean.java
 * %date 17:23:53 PM, Jun 18, 2016
 */
@Local
public interface AuthenticationActionBeanLocal {
    
    /**
     * Verifies the specified username and password against the database by
     * using BCrypts checkpw(String plaintext, String hashed) method.
     * 
     * @param username - specified username.
     * @param password - specified password.
     */
    public boolean preformAuthentication(String username, String password);
    
    /**
     * 
     */
    public void preformLogout();
    
    /**
     * Get the number of login attempts from clients IP address.
     * 
     * @return the amount of login attempts that has been made from the clients
     * current IP address.
     */
    public int getLoginAttempts();
}
