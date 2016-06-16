/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb.bmanager.main;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import tb.bmanager.auth.RegisterActionBeanLocal;
import tb.bmanager.entity.UserEntity;
import tb.bmanager.entitymanager.UserEntityFacade;

/**
 * Controller for the register view.
 * 
 * @author oskarmendel
 * @version 0.00.00
 * %name RegisterManagedBean.java
 * %date 15:34:37 PM, Jun 15, 2016
 */
@Named(value = "registerManagedBean")
@RequestScoped
public class RegisterManagedBean {

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
        //Check username is within length & if its taken or not
        if(username.length() > 255) {
            String message = "Your specified username is too long.";
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
        }else if (userFacade.findByUsername(username) != null){
            String message = "Username already exists.";
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
        }
        
        //Check if displayName is within length & if its taken or not
        if (userFacade.findByDisplayName(displayName) != null) {
            String message = "Display name already exists.";
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
        }
        
        //Check if email is valid and if its taken
        if (userFacade.findByEmail(email) != null) {
            String message = "An account with specified email already exists.";
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
        }
        
        //Check if password is strong enough
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
