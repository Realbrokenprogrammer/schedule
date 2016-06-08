/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb.bmanager.main;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import tb.bmanager.entity.UserEntity;
import tb.bmanager.entitymanager.UserEntityFacade;

/**
 *
 * @author oskarmendel
 */
@Named(value = "userManagedBean")
@Dependent
@ViewScoped
@ManagedBean
public class UserManagedBean {
    
    @EJB
    UserEntityFacade userFacade;
    
    String username;
    String password;
    String displayname;
    String email;
    String bio;
    String websiteurl;
    int followers;
    int follows;
    int projects;
    UserEntity user;
    
    private Long userId;
    
    /**
     * Creates a new instance of UserManagedBean
     */
    public UserManagedBean() {
    }
    
    public void init() {
        if (userId == null) {
            String message = "Bad request. Please use a link from within the system.";
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            return;
        }

        user = userFacade.find(userId);

        if (user == null) {
            String message = "Bad request. Unknown user.";
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }
    
    public void setUser(UserEntity u) {
        this.user = u;
    }
    
    public UserEntity getUser(){
        return this.user;
    }
    
     public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
}
