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

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import tb.bmanager.entity.LoginAttemptsEntity;
import tb.bmanager.entity.UserEntity;
import tb.bmanager.entitymanager.LoginAttemptsEntityFacade;
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
@Stateful
public class AuthenticationActionBean implements AuthenticationActionBeanLocal {

    @EJB
    private UserEntityFacade userFacade;
    
    @EJB
    private LoginAttemptsEntityFacade loginAttemptsFacade;
    
    UserEntity user;
    LoginAttemptsEntity loginAttempt;
    
    /**
     * Verifies the specified username and password against the database by
     * using BCrypts checkpw(String plaintext, String hashed) method.
     * 
     * @param username - specified username.
     * @param password - specified password.
     */
    public void preformAuthentication(String username, String password) {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("Made it to perform Auth.");
        
        user = userFacade.findByUsername(username);
        
        if (user == null) {
            return;
        }
        
        if (user.getUsername().equals(username)) {
            System.out.println("Username matches");
            if(BCrypt.checkpw(password, user.getPassword())) {
                context.getExternalContext().getSessionMap().put("USER", user);
                System.out.println("Password matches");
            } else {
                addLoginAttempt();
                
                String message = "The user / password combination is wrong. ";
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
                }
        }
    }
    
    /**
     * 
     */
    public void preformLogout() {
        
    }
    
    /**
     * Creates a new entry in the LoginAttempts table by using the Entity of 
     * LoginAttemptsEntity and JPA.
     */
    public void addLoginAttempt() {
        //Store a failed login attempt in the database.
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();

        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        loginAttempt = new LoginAttemptsEntity();
        loginAttempt.setAddress(ipAddress);
        loginAttempt.setDatetime(new Date());

        loginAttemptsFacade.create(loginAttempt);
    }
    
    /**
     * Get the number of login attempts from clients IP address.
     * 
     * @return the amount of login attempts that has been made from the clients
     * current IP address.
     */
    public int getLoginAttempts() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();

        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return loginAttemptsFacade.getLoginAttempts(ipAddress);
    }
}
