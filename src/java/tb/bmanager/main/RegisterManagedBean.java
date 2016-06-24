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
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import tb.bmanager.auth.RegisterActionBeanLocal;
import tb.bmanager.entity.UserEntity;
import tb.bmanager.entitymanager.UserEntityFacade;
import tb.bmanager.util.validation.UserValidation;

/**
 * Controller for the register view.
 * 
 * @author oskarmendel
 * @version 0.00.00
 * %name RegisterManagedBean.java
 * %date 15:34:37 PM, Jun 15, 2016
 */
@Named(value = "registerManagedBean")
@SessionScoped
public class RegisterManagedBean  implements Serializable{

    //Injecting EJBs
    @EJB 
    private RegisterActionBeanLocal register;
    
    @EJB
    private UserEntityFacade userFacade;
    
    private UserEntity user;
    private String username;
    private String password;
    private String displayName;
    private String email;
    
    private UserValidation userValidation;
    
    /**
     * Creates a new instance of RegisterManagedBean.
     */
    public RegisterManagedBean() {
    }
    
    /**
     * Initializes a new UserEntity to prepare a new UserEntity to be created.
     */
    public void init() {

        user = new UserEntity();

        if (user == null) {
            String message = "Error couldn't initialize user.";
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }
    
    /**
     * Verify the specified data the user registering has provided.
     */
    public void verifyRegistration(){
        //Thread local variable to prevent multiple forms to submit different info.
        String username = this.username;
        String password = this.password;
        String displayName = this.displayName;
        String email = this.email;
        
        boolean gotError = false;
        
        userValidation = UserValidation.getInstance();
        
        //Check username is within length & if its taken or not
        if(!userValidation.validateUsername(username)) {
            String message = "Your specified username is too long or contains illegal characters.";
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
            gotError = true;
        }else if (userFacade.findByUsername(username) != null){
            String message = "Username already exists.";
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
            gotError = true;
        }
        
        //Check if displayName is within length & if its taken or not
        if(!userValidation.validateDisplayname(displayName)) {
            String message = "Your specified display name is too long or contains illegal characters.";
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
            gotError = true;
        }else if (userFacade.findByDisplayName(displayName) != null) {
            String message = "Display name already exists.";
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
            gotError = true;
        }
        
        //Check if email is valid and if its taken
        if(!userValidation.validateEmail(email)) {
            String message = "Your specified email is not a valid email address.";
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
            gotError = true;
        }else if (userFacade.findByEmail(email) != null) {
            String message = "An account with specified email already exists.";
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
            gotError = true;
        }
        
        //Check if password is strong enough
        if(!userValidation.validatePassword(password)) {
            String message = "Your password needs to be longer than 4 characters.";
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
            gotError = true;
        }
        
        //Build user using local thread variables.
        buildUser(username, password, displayName, email);
        if (!gotError) {
            register.preformRegistration(user);
        }
    }
    
    /**
     * Builds the local user object before performing the registration, 
     * uses thread local variables to protect from multiple form requests.
     * 
     * @param usr - username to use when building the user object.
     * @param pass - password to use when building the user object.
     * @param displ - display name to use when building the user object.
     * @param mail - email address to use when building the user object.
     */
    private void buildUser(String usr, String pass, String displ, String mail) {
        if (user == null) {
            user = new UserEntity();
        }
        
        user.setUsername(usr);
        user.setDisplayname(displ);
        user.setEmail(mail);
        user.setPassword(pass);
    }
    
    /**
     * 
     * @param user - set the user object.
     */
    public void setUser(UserEntity user){
        this.user = user;
    }
    
    /**
     * 
     * @return the user object.
     */
    public UserEntity getUser(){
        return user;
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
     * @return the display name of the user.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 
     * @param displayname - the display name of the user.
     */
    public void setDisplayName(String displayname) {
        this.displayName = displayname;
    }
    
    /**
     * 
     * @return the email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email - the email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
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
