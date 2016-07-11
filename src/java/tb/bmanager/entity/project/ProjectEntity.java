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
package tb.bmanager.entity.project;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import tb.bmanager.entity.UserEntity;

/**
 * ProjectEntity acts like a model for the Projects table in the database.
 * 
 * @author oskarmendel
 * @version 0.00.00
 * %name ProjectEntity.java
 * %date 14:15:06 PM, Jul 5, 2016
 */
@Entity
@Table(name = "PROJECTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectEntity.findAll", query = "SELECT p FROM ProjectEntity p"),
    @NamedQuery(name = "ProjectEntity.findById", query = "SELECT p FROM ProjectEntity p WHERE p.id = :id"),
    @NamedQuery(name = "ProjectEntity.findByName", query = "SELECT p FROM ProjectEntity p WHERE p.name = :name"),
    @NamedQuery(name = "ProjectEntity.findByOwnerid", query = "SELECT p FROM ProjectEntity p WHERE p.ownerid = :ownerid"),
    @NamedQuery(name = "ProjectEntity.findByIsPublic", query = "SELECT p FROM ProjectEntity p WHERE p.isPublic = :isPublic"),
    @NamedQuery(name = "ProjectEntity.findByCreationdate", query = "SELECT p FROM ProjectEntity p WHERE p.creationdate = :creationdate"),
    @NamedQuery(name = "ProjectEntity.findByTags", query = "SELECT p FROM ProjectEntity p WHERE p.tags = :tags"),
    @NamedQuery(name = "ProjectEntity.findByMemberAmount", query = "SELECT p FROM ProjectEntity p WHERE p.memberAmount = :memberAmount"),
    @NamedQuery(name = "ProjectEntity.findByFollowers", query = "SELECT p FROM ProjectEntity p WHERE p.followers = :followers"),
    @NamedQuery(name = "ProjectEntity.findByWebsiteurl", query = "SELECT p FROM ProjectEntity p WHERE p.websiteurl = :websiteurl")})
public class ProjectEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "ownerid")
    private Integer ownerid;
    @Column(name = "isPublic")
    private Boolean isPublic;
    @Column(name = "creationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdate;
    @Size(max = 255)
    @Column(name = "tags")
    private String tags;
    @Column(name = "memberAmount")
    private Integer memberAmount;
    @Column(name = "followers")
    private Integer followers;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Size(max = 255)
    @Column(name = "websiteurl")
    private String websiteurl;
    @ManyToMany()
    @JoinTable(name="USER_PROJECTS", 
            joinColumns=
                    {@JoinColumn(name="projectid")}, 
            inverseJoinColumns=
                    {@JoinColumn(name="userid")})
    private List<UserEntity> users;

    public ProjectEntity() {
    }

    public ProjectEntity(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return project id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id - unique id for the project.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return project name.
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name - name of the project.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return user id of the project owner.
     */
    public Integer getOwnerid() {
        return ownerid;
    }

    /**
     * 
     * @param ownerid - user id of the owner of the project.
     */
    public void setOwnerid(Integer ownerid) {
        this.ownerid = ownerid;
    }

    /**
     * 
     * @return boolean depending if the project is public or not.
     */
    public Boolean getIsPublic() {
        return isPublic;
    }

    /**
     * 
     * @param isPublic - boolean if the project is public or not.
     */
    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    /**
     * 
     * @return date of project creation.
     */
    public Date getCreationdate() {
        return creationdate;
    }

    /**
     * 
     * @param creationdate - the timestamp of when the project was created.
     */
    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    /**
     * 
     * @return the tags of the project.
     */
    public String getTags() {
        return tags;
    }

    /**
     * 
     * @param tags - tags for the project.
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * 
     * @return amount of workers on this project. 
     */
    public Integer getMemberAmount() {
        return memberAmount;
    }

    /**
     * 
     * @param memberAmount - amount of workers for this project.
     */
    public void setMemberAmount(Integer memberAmount) {
        this.memberAmount = memberAmount;
    }

    /**
     * 
     * @return amount of followers for this project.
     */
    public Integer getFollowers() {
        return followers;
    }

    /**
     * 
     * @param followers - set follower amount.
     */
    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    /**
     * 
     * @return project description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description - project description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return project web site URL.
     */
    public String getWebsiteurl() {
        return websiteurl;
    }

    /**
     * 
     * @param websiteurl - project web site URL.
     */
    public void setWebsiteurl(String websiteurl) {
        this.websiteurl = websiteurl;
    }
    
    public List<UserEntity> getUsers() {
       return users;
    }
    
    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectEntity)) {
            return false;
        }
        ProjectEntity other = (ProjectEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tb.bmanager.entity.project.ProjectEntity[ id=" + id + " ]";
    }
    
}
