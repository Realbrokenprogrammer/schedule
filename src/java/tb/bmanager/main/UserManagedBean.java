/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb.bmanager.main;

import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import tb.bmanager.entity.UserEntity;
import tb.bmanager.entitymanager.UserEntityFacade;

/**
 *
 * @author oskarmendel
 */
@Named(value = "userManagedBean")
@RequestScoped
@ManagedBean
public class UserManagedBean {
    
    @EJB
    private UserEntityFacade userFacade;
    
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
    
    private int userId;
    
    /**
     * Creates a new instance of UserManagedBean
     */
    public UserManagedBean() {
    }
    
    public void init() {

        user = userFacade.find(userId);

        if (user == null) {
            String message = "Bad request. Unknown user.";
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }
    
    /**
     * Uses the UserEntityFacade to return all user records.
     * @return 
     */
    public List<UserEntity> getAll(){
        System.out.println("getall");
        return userFacade.findAll();
    }
    
    /**
     * Uses the UserEntityFacade to return the user with specified id.
     * @param id - id of target user.
     * @return the UserEntity of found user.
     */
    public UserEntity getById(int id){
        return userFacade.find(id);
    }
    
    /**
     * Uses the UserEntityFacade to return the amount user records.
     * @return 
     */
    public int count() {
        return userFacade.count();
    }
    
    /**
     * Uses the UserEntityFacade to delete specified user record.
     * 
     * @param u - Target user record to be deleted.
     * @return - null
     */
    public String delete(UserEntity u) {
        userFacade.remove(u);
        return null; //Error logging ?
    }
    
    /**
     * Uses the UserEntityFacade to insert a new  user record.
     * 
     * @return - An URL to be redirected to.
     */
    public String add() {
        UserEntity u = new UserEntity();
        //u.setDisplayname(userBean.getDisplayName());
        //u.setUsername(userBean.getUsername());
        //u.setPassword(userBean.getPassword());
        
        userFacade.create(u);
        
        return "index";//Url ?
    }
    
    public void setUser(UserEntity u) {
        this.user = u;
    }
    
    public UserEntity getUser(){
        return this.user;
    }
    
     public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
