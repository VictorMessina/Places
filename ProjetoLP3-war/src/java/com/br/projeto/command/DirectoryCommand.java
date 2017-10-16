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
import java.util.ArrayList;
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
 * @author 31449530
 */
public class DirectoryCommand implements Command {

    PlaceDAO placeDAO = lookupPlaceDAOBean();

    UserProjDAO userProjDAO = lookupUserProjDAOBean();

    DirectoryDAO directoryDAO = lookupDirectoryDAOBean();

    private String returnPage = "/directories.jsp";
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

//                
        switch (action) {
            //carrega pagina de registro dos diretórios por parte do ADM com a lista de diretorios
            case "registerDirectory":

                List<Userproj> lup = userProjDAO.find();

                request.getSession().setAttribute("listaUsuarios", lup);

                returnPage = "/registerDirectory.jsp";

                break;
            //inserção do diretorio por parte do adm
            case "insert":

                Long id = Long.parseLong(request.getParameter("idUser"));
                if (id == -1) {
                    request.getSession().setAttribute("errormsg", "Nenhum usuário foi selecionado.");
                } else {

                    Directory d = new Directory();
                    Userproj up;

                    up = userProjDAO.findById(id);

                    String nomediretorio = request.getParameter("name");

                    d.setNameDirectory(nomediretorio);

                    d.setFkUserproj(up);

                    directoryDAO.persist(d);

                    request.getSession().setAttribute("successmsg", "Diretório inserido com sucesso.");
                }
                returnPage = "/admFunctions.jsp";

                break;

            case "insertDirectoryUser":
                Directory du = new Directory();

                Userproj upi = (Userproj) request.getSession().getAttribute("usuarioSessao");

                String nomediretorioi = request.getParameter("name");

                du.setNameDirectory(nomediretorioi);

                du.setFkUserproj(upi);

                du.setPlaceList(new ArrayList<Place>());

                directoryDAO.persist(du);

                List<Directory> novaLista = refreshDirectories(upi);

                request.getSession().setAttribute("listaDiretorios", novaLista);

                request.getSession().setAttribute("successmsg", "Diretório inserido com sucesso");
                break;

            case "openDirectories":
                Userproj ups = (Userproj) request.getSession().getAttribute("usuarioSessao");

                List<Directory> novaLista2 = refreshDirectories(ups);

                request.getSession().setAttribute("listaDiretorios", novaLista2);

                break;

            case "updateDirectoryUser":

                Long id2 = Long.parseLong(request.getParameter("idDiretorio"));
                if (id2 == -1) {
                    request.getSession().setAttribute("errormsg", "Nenhum diretorio foi selecionado.");
                } else {

                    String name = request.getParameter("name");
                    Userproj upi2 = (Userproj) request.getSession().getAttribute("usuarioSessao");

                    Directory d2 = directoryDAO.findById(id2);

                    d2.setNameDirectory(name);

                    directoryDAO.update(d2);

                    List<Directory> novaLista3 = refreshDirectories(upi2);

                    request.getSession().setAttribute("listaDiretorios", novaLista3);

                    request.getSession().setAttribute("successmsg", "Diretório atualizado com sucesso");
                }
                break;

            case "deleteDirectoryUser":
                Long id1 = Long.parseLong(request.getParameter("idDiretorio"));
                if (id1 == -1) {
                    request.getSession().setAttribute("errormsg", "Nenhum diretório foi selecionado.");
                } else {

                    Directory d1 = directoryDAO.findById(id1);
                    directoryDAO.remove(d1);

                    Userproj ups2 = (Userproj) request.getSession().getAttribute("usuarioSessao");
                    List<Directory> lista = refreshDirectories(ups2);

                    request.getSession().setAttribute("listaDiretorios", lista);
                    request.getSession().setAttribute("successmsg", "Diretório excluído com sucesso.");
                }
                break;

            case "moreInfoDirectory":

                Long id3 = Long.parseLong(request.getParameter("idDirectory"));

                Directory d3 = directoryDAO.findById(id3);
                List<Place> listaLugar = d3.getPlaceList();

//                
                request.getSession().setAttribute("diretorio", d3);
                request.getSession().setAttribute("listaLugar", listaLugar);

                returnPage = "/moreInfoDirectory.jsp";
                break;

        }
    }

    @Override
    public String getReturnPage() {
        return returnPage;
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

    private UserProjDAO lookupUserProjDAOBean() {
        try {
            Context c = new InitialContext();
            return (UserProjDAO) c.lookup("java:global/ProjetoLP3/ProjetoLP3-ejb/UserProjDAO!com.br.projeto.model.dao.UserProjDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public List<Directory> refreshDirectories(Userproj up) {
        List<Directory> lista2 = directoryDAO.find();
        List<Directory> novaLista2 = new ArrayList<>();

        for (Directory dir : lista2) {
            if (dir.getFkUserproj().getIdUser().equals(up.getIdUser())) {
                novaLista2.add(dir);
            }
        }
        return novaLista2;
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

}
