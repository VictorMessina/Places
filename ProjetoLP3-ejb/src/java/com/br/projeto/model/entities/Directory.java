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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "DIRECTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Directory.findAll", query = "SELECT d FROM Directory d"),
    @NamedQuery(name = "Directory.findByIdDirectory", query = "SELECT d FROM Directory d WHERE d.idDirectory = :idDirectory"),
    @NamedQuery(name = "Directory.findByNameDirectory", query = "SELECT d FROM Directory d WHERE d.nameDirectory = :nameDirectory")})
public class Directory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DIRECTORY")
    private Long idDirectory;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 140)
    @Column(name = "NAME_DIRECTORY")
    private String nameDirectory;
    @JoinTable(name = "DIRECTORY_PLACE", joinColumns = {
        @JoinColumn(name = "FK_IDDIRECTORY", referencedColumnName = "ID_DIRECTORY")}, inverseJoinColumns = {
        @JoinColumn(name = "FK_IDPLACE", referencedColumnName = "ID_PLACE")})
    @ManyToMany
    private List<Place> placeList;
    @JoinColumn(name = "FK_USERPROJ", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private Userproj fkUserproj;

    public Directory() {
    }

    public Directory(Long idDirectory) {
        this.idDirectory = idDirectory;
    }

    public Directory(Long idDirectory, String nameDirectory) {
        this.idDirectory = idDirectory;
        this.nameDirectory = nameDirectory;
    }

    public Long getIdDirectory() {
        return idDirectory;
    }

    public void setIdDirectory(Long idDirectory) {
        this.idDirectory = idDirectory;
    }

    public String getNameDirectory() {
        return nameDirectory;
    }

    public void setNameDirectory(String nameDirectory) {
        this.nameDirectory = nameDirectory;
    }

    @XmlTransient
    public List<Place> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(List<Place> placeList) {
        this.placeList = placeList;
    }

    public Userproj getFkUserproj() {
        return fkUserproj;
    }

    public void setFkUserproj(Userproj fkUserproj) {
        this.fkUserproj = fkUserproj;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDirectory != null ? idDirectory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Directory)) {
            return false;
        }
        Directory other = (Directory) object;
        if ((this.idDirectory == null && other.idDirectory != null) || (this.idDirectory != null && !this.idDirectory.equals(other.idDirectory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.projeto.model.entities.Directory[ idDirectory=" + idDirectory + " ]";
    }
    
}
