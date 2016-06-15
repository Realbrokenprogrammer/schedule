package tb.bmanager.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * UserEntity acts like a model for the user table in the database.
 * 
 * @author oskarmendel
 * @version 0.00.00
 * %name UserEntity.java
 * %date 15:27:26 PM, Jun 15, 2016
 */
@Entity
@Table(name = "USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserEntity.findAll", query = "SELECT u FROM UserEntity u"),
    @NamedQuery(name = "UserEntity.findById", query = "SELECT u FROM UserEntity u WHERE u.id = :id"),
    @NamedQuery(name = "UserEntity.findByUsername", query = "SELECT u FROM UserEntity u WHERE u.username = :username"),
    @NamedQuery(name = "UserEntity.findByPassword", query = "SELECT u FROM UserEntity u WHERE u.password = :password"),
    @NamedQuery(name = "UserEntity.findByDisplayname", query = "SELECT u FROM UserEntity u WHERE u.displayname = :displayname"),
    @NamedQuery(name = "UserEntity.findByWebsiteurl", query = "SELECT u FROM UserEntity u WHERE u.websiteurl = :websiteurl"),
    @NamedQuery(name = "UserEntity.findByEmail", query = "SELECT u FROM UserEntity u WHERE u.email = :email"),
    @NamedQuery(name = "UserEntity.findByFollowers", query = "SELECT u FROM UserEntity u WHERE u.followers = :followers"),
    @NamedQuery(name = "UserEntity.findByFollows", query = "SELECT u FROM UserEntity u WHERE u.follows = :follows"),
    @NamedQuery(name = "UserEntity.findByProjects", query = "SELECT u FROM UserEntity u WHERE u.projects = :projects"),
    @NamedQuery(name = "UserEntity.findByJoindate", query = "SELECT u FROM UserEntity u WHERE u.joindate = :joindate"),
    @NamedQuery(name = "UserEntity.findByProfileimg", query = "SELECT u FROM UserEntity u WHERE u.profileimg = :profileimg")})
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "displayname")
    private String displayname;
    @Size(max = 200)
    @Column(name = "websiteurl")
    private String websiteurl;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Lob
    @Size(max = 65535)
    @Column(name = "bio")
    private String bio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "followers")
    private int followers;
    @Basic(optional = false)
    @NotNull
    @Column(name = "follows")
    private int follows;
    @Basic(optional = false)
    @NotNull
    @Column(name = "projects")
    private int projects;
    @Column(name = "joindate")
    @Temporal(TemporalType.DATE)
    private Date joindate;
    @Size(max = 200)
    @Column(name = "profileimg")
    private String profileimg;

    public UserEntity() {
    }

    public UserEntity(Integer id) {
        this.id = id;
    }

    public UserEntity(Integer id, String username, String password, String displayname, int followers, int follows, int projects) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.displayname = displayname;
        this.followers = followers;
        this.follows = follows;
        this.projects = projects;
    }
    
    /**
     * 
     * @return users id.
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * 
     * @param id - unique id for the user.
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * 
     * @return users username. 
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username - username used for logging in to the application.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * 
     * @return users password.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * 
     * @param password - users password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * 
     * @return users display name.
     */
    public String getDisplayname() {
        return displayname;
    }
    
    /**
     * 
     * @param displayname - users display name used as a public name.
     */
    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    /**
     * 
     * @return users specified web site URL.
     */
    public String getWebsiteurl() {
        return websiteurl;
    }
    
    /**
     * 
     * @param websiteurl - target users web site URL.
     */
    public void setWebsiteurl(String websiteurl) {
        this.websiteurl = websiteurl;
    }
    
    /**
     * 
     * @return users specified email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email - users email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * 
     * @return users set biography.
     */
    public String getBio() {
        return bio;
    }

    /**
     * 
     * @param bio - user biography.
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * 
     * @return the amount of followers the user has.
     */
    public int getFollowers() {
        return followers;
    }

    /**
     * 
     * @param followers - users followers.
     */
    public void setFollowers(int followers) {
        this.followers = followers;
    }
    
    /**
     * 
     * @return the number of users the user follows.
     */
    public int getFollows() {
        return follows;
    }

    /**
     * 
     * @param follows - the amount of users the user follows.
     */
    public void setFollows(int follows) {
        this.follows = follows;
    }

    /**
     * 
     * @return the amount of projects the user is apart of.
     */
    public int getProjects() {
        return projects;
    }

    /**
     * 
     * @param projects - the amount of projects the user is a part of.
     */
    public void setProjects(int projects) {
        this.projects = projects;
    }

    /**
     * 
     * @return the date the user joined.
     */
    public Date getJoindate() {
        return joindate;
    }

    /**
     * 
     * @param joindate - the date the user joined.
     */
    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    /**
     * 
     * @return URL of target users profile image.
     */
    public String getProfileimg() {
        return profileimg;
    }

    /**
     * 
     * @param profileimg - URL of target users profile image.
     */
    public void setProfileimg(String profileimg) {
        this.profileimg = profileimg;
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
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tb.bmanager.entity.UserEntity[ id=" + id + " ]";
    }
    
}
