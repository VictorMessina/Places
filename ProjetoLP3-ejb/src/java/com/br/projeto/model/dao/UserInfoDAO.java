/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projeto.model.dao;

import com.br.projeto.model.entities.Userinfo;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author 31449530
 */
@Stateless
@LocalBean
public class UserInfoDAO implements GenericDAO<Userinfo>{

    @PersistenceContext(unitName = "ProjetoPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;
    
    @Override
    public void persist(Userinfo e) {
        em.persist(e);
    }

    @Override
    public List<Userinfo> find() {
       return em.createNamedQuery("Userinfo.findAll", Userinfo.class).getResultList();
    }

    @Override
    public Userinfo findById(long id) {
        return em.find(Userinfo.class, id);
    }

    @Override
    public void update(Userinfo e) {
        em.merge(e);
    }

    @Override
    public void remove(Userinfo e) {
        em.remove(em.merge(e));
    }
    
}