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

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tb.bmanager.entity.project.ProjectEntity;

/**
 * ProjectsEntityFacade extends AbstractFacade and acts as an entity manager for 
 * the Projects table and can be used to perform queries to the table.
 * 
 * @author oskarmendel
 * @version 0.00.00
 * %name ProjectEntityFacade.java
 * %date 14:16:26 PM, Jul 5, 2016
 */
@Stateless
public class ProjectEntityFacade extends AbstractFacade<ProjectEntity> {

    @PersistenceContext(unitName = "BManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjectEntityFacade() {
        super(ProjectEntity.class);
    }
    
}
