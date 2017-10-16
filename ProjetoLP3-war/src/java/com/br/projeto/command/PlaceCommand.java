/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projeto.command;

import com.br.projeto.model.dao.DirectoryDAO;
import com.br.projeto.model.dao.PlaceDAO;
import com.br.projeto.model.dao.UserProjDAO;
import com.br.projeto.model.entities.Directory;
import com.br.projeto.model.entities.Place;
import com.br.projeto.model.entities.Userproj;
import com.br.projeto.webservice.consumer.PlaceJSON;
import com.br.projeto.webservice.consumer.PlaceManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vitória
 */
public class PlaceCommand implements Command {

    PlaceManager placeManager = lookupPlaceManagerBean1();

    DirectoryDAO directoryDAO = lookupDirectoryDAOBean();
    UserProjDAO userProjDAO = lookupUserProjDAOBean();

    DirectoryCommand dc = new DirectoryCommand();

    PlaceDAO placeDAO = lookupPlaceDAOBean();

    private String returnPage = "/placeProfile.jsp";
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
        String action = request.getParameter("action");

        switch (action) {
            case "searchPlace":
                String textsearch = request.getParameter("textsearch");
                List<PlaceJSON> places = placeManager.textSearchPlaces(textsearch);

                request.getSession().setAttribute("placesCollection", places);
                returnPage = "/search.jsp";
                break;

            case "openPlace":
                String idAPI = request.getParameter("idPlaceAPI");
                PlaceJSON place = placeManager.openPlaceById(idAPI);
                Userproj ups = (Userproj) request.getSession().getAttribute("usuarioSessao");

                List<Directory> directorys = dc.refreshDirectories(ups);

                request.getSession().setAttribute("listaD", directorys);
                request.getSession().setAttribute("place", place);

                refreshPopularity(idAPI);
                break;

            case "insertPlaceUser":
                Long idD = Long.parseLong(request.getParameter("idDiretorio"));
                if (idD == -1) {
                    request.getSession().setAttribute("errormsg", "Nenhum diretório foi selecionado.");
                } else {

                    String idAPI2 = request.getParameter("idAPI");
                    String nameAPI = request.getParameter("nameAPI");
                    nameAPI = convertToUTF8(nameAPI);

                    Userproj usuarioSessao = (Userproj) request.getSession().getAttribute("usuarioSessao");

                    List<Directory> list = dc.refreshDirectories(usuarioSessao);
                    Place place2 = placeDAO.findByIdAPI(idAPI2);

                    if (place2 == null) {

                        Place place3 = new Place();

                        place3.setIdApi(idAPI2);
                        place3.setNameApi(nameAPI);
                        place3.setDirectoryList(list);
                        place3.setPopularity(1);

                        //placeDAO.persist(place3);
                        for (Directory d : list) {
                            if (d.getIdDirectory().equals(idD)) {
                                List<Place> lugares = d.getPlaceList();
                                lugares.add(place3);
                                d.setPlaceList(lugares);
                                directoryDAO.update(d);
                                request.getSession().setAttribute("successmsg", "O local foi adicionado ao seu diretório.");
                                refreshPopularity(idAPI2);
                                break;
                            }
                        }

                    } else {
                        //altera popularidade do placeBD e persiste ele na tabela Place_directory

                        place2.setPopularity(place2.getPopularity() + 1);
                        placeDAO.update(place2);

                        for (Directory d : list) {
                            if (d.getIdDirectory().equals(idD)) {
                                List<Place> lugares = d.getPlaceList();
                                boolean achou = false;
                                for (Place lugar : lugares) {
                                    if (lugar.getIdApi().equals(idAPI2)) {
                                        request.getSession().setAttribute("errormsg", "Este diretório já contém esse lugar. Escolha outro diretório.");
                                        achou = true;
                                        break;
                                    }
                                }
                                if (!achou) {
                                    lugares.add(place2);
                                    d.setPlaceList(lugares);
                                    directoryDAO.update(d);
                                    request.getSession().setAttribute("successmsg", "O local foi adicionado ao seu diretório.");
                                    refreshPopularity(idAPI2);
                                    break;
                                }
                            }
                        }

                    }
                }
//               
                break;

            case "deletePlaceUser":
                Long idPlace = Long.parseLong(request.getParameter("idLugar"));

                if (idPlace == -1) {
                    request.getSession().setAttribute("errormsg", "Nenhum lugar foi selecionado.");
                } else {

                    Long idDiretorio = Long.parseLong(request.getParameter("idDiretorio"));
                    Directory d1 = directoryDAO.findById(idDiretorio);

                    List<Place> listaLugar = d1.getPlaceList();
                    for (Place lugar : listaLugar) {
                        if (lugar.getIdPlace().equals(idPlace)) {
                            listaLugar.remove(lugar);
                            d1.setPlaceList(listaLugar);
                            directoryDAO.update(d1);

                            Place p1 = placeDAO.findById(idPlace);
                            p1.setPopularity(p1.getPopularity() - 1);
                            placeDAO.update(p1);

                            request.getSession().setAttribute("successmsg", "Lugar excluído com sucesso.");
                            break;
                        }
                    }

                    Userproj ups2 = (Userproj) request.getSession().getAttribute("usuarioSessao");
                    //                
                    request.getSession().setAttribute("diretorio", d1);
                    request.getSession().setAttribute("listaLugar", listaLugar);
                }
                returnPage = "/moreInfoDirectory.jsp";

                break;

        }

    }

    @Override
    public String getReturnPage() {
        return returnPage;
    }

    private PlaceDAO lookupPlaceDAOBean() {
        try {
            Context c = new InitialContext();
            return (PlaceDAO) c.lookup("java:global/ProjetoLP3/ProjetoLP3-ejb/PlaceDAO!com.br.projeto.model.dao.PlaceDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private PlaceManager lookupPlaceManagerBean() {
        try {
            Context c = new InitialContext();
            return (PlaceManager) c.lookup("java:global/ProjetoLP3/ProjetoLP3-ejb/PlaceManager!com.br.projeto.webservice.PlaceManager");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private UserProjDAO lookupUserProjDAOBean() {
        try {
            Context c = new InitialContext();
            return (UserProjDAO) c.lookup("java:global/ProjetoLP3/ProjetoLP3-ejb/UserProjDAO!com.br.projeto.model.dao.UserProjDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private DirectoryDAO lookupDirectoryDAOBean() {
        try {
            Context c = new InitialContext();
            return (DirectoryDAO) c.lookup("java:global/ProjetoLP3/ProjetoLP3-ejb/DirectoryDAO!com.br.projeto.model.dao.DirectoryDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private void refreshPopularity(String idAPI) {
        Place placeBD = placeDAO.findByIdAPI(idAPI);
        if (placeBD == null) {
            request.getSession().setAttribute("popularity", 0);
        } else {
            request.getSession().setAttribute("popularity", placeBD.getPopularity());
        }
    }

    private PlaceManager lookupPlaceManagerBean1() {
        try {
            Context c = new InitialContext();
            return (PlaceManager) c.lookup("java:global/ProjetoLP3/ProjetoLP3-ejb/PlaceManager!com.br.projeto.webservice.consumer.PlaceManager");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private static String convertToUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes(), "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }

}
