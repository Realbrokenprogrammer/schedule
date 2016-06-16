/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb.bmanager.entitymanager;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import tb.bmanager.entity.UserEntity;

/**
 * UserEntityFacade extends AbstractFacade and acts as an entity manager for 
 * the users table and can be used to perform queries to the users table and get
 * the results returned as UserEntity objects.
 * 
 * @author oskarmendel
 * @version 0.00.00
 * %name UserEntityFacade.java
 * %date 15:32:45 PM, Jun 15, 2016
 * read:
 *  http://stackoverflow.com/questions/2002993/jpa-getsingleresult-or-null
 * ask:
 *  Are the findBy methods good?
 */
@Stateless
public class UserEntityFacade extends AbstractFacade<UserEntity> {

    @PersistenceContext(unitName = "BManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserEntityFacade() {
        super(UserEntity.class);
    }
    
    /**
     * Finds a record by searching for a username.
     * @param Username - target username to find in the users table.
     * @return UserEntity with data from the found user with specified username.
     * returns null if no user was found.
     */
    public UserEntity findByUsername(String Username) {
        Query q = em.createQuery("SELECT u FROM UserEntity u WHERE u.username = :username");
        q.setParameter("username", Username);
        List<UserEntity> users = q.getResultList();
        
        if (users.isEmpty() || users == null) {
            System.out.println("USER NOT FOUND");
            return null;
        } else {
            System.out.println("USER WAS FOUND" + users.get(0).getUsername());
            return users.get(0);
        }
    }
    
    /**
     * Finds a record by searching for a display name.
     * @param DisplayName - target display name to find in the users table.
     * @return UserEntity with data from the found user with specified username.
     * returns null if no user was found.
     */
    public UserEntity findByDisplayName(String DisplayName) {
        Query q = em.createQuery("SELECT u FROM UserEntity u WHERE u.displayname = :displayname");
        q.setParameter("displayname", DisplayName);
        List<UserEntity> users = q.getResultList();
        
        if (users.isEmpty() || users == null) {
            System.out.println("USER NOT FOUND");
            return null;
        } else {
            System.out.println("USER WAS FOUND" + users.get(0).getDisplayname());
            return users.get(0);
        }
    }
    
    /**
     * Finds a record by searching for a email.
     * @param Email - target email to find in the users table.
     * @return UserEntity with data from the found user with specified username.
     * returns null if no user was found.
     */
    public UserEntity findByEmail(String Email) {
        Query q = em.createQuery("SELECT u FROM UserEntity u WHERE u.email = :email");
        q.setParameter("email", Email);
        List<UserEntity> users = q.getResultList();
        
        if (users.isEmpty() || users == null) {
            System.out.println("USER NOT FOUND");
            return null;
        } else {
            System.out.println("USER WAS FOUND" + users.get(0).getEmail());
            return users.get(0);
        }
    }
}
