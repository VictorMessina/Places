/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projeto.model.dao;

import com.br.projeto.model.entities.Place;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author 31320600
 */
@Stateless
@LocalBean

public class PlaceDAO implements GenericDAO<Place> {

    @PersistenceContext(unitName = "ProjetoPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public void persist(Place e) {
        em.persist(e);
    }

    @Override
    public List<Place> find() {
        return em.createNamedQuery("Place.findAll", Place.class).getResultList();
    }

    @Override
    public Place findById(long id) {
        return em.find(Place.class, id);
    }

    public Place findByIdAPI(String id) {
        try {
            return (Place) em.createNamedQuery("Place.findByIdApi").setParameter("idApi", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void update(Place e) {
        em.merge(e);
    }

    @Override
    public void remove(Place e) {
        em.remove(em.merge(e));
    }
}
