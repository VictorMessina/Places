/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projeto.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 31402836
 */
@Entity
@Table(name = "PLACE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Place.findAll", query = "SELECT p FROM Place p"),
    @NamedQuery(name = "Place.findByIdPlace", query = "SELECT p FROM Place p WHERE p.idPlace = :idPlace"),
    @NamedQuery(name = "Place.findByIdApi", query = "SELECT p FROM Place p WHERE p.idApi = :idApi"),
    @NamedQuery(name = "Place.findByNameApi", query = "SELECT p FROM Place p WHERE p.nameApi = :nameApi"),
    @NamedQuery(name = "Place.findByPopularity", query = "SELECT p FROM Place p WHERE p.popularity = :popularity")})
public class Place implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PLACE")
    private Long idPlace;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 140)
    @Column(name = "ID_API")
    private String idApi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 140)
    @Column(name = "NAME_API")
    private String nameApi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POPULARITY")
    private int popularity;
    @ManyToMany(mappedBy = "placeList")
    private List<Directory> directoryList;

    public Place() {
    }

    public Place(Long idPlace) {
        this.idPlace = idPlace;
    }

    public Place(Long idPlace, String idApi, String nameApi, int popularity) {
        this.idPlace = idPlace;
        this.idApi = idApi;
        this.nameApi = nameApi;
        this.popularity = popularity;
    }

    public Long getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(Long idPlace) {
        this.idPlace = idPlace;
    }

    public String getIdApi() {
        return idApi;
    }

    public void setIdApi(String idApi) {
        this.idApi = idApi;
    }

    public String getNameApi() {
        return nameApi;
    }

    public void setNameApi(String nameApi) {
        this.nameApi = nameApi;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @XmlTransient
    public List<Directory> getDirectoryList() {
        return directoryList;
    }

    public void setDirectoryList(List<Directory> directoryList) {
        this.directoryList = directoryList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlace != null ? idPlace.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Place)) {
            return false;
        }
        Place other = (Place) object;
        if ((this.idPlace == null && other.idPlace != null) || (this.idPlace != null && !this.idPlace.equals(other.idPlace))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.projeto.model.entities.Place[ idPlace=" + idPlace + " ]";
    }
    
}
