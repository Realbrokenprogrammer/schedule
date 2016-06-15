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
 *
 * @author oskarmendel
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
     * Creates a new instance of RegisterManagedBean
     */
    public RegisterManagedBean() {
    }
    
    public void init() {

        user = new UserEntity();

        if (user == null) {
            String message = "Error couldn't initialize user.";
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }
    
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
    
    public void setUser(UserEntity user){
        this.user = user;
    }
    
    public UserEntity getUser(){
        return user;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayname) {
        this.displayName = displayname;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
