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

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import tb.bmanager.entity.UserEntity;
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
    
    private int projectId;
    private String projectName;
    private String description;
    private String publicity;
    private ProjectEntity project;
    
    /**
     * 
     */
    public ProjectManagedBean() {
        
    }
    
    public void init() {
        System.out.println("Called for ");

        project = projectFacade.find(projectId);

        if (project == null) {
            Messages.addGlobalError("Bad request, unknown user.");
        }
    }
    
    public void init(int projectid) {
        System.out.println("Called for id: " + projectid);
        this.project = projectFacade.find(projectId);

        if (this.project == null) {
            Messages.addGlobalError("Bad request, unknown user.");
        }
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
     * @param ownerid - the id of the user that sent the request.
     */
    public void createProject(int ownerid) throws IOException{
        //Manually check if form submissor is same as session user.
        if (ownerid == ((UserEntity)Faces.getSessionAttribute("USER")).getId()) {
            this.project = new ProjectEntity();
            this.project.setName(this.projectName);
            this.project.setDescription(this.description);
            this.project.setOwnerid(ownerid);
            this.project.setIsPublic("public".equals(this.publicity));
            
            //Add owner to the members many to many table.
            List userList = new ArrayList();
            userList.add(userFacade.find(ownerid));
            this.project.setUsers(userList);
            
            //Newly created project only has one follower and member.
            this.project.setMemberAmount(1);
            this.project.setFollowers(1);
            this.project.setCreationdate(new Date());
            
            if (this.project != null) {
                //Create the project and store it in the database.
                projectFacade.create(this.project);
            }
        }
    }
    
    /**
     * 
     * @return the project id of the project object.
     */
     public int getProjectId() {
        return this.projectId;
    }

     /**
      * 
      * @param projectId - the project id of the project object.
      */
    public void setProjectId(int projectId) {
        System.out.println("Attempt to set projectId to: " + projectId);
        this.projectId = projectId;
    }
    
    /**
     * 
     * @param projectName - the projectName of the project.
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    /**
     * 
     * @return the projectName of the project.
     */
    public String getProjectName(){
        return this.projectName;
    }
    
    /**
     * 
     * @param description - the description of the project.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * 
     * @return the projectName of the project.
     */
    public String getDescription(){
        return this.description;
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
    
    /**
     * 
     * @param project - the project.
     */
    public void setProject(ProjectEntity project) {
        this.project = project;
    }
    
    /**
     * 
     * @return the project.
     */
    public ProjectEntity getProject(){
        return this.project;
    }
}
