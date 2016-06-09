/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb.bmanager.entitycontroller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import tb.bmanager.entity.UserEntity;
import tb.bmanager.entitymanager.UserEntityFacade;

/**
 *
 * @author oskarmendel
 */

@Named(value="userController")
@ApplicationScoped
public class UserController implements Serializable {
    
    @EJB
    UserEntityFacade userFacade;
    
    /**
     * Uses the UserEntityFacade to return all user records.
     */
    public List<UserEntity> getAll(){
        return userFacade.findAll();
    }
    
    public UserEntity getById(int id){
        return userFacade.find(id);
    }
    
    /**
     * Uses the UserEntityFacade to return the amount user records.
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
}
