/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projeto.model.dao;

import com.br.projeto.model.entities.Usertype;
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
public class UserTypeDAO implements GenericDAO<Usertype>{

    @PersistenceContext(unitName = "ProjetoPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;
    
    @Override
    public void persist(Usertype e) {
        em.persist(e);
    }

    @Override
    public List<Usertype> find() {
        return em.createNamedQuery("Usertype.findAll", Usertype.class).getResultList();
    }
    
    @Override
    public Usertype findById (long id){
        return em.find(Usertype.class, id);
    }
    
    @Override
    public void update(Usertype e) {
        em.merge(e);
    }
    
    @Override
    public void remove(Usertype e) {
        em.remove(em.merge(e));
    }
    
}