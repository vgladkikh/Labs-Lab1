/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencedemo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Akulov
 */
@Entity
@Table(name = "ATTRACTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attractions.findAll", query = "SELECT a FROM Attractions a"),
    @NamedQuery(name = "Attractions.findById", query = "SELECT a FROM Attractions a WHERE a.id = :id"),
    @NamedQuery(name = "Attractions.findByDescription", query = "SELECT a FROM Attractions a WHERE a.description = :description"),
    @NamedQuery(name = "Attractions.findByName", query = "SELECT a FROM Attractions a WHERE a.name = :name")})
public class Attractions implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;

    public Attractions() {
    }

    public Attractions(BigDecimal id) {
        this.id = id;
    }

    public Attractions(BigDecimal id, String description, String name) {
        this.id = id;
        this.description = description;
        this.name = name;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof Attractions)) {
            return false;
        }
        Attractions other = (Attractions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencedemo.Attractions[ id=" + id + " ]";
    }
    
}
