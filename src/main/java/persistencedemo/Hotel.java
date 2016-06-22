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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Akulov
 */
@Entity
@Table(name = "HOTEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hotel.findAll", query = "SELECT h FROM Hotel h"),
    @NamedQuery(name = "Hotel.findById", query = "SELECT h FROM Hotel h WHERE h.id = :id"),
    @NamedQuery(name = "Hotel.findByAddress", query = "SELECT h FROM Hotel h WHERE h.address = :address"),
    @NamedQuery(name = "Hotel.findByName", query = "SELECT h FROM Hotel h WHERE h.name = :name"),
    @NamedQuery(name = "Hotel.findByZip", query = "SELECT h FROM Hotel h WHERE h.zip = :zip")})
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "ADDRESS")
    private String address;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "ZIP")
    private String zip;
    @JoinColumn(name = "CITY", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private City city;

    public Hotel() {
    }

    public Hotel(BigDecimal id) {
        this.id = id;
    }

    public Hotel(BigDecimal id, String address, String name, String zip) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.zip = zip;
    }

    public Hotel(BigDecimal id, String address, String name, String zip, City gorod) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.zip = zip;
        this.city = gorod;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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
        if (!(object instanceof Hotel)) {
            return false;
        }
        Hotel other = (Hotel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencedemo.Hotel[ id=" + id + " ]";
    }
    
}
