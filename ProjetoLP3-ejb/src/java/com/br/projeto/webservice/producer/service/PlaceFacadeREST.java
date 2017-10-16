/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projeto.webservice.producer.service;

import com.br.projeto.model.entities.Place;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Vitória
 */
@Stateless
@Path("place")
public class PlaceFacadeREST extends AbstractFacade<Place> {
    @PersistenceContext(unitName = "ProjetoPU")
    private EntityManager em;

    public PlaceFacadeREST() {
        super(Place.class);
    }
    
    ////////// ACHO QUE LOCALHOST É 8080 NO MACK, SE NAO FOR VERIFICAR E SUBSTITUIR NAS URLS

    //http://localhost:8080/ProjetoLP3-war/webresources/place/popularity/1
    @GET
    @Path("popularity/{number}")
    @Produces ({"application/xml"})
    public List<Place> getByPopularity(@PathParam("number") int number){
        List<Place> resposta = new ArrayList<>();
        List<Place> lista = super.findAll();
        for (Place place: lista){
            if (place.getPopularity()==number)
                resposta.add(place);
        }
        return resposta;
    }
    
    //http://localhost:8080/ProjetoLP3-war/webresources/place/name/spoleto
    @GET
    @Path("name/{text}")
    @Produces ({"application/xml", "application/json"})
    public List<Place> getByName(@PathParam("text") String text){
        List<Place> resposta = new ArrayList<>();
        List<Place> lista = super.findAll();
        for (Place place: lista){
            if (place.getNameApi().equalsIgnoreCase(text))
                resposta.add(place);
        }
        return resposta;
    }
    
    //http://localhost:8080/ProjetoLP3-war/webresources/place/2
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Place find(@PathParam("id") Long id) {
        return super.find(id);
    }

    //http://localhost:8080/ProjetoLP3-war/webresources/place
    @GET
    @Override
    @Produces({ "application/xml", "application/json"})
    public List<Place> findAll() {
        return super.findAll();
    }

    //http://localhost:8080/ProjetoLP3-war/webresources/place/1/3
    //RETORNA OS 3 PRIMEIROS SALVOS NO BANCO, NAO É O ID
    @GET
    @Path("{from}/{to}")
    @Produces({ "application/xml", "application/json"})
    public List<Place> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    //http://localhost:8080/ProjetoLP3-war/webresources/place/count
    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
