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

import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import tb.bmanager.auth.AuthenticationActionBeanLocal;
import tb.bmanager.entitymanager.UserEntityFacade;
import tb.bmanager.util.validation.UserValidation;

/**
 * Controller for the login view.
 * 
 * @author oskarmendel
 * @version 0.00.00
 * %name LoginManagedBean.java
 * %date 14:38:19 PM, Jun 18, 2016
 */
@Named(value = "loginManagedBean")
@SessionScoped
public class LoginManagedBean implements Serializable{

    @EJB
    private AuthenticationActionBeanLocal login;
    
    @EJB
    private UserEntityFacade userFacade;
    
    String username;
    String password;
    
    private UserValidation userValidation;
    
    /**
     * Creates a new instance of LoginManagedBean
     */
    public LoginManagedBean() {
    }
    
    /**
     * Called when user clicks the login button in the view, validates input then
     * sends input to AuthenticationActionBean to authenticate user.
     */
    public void verifyLogin() throws IOException {
        //Thread local variable to prevent multiple forms to submit different info.
        String username = this.username;
        String password = this.password;
        
        userValidation = UserValidation.getInstance();
        
        if(failedLogins() < 5) {
        
            //Check username is within length & if its taken or not
            if(!userValidation.validateUsername(username)) {
                Messages.addGlobalError("Your specified username is too long or contains illegal characters.");
            }else if (userFacade.findByUsername(username) == null){
                Messages.addGlobalError("User does not exist.");
            }

            //Check if password is strong enough
            if(!userValidation.validatePassword(password)) {
                Messages.addGlobalError("Your password needs to be longer than 4 characters.");
            }

            if (login.preformAuthentication(username, password) == true) {
                Faces.redirect("b/dashboard.xhtml");
            }
            
        } else {
            Messages.addGlobalError("Too many failed login attempts, try again later.");
        }
    }
    
    /**
     * Amount of failed login attempts from this IP Address.
     * 
     * @return amount of failed login attempts from this IP address.
     */
    public int failedLogins() {
        return login.getLoginAttempts();
    }
    
    /**
     * 
     * @param username - the username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * 
     * @return the username of the user.
     */
    public String getUsername(){
        return this.username;
    }
    
    /**
     * 
     * @return the password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password - the password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
