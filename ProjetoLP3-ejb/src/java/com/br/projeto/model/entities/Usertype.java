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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 31402836
 */
@Entity
@Table(name = "USERTYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usertype.findAll", query = "SELECT u FROM Usertype u"),
    @NamedQuery(name = "Usertype.findByIdUsertype", query = "SELECT u FROM Usertype u WHERE u.idUsertype = :idUsertype"),
    @NamedQuery(name = "Usertype.findByTitle", query = "SELECT u FROM Usertype u WHERE u.title = :title")})
public class Usertype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USERTYPE")
    private Long idUsertype;
    @Size(max = 20)
    @Column(name = "TITLE")
    private String title;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsertype")
    private List<Userproj> userprojList;

    public Usertype() {
    }

    public Usertype(Long idUsertype) {
        this.idUsertype = idUsertype;
    }

    public Long getIdUsertype() {
        return idUsertype;
    }

    public void setIdUsertype(Long idUsertype) {
        this.idUsertype = idUsertype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlTransient
    public List<Userproj> getUserprojList() {
        return userprojList;
    }

    public void setUserprojList(List<Userproj> userprojList) {
        this.userprojList = userprojList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsertype != null ? idUsertype.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usertype)) {
            return false;
        }
        Usertype other = (Usertype) object;
        if ((this.idUsertype == null && other.idUsertype != null) || (this.idUsertype != null && !this.idUsertype.equals(other.idUsertype))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.projeto.model.entities.Usertype[ idUsertype=" + idUsertype + " ]";
    }
    
}
