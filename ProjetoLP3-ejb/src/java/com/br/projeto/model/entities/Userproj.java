/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projeto.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "USERPROJ")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userproj.findAll", query = "SELECT u FROM Userproj u"),
    @NamedQuery(name = "Userproj.findByIdUser", query = "SELECT u FROM Userproj u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "Userproj.findByUsername", query = "SELECT u FROM Userproj u WHERE u.username = :username"),
    @NamedQuery(name = "Userproj.findByPassword", query = "SELECT u FROM Userproj u WHERE u.password = :password")})
public class Userproj implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USER")
    private Long idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "PASSWORD")
    private String password;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userproj")
    private Userinfo userinfo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUserproj")
    private List<Directory> directoryList;
    @JoinColumn(name = "FK_USERTYPE", referencedColumnName = "ID_USERTYPE")
    @ManyToOne(optional = false)
    private Usertype fkUsertype;

    public Userproj() {
    }

    public Userproj(Long idUser) {
        this.idUser = idUser;
    }

    public Userproj(Long idUser, String username, String password) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    @XmlTransient
    public List<Directory> getDirectoryList() {
        return directoryList;
    }

    public void setDirectoryList(List<Directory> directoryList) {
        this.directoryList = directoryList;
    }

    public Usertype getFkUsertype() {
        return fkUsertype;
    }

    public void setFkUsertype(Usertype fkUsertype) {
        this.fkUsertype = fkUsertype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userproj)) {
            return false;
        }
        Userproj other = (Userproj) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.projeto.model.entities.Userproj[ idUser=" + idUser + " ]";
    }
    
}
