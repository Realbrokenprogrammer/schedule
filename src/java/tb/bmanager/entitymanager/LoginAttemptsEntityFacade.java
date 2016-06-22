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
package tb.bmanager.entitymanager;

import java.util.Calendar;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import tb.bmanager.entity.LoginAttemptsEntity;

/**
 * LoginAttemptsEntityFacade extends AbstractFacade and acts as an entity manager for 
 * the LoginAttempts table and can be used to perform queries to the table.
 * 
 * @author oskarmendel
 * @version 0.00.00
 * %name LoginAttemptsEntityFacade.java
 * %date 17:46:26 PM, Jun 21, 2016
 */
@Stateless
public class LoginAttemptsEntityFacade extends AbstractFacade<LoginAttemptsEntity> {

    @PersistenceContext(unitName = "BManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LoginAttemptsEntityFacade() {
        super(LoginAttemptsEntity.class);
    }
    
    /**
     * Gets the number of attempts to login from the specified IP address.
     * 
     * @param ipAddress - IP address to get amount of login attempts from.
     * @return amount of login attempts made form specified IP address.
     */
    public int getLoginAttempts(String ipAddress) {
        //Delete too old attempts from the database.
        deleteOldAttempts();
        
        //Get the number of failed login attemtps.
        Query q = em.createQuery("SELECT count(l) FROM LoginAttemptsEntity l WHERE l.address = :address");
        q.setParameter("address", ipAddress);
        long amount = (long) q.getSingleResult();
        
        return (int)amount;
    }
    
    /**
     * Queries the database to delete entries that are older than 10 minutes.
     */
    public void deleteOldAttempts() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) - 10);
        
        Query q = em.createQuery("DELETE FROM LoginAttemptsEntity l WHERE l.datetime < :datetime");
        q.setParameter("datetime", cal.getTime()).executeUpdate();
    }
}
