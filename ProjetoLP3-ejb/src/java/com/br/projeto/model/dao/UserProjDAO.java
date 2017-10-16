/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projeto.model.dao;

import com.br.projeto.model.entities.Userproj;
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
public class UserProjDAO implements GenericDAO<Userproj> {

    @PersistenceContext(unitName = "ProjetoPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public void persist(Userproj e) {
        em.persist(e);
    }

    @Override
    public List<Userproj> find() {
        return em.createNamedQuery("Userproj.findAll", Userproj.class).getResultList();
    }

    @Override
    public Userproj findById(long id) {

        return em.find(Userproj.class, id);
    }

    @Override
    public void update(Userproj e) {
        em.merge(e);
    }

    @Override
    public void remove(Userproj e) {
        em.remove(em.merge(e));
    }

    public Userproj findByUserName(String userName) {

        List<Userproj> usuarioproj = find();

        for (Userproj up : usuarioproj) {
            if (up.getUsername().equals(userName)) {
                return up;
            }
        }
        return null;
    }
}