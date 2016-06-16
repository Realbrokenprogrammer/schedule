/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb.bmanager.entitymanager;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * AbstractFacade acts as a universal entity manger that can be extended to
 * perform standard queries for a specific table like find by id or find all.
 * 
 * @author oskarmendel
 * @version 0.00.00
 * %name AbstractFacade.java
 * %date 15:29:40 PM, Jun 15, 2016
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    /**
     * Creates a new record.
     * @param entity - entity to push to the target table.
     */
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    /**
     * Edits an existing record.
     * @param entity - target entity to edit in the table.
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    /**
     * Removes a record.
     * @param entity - target entity to remove from the table.
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * Finds an record by its primary key.
     * @param id - primary key of target row to find.
     * @return the object from target table with specified primary key.
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * Finds all records.
     * @return entire list of records of target table.
     */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Finds records within a range.
     * @param range - amount of records.
     * @return return the range of records.
     */
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    /**
     * Gives the amount of records.
     * @return amount of records found in target table.
     */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
