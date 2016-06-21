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
package tb.bmanager.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author oskarmendel
 */
@Entity
@Table(name = "LOGINATTEMPTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoginAttemptsEntity.findAll", query = "SELECT l FROM LoginAttemptsEntity l"),
    @NamedQuery(name = "LoginAttemptsEntity.findById", query = "SELECT l FROM LoginAttemptsEntity l WHERE l.id = :id"),
    @NamedQuery(name = "LoginAttemptsEntity.findByAddress", query = "SELECT l FROM LoginAttemptsEntity l WHERE l.address = :address"),
    @NamedQuery(name = "LoginAttemptsEntity.findByDatetime", query = "SELECT l FROM LoginAttemptsEntity l WHERE l.datetime = :datetime")})
public class LoginAttemptsEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datetime")
    @Temporal(TemporalType.DATE)
    private Date datetime;

    public LoginAttemptsEntity() {
    }

    public LoginAttemptsEntity(Integer id) {
        this.id = id;
    }

    public LoginAttemptsEntity(Integer id, String address, Date datetime) {
        this.id = id;
        this.address = address;
        this.datetime = datetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
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
        if (!(object instanceof LoginAttemptsEntity)) {
            return false;
        }
        LoginAttemptsEntity other = (LoginAttemptsEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tb.bmanager.entity.LoginAttemptsEntity[ id=" + id + " ]";
    }
    
}
