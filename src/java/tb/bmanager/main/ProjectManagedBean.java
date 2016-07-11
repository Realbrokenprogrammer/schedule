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
package tb.bmanager.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import tb.bmanager.entity.project.ProjectEntity;
import tb.bmanager.entitymanager.ProjectEntityFacade;
import tb.bmanager.entitymanager.UserEntityFacade;

/**
 *
 * @author oskarmendel
 */
@Named(value = "projectManagedBean")
@SessionScoped
public class ProjectManagedBean implements Serializable{
    
    @EJB
    private ProjectEntityFacade projectFacade;
    
    @EJB
    private UserEntityFacade userFacade;
    
    private String publicity;
    
    /**
     * 
     */
    public ProjectManagedBean() {
        
    }
    
    /**
     * Uses the ProjectEntityFacade to return all project records.
     * @return 
     */
    public List<ProjectEntity> getAll(){
        System.out.println("In ProjectBean");
        return projectFacade.findAll();
    }
    
    /**
     * 
     * @param projectid
     * @return 
     */
    public List<ProjectEntity> getAllProjectUsers(int projectid) {
        System.out.println("Getting all the projects users from projectid: " + projectid);
        /*ProjectEntity newProject = new ProjectEntity();
        newProject.setName("TestProj");
        newProject.setOwnerid(userid);
        
        List userList = new ArrayList();
        userList.add(userFacade.find(userid));
         
        newProject.setUsers(userList);
        newProject.setCreationdate(new Date());
        newProject.setIsPublic(Boolean.TRUE);
        
        projectFacade.create(newProject);*/
        
        return projectFacade.findAll();
    }
    
    /**
     * 
     * @param publicity - the publicity of the project.
     */
    public void setPublicity(String publicity) {
        this.publicity = publicity;
    }
    
    /**
     * 
     * @return the publicity of the project.
     */
    public String getPublicity(){
        return this.publicity;
    }
}
