/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencedemo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Akulov
 */
@Entity
@Table(name = "CITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "City.findAll", query = "SELECT c FROM City c"),
    @NamedQuery(name = "City.findById", query = "SELECT c FROM City c WHERE c.id = :id"),
    @NamedQuery(name = "City.findByCountry", query = "SELECT c FROM City c WHERE c.country = :country"),
    @NamedQuery(name = "City.findByMap", query = "SELECT c FROM City c WHERE c.map = :map"),
    @NamedQuery(name = "City.findByName", query = "SELECT c FROM City c WHERE c.name = :name"),
    @NamedQuery(name = "City.findByState", query = "SELECT c FROM City c WHERE c.state = :state")})
public class City implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "COUNTRY")
    private String country;
    @Basic(optional = false)
    @Column(name = "MAP")
    private String map;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "STATE")
    private String state;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private Collection<Hotel> hotelCollection;

    public City() {
    }

    public City(BigDecimal id) {
        this.id = id;
    }

    public City(BigDecimal id, String country, String map, String name, String state) {
        this.id = id;
        this.country = country;
        this.map = map;
        this.name = name;
        this.state = state;
    }

    public City(BigDecimal id, String country, String map, String name, String state, Collection<Hotel> hotelCol) {
        this.id = id;
        this.country = country;
        this.map = map;
        this.name = name;
        this.state = state;
        this.hotelCollection = hotelCol;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @XmlTransient
    public Collection<Hotel> getHotelCollection() {
        return hotelCollection;
    }

    public void setHotelCollection(List<Hotel> hotelCollection) {
        this.hotelCollection = hotelCollection;
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
        if (!(object instanceof City)) {
            return false;
        }
        City other = (City) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencedemo.City[ id=" + id + " ]"+"[name="+ name+"]";
    }

    void setId(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
