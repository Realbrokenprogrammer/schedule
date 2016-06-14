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
 *
 * @author oskarmendel
 * read:
 *  http://stackoverflow.com/questions/2002993/jpa-getsingleresult-or-null
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
    
    public UserEntity findByUsername(String Username) {
        Query q = em.createQuery("SELECT u FROM UserEntity u WHERE u.username = :username");
        q.setParameter("username", Username);
        List<UserEntity> users = q.getResultList();
        
        if (users.isEmpty() || users == null) {
            System.out.println("USER IS NULL");
            return null;
        } else {
            System.out.println("USER WAS FOUND" + users.get(0).getUsername());
            return users.get(0);
        }
    }
}
