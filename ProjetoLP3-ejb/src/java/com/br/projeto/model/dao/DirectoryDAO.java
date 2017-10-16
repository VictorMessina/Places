/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projeto.model.dao;

import com.br.projeto.model.entities.Directory;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author 31320600
 */
@Stateless
@LocalBean
public class DirectoryDAO implements GenericDAO<Directory> {

    @PersistenceContext(unitName = "ProjetoPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public void persist(Directory e) {
        em.persist(e);
    }

    @Override
    public List<Directory> find() {
        return em.createNamedQuery("Directory.findAll",Directory.class).getResultList();
    }

    @Override
    public Directory findById(long id) {
        return em.find(Directory.class, id);
    }

    @Override
    public void update(Directory e) {
        em.merge(e);
    }
    
    @Override
    public void remove(Directory e) {
        em.remove(em.merge(e));
    }

}
