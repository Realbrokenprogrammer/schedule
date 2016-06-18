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

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
@RequestScoped
public class LoginManagedBean {

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
    
    public void verifyLogin() {
        userValidation = UserValidation.getInstance();
        
        //Check username is within length & if its taken or not
        if(!userValidation.validateUsername(username)) {
            String message = "Your specified username is too long or contains illegal characters.";
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
        }else if (userFacade.findByUsername(username) == null){
            String message = "User doesnt exists.";
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
        }
        
        //Check if password is strong enough
        if(!userValidation.validatePassword(password)) {
            String message = "Your password needs to be longer than 4 characters.";
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
        }
        
        login.preformAuthentication(username, password);
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
