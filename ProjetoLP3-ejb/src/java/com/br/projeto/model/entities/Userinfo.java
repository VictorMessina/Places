/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projeto.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 31402836
 */
@Entity
@Table(name = "USERINFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userinfo.findAll", query = "SELECT u FROM Userinfo u"),
    @NamedQuery(name = "Userinfo.findByIdUserinfo", query = "SELECT u FROM Userinfo u WHERE u.idUserinfo = :idUserinfo"),
    @NamedQuery(name = "Userinfo.findByEmail", query = "SELECT u FROM Userinfo u WHERE u.email = :email"),
    @NamedQuery(name = "Userinfo.findByFullname", query = "SELECT u FROM Userinfo u WHERE u.fullname = :fullname"),
    @NamedQuery(name = "Userinfo.findByBirthday", query = "SELECT u FROM Userinfo u WHERE u.birthday = :birthday")})
public class Userinfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USERINFO")
    private Long idUserinfo;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 150)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 100)
    @Column(name = "FULLNAME")
    private String fullname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BIRTHDAY")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @JoinColumn(name = "ID_USERINFO", referencedColumnName = "ID_USER", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Userproj userproj;

    public Userinfo() {
    }

    public Userinfo(Long idUserinfo) {
        this.idUserinfo = idUserinfo;
    }

    public Userinfo(Long idUserinfo, Date birthday) {
        this.idUserinfo = idUserinfo;
        this.birthday = birthday;
    }

    public Long getIdUserinfo() {
        return idUserinfo;
    }

    public void setIdUserinfo(Long idUserinfo) {
        this.idUserinfo = idUserinfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Userproj getUserproj() {
        return userproj;
    }

    public void setUserproj(Userproj userproj) {
        this.userproj = userproj;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserinfo != null ? idUserinfo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userinfo)) {
            return false;
        }
        Userinfo other = (Userinfo) object;
        if ((this.idUserinfo == null && other.idUserinfo != null) || (this.idUserinfo != null && !this.idUserinfo.equals(other.idUserinfo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.projeto.model.entities.Userinfo[ idUserinfo=" + idUserinfo + " ]";
    }
    
}
