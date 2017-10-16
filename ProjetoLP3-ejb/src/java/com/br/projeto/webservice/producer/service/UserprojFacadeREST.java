/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projeto.webservice.producer.service;

import com.br.projeto.model.entities.Userproj;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Vitória
 */
@Stateless
@Path("userproj")
public class UserprojFacadeREST extends AbstractFacade<Userproj> {
    @PersistenceContext(unitName = "ProjetoPU")
    private EntityManager em;

    public UserprojFacadeREST() {
        super(Userproj.class);
    }

    //http://localhost:8080/ProjetoLP3-war/webresources/userproj/username/vitinho10
    @GET
    @Path("username/{text}")
    @Produces ({"application/xml", "application/json"})
    public List<Userproj> getByUsername(@PathParam("text") String text){
        List<Userproj> resposta = new ArrayList<>();
        List<Userproj> lista = super.findAll();
        for (Userproj userproj: lista){
            if (userproj.getUsername().equals(text))
                resposta.add(userproj);
        }
        return resposta;
    }
    
    //http://localhost:8080/ProjetoLP3-war/webresources/userproj/1
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Userproj find(@PathParam("id") Long id) {
        return super.find(id);
    }

    //http://localhost:8080/ProjetoLP3-war/webresources/userproj
    @GET
    @Override
    @Produces({ "application/xml", "application/json"})
    public List<Userproj> findAll() {
        return super.findAll();
    }

    //http://localhost:8080/ProjetoLP3-war/webresources/userproj/1/3
    @GET
    @Path("{from}/{to}")
    @Produces({ "application/xml", "application/json"})
    public List<Userproj> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    //http://localhost:8080/ProjetoLP3-war/webresources/userproj/count
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
