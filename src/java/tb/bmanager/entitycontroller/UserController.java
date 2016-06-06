/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb.bmanager.entitycontroller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import tb.bmanager.entity.UserEntity;
import tb.bmanager.entitymanager.UserEntityFacade;

/**
 *
 * @author oskarmendel
 */

@Named(value="userController")
@SessionScoped
public class UserController implements Serializable {
    
    @EJB
    UserEntityFacade userFacade;
    
    public List<UserEntity> getAll(){
        return userFacade.findAll();
    }
    
    public int count() {
        return userFacade.count();
    }
    
    public String delete(UserEntity u) {
        userFacade.remove(u);
        return null; //Error logging ?
    }
    
    public String add() {
        UserEntity u = new UserEntity();
        //u.setDisplayname(userBean.getDisplayName());
        //u.setUsername(userBean.getUsername());
        //u.setPassword(userBean.getPassword());
        
        userFacade.create(u);
        
        return "index";//Url ?
    }
}
