/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projeto.command;

import com.br.projeto.model.dao.DirectoryDAO;
import com.br.projeto.model.dao.PlaceDAO;
import com.br.projeto.model.dao.UserInfoDAO;
import com.br.projeto.model.dao.UserProjDAO;
import com.br.projeto.model.dao.UserTypeDAO;
import com.br.projeto.model.entities.Directory;
import com.br.projeto.model.entities.Place;
import com.br.projeto.model.entities.Userinfo;
import com.br.projeto.model.entities.Userproj;
import com.br.projeto.model.entities.Usertype;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 * @author 31320600
 */
public class AdmCommand implements Command {

    UserTypeDAO userTypeDAO = lookupUserTypeDAOBean();
    UserProjDAO userProjDAO = lookupUserProjDAOBean();
    UserInfoDAO userInfoDAO = lookupUserInfoDAOBean();
    DirectoryDAO directoryDAO = lookupDirectoryDAOBean();
    PlaceDAO placeDAO = lookupPlaceDAOBean();

    private String returnPage = "/admFunctions.jsp";
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
        String action2 = request.getParameter("action2");
        String action3 = request.getParameter("action3");

        switch (action) {

            case "readUser":

                List<Userproj> usuarios = userProjDAO.find();
                request.getSession().setAttribute("listaUsuarios", usuarios);
                returnPage = "/readUser.jsp";

                break;

            case "registerUser":

                if (userProjDAO.findByUserName(request.getParameter("login")) != null) {
                    returnPage = "/register.jsp";
                    request.getSession().setAttribute("errormsg", "Usuário já utilizado!, favor utilizar outro para realizar o cadastro");
                } else if (!request.getParameter("password").equals(request.getParameter("password2"))) {
                    returnPage = "/register.jsp";
                    request.getSession().setAttribute("errormsg", "Senha não confere, favor digitar senhas compativeis");
                } else {

                    Userproj userproj = new Userproj();
                    Usertype usertype = userTypeDAO.findById(2);

                    userproj.setUsername(request.getParameter("login"));

                    String md5InsertADM = geraSenhaMD5(request.getParameter("password"));

                    userproj.setPassword(md5InsertADM);

                    Userinfo userinfo = new Userinfo();

                    userinfo.setFullname(request.getParameter("fullname"));
                    userinfo.setEmail(request.getParameter("email"));
                    userproj.setFkUsertype(usertype);

                    userproj.setDirectoryList(new ArrayList<Directory>());

                    String date = request.getParameter("bday");

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date bday = new Date();

                    try {
                        bday = formatter.parse(date);
                    } catch (ParseException ex) {
                        Logger.getLogger(UserProjCommand.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    userinfo.setBirthday(bday);

                    userproj.setUserinfo(userinfo);

                    userinfo.setUserproj(userproj);

                    userProjDAO.persist(userproj);

                    request.getSession().setAttribute("succesmsg", " usuario inserido com sucesso");

                }
                break;

            case "deleteUserPage":

                List<Userproj> usuarios2 = userProjDAO.find();
                request.getSession().setAttribute("listaUsuarios", usuarios2);
                returnPage = "/deleteUser.jsp";

                break;

            case "deleteUser":
                Long id = Long.parseLong(request.getParameter("idUser"));
                if (id == -1) {
                    request.getSession().setAttribute("errormsg", "Nenhum usuario foi selecionado.");
                } else {

                    Userproj upd = userProjDAO.findById(id);

                    userProjDAO.remove(upd);

                    request.getSession().setAttribute("successmsg", "Usuario deletado com sucesso");
                }
                break;

            case "updateUser":

                List<Userproj> usuarios3 = userProjDAO.find();
                request.getSession().setAttribute("listaUsuarios", usuarios3);
                returnPage = "/updateUser.jsp";

                break;

            case "update":
                switch (action2) {
                    case "updatePassword":
                        String password = request.getParameter("password");
                        String password2 = request.getParameter("password2");
                        Long id2 = Long.parseLong(request.getParameter("idUser"));
                        if (id2 == -1) {
                            request.getSession().setAttribute("errormsg", "Nenhum usuario foi selecionado.");
                        } else {
                            Userproj usuarioSenha = userProjDAO.findById(id2);

                            if (password.equals(password2)) {

                                String md5UpdateADM = geraSenhaMD5(password);
                                usuarioSenha.setPassword(md5UpdateADM);
                                userProjDAO.update(usuarioSenha);

                                request.getSession().setAttribute("successmsg", " Senha atualizada com sucesso");

                            } else {
                                request.getSession().setAttribute("errormsg", "Senha não confere, favor digitar senhas compativeis");
                            }
                        }
                        returnPage = "/updateUser.jsp";

                        break;

                    case "updateUserName":

                        Long id3 = Long.parseLong(request.getParameter("idUser"));
                        if (id3 == -1) {
                            request.getSession().setAttribute("errormsg", "Nenhum usuario foi selecionado.");
                        } else {

                            String username1 = request.getParameter("username");
                            Userproj upUserName = userProjDAO.findById(id3);
                            upUserName.setUsername(username1);

                            userProjDAO.update(upUserName);

                            request.getSession().setAttribute("usuarioSessao", upUserName);

                            request.getSession().setAttribute("successmsg", "Nome de usuario atualizado com sucesso");
                        }
                        returnPage = "/updateUser.jsp";
                        break;

                    case "updateBday":

                        Long id4 = Long.parseLong(request.getParameter("idUser"));
                        if (id4 == -1) {
                            request.getSession().setAttribute("errormsg", "Nenhum usuario foi selecionado.");
                        } else {

                            String data = request.getParameter("bday");
                            Userproj upBday = userProjDAO.findById(id4);

                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            Date bday = new Date();

                            try {
                                bday = formatter.parse(data);
                            } catch (ParseException ex) {
                                Logger.getLogger(UserProjCommand.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            upBday.getUserinfo().setBirthday(bday);

                            userProjDAO.update(upBday);

                            request.getSession().setAttribute("usuarioSessao", upBday);

                            request.getSession().setAttribute("successmsg", "Data de aniversário atualizado com sucesso");
                        }
                        returnPage = "/updateUser.jsp";
                        break;

                    case "updateEmail":

                        Long id5 = Long.parseLong(request.getParameter("idUser"));
                        if (id5 == -1) {
                            request.getSession().setAttribute("errormsg", "Nenhum usuario foi selecionado.");
                        } else {
                            String email1 = request.getParameter("email");
                            Userproj upEmail = userProjDAO.findById(id5);

                            upEmail.getUserinfo().setEmail(email1);

                            userProjDAO.update(upEmail);

                            request.getSession().setAttribute("usuarioSessao", upEmail);

                            request.getSession().setAttribute("successmsg", "Email atualizado com sucesso");
                        }
                        returnPage = "/updateUser.jsp";
                        break;

                    case "updateName":

                        Long id6 = Long.parseLong(request.getParameter("idUser"));
                        if (id6 == -1) {
                            request.getSession().setAttribute("errormsg", "Nenhum usuario foi selecionado.");
                        } else {
                            String nome = request.getParameter("nome");
                            Userproj upName = userProjDAO.findById(id6);

                            upName.getUserinfo().setFullname(nome);

                            userProjDAO.update(upName);

                            request.getSession().setAttribute("usuarioSessao", upName);

                            request.getSession().setAttribute("successmsg", "Nome atualizado com sucesso");
                        }
                        returnPage = "/updateUser.jsp";
                        break;

                }

                break;

            case "readDirectory":
                List<Directory> listaDirectory = directoryDAO.find();
                request.getSession().setAttribute("listaDiretorios", listaDirectory);
                returnPage = "/readDirectory.jsp";

                break;

            case "updateDirectoryPage":
                List<Directory> listaDirectory2 = directoryDAO.find();
                List<Userproj> listaUsuario = userProjDAO.find();
                request.getSession().setAttribute("listaDiretorios", listaDirectory2);
                request.getSession().setAttribute("listaUsuario", listaUsuario);
                returnPage = "/updateDirectory.jsp";

                break;

            case "updateDirectory":

                switch (action3) {

                    case "updateNameDirectory":
                        Long id3 = Long.parseLong(request.getParameter("idDiretorio"));
                        if (id3 == -1) {
                            request.getSession().setAttribute("errormsg", "Nenhum diretorio foi selecionado.");
                        } else {
                            Directory d = directoryDAO.findById(id3);

                            d.setNameDirectory(request.getParameter("nome"));

                            directoryDAO.update(d);

                            request.getSession().setAttribute("successmsg", "nome do diretório atualizado com sucesso");
                        }
                        break;

                    case "updateOwnerDirectory":
                        Long id4 = Long.parseLong(request.getParameter("idUser"));
                        Long id5 = Long.parseLong(request.getParameter("idDiretorio"));
                        if (id4 == -1) {
                            request.getSession().setAttribute("errormsg", "Nenhum usuario foi selecionado.");
                        } else if (id5 == -1) {
                            request.getSession().setAttribute("errormsg", "Nenhum diretorio foi selecionado.");
                        } else {

                            Userproj up = userProjDAO.findById(id4);
                            Directory d2 = directoryDAO.findById(id5);

                            d2.setFkUserproj(up);

                            directoryDAO.update(d2);

                            request.getSession().setAttribute("successmsg", "Dono do diretório atualizado com sucesso");
                        }
                        break;

                }
                break;

            case "deleteDirectoryPage":
                List<Directory> listaDirectory3 = directoryDAO.find();
                request.getSession().setAttribute("listaDiretorios", listaDirectory3);
                returnPage = "/deleteDiretory.jsp";
                break;

            case "deleteDirectory":

                Long id2 = Long.parseLong(request.getParameter("idDiretorio"));
                if (id2 == -1) {
                    request.getSession().setAttribute("errormsg", "Nenhum diretorio foi selecionado.");
                } else {
                    Directory d = directoryDAO.findById(id2);

                    directoryDAO.remove(d);

                    request.getSession().setAttribute("successmsg", "diretorio deletado com sucesso");
                }
                break;
                
            case "readPlaces":
                List<Place> places = placeDAO.find();
                request.getSession().setAttribute("listaLugares", places);
                returnPage = "/readPlaces.jsp";
                break;

            case "deletePlacePage":
                List<Place> places2 = placeDAO.find();
                request.getSession().setAttribute("listaLugares", places2);
                returnPage = "/deletePlaces.jsp";

                break;

            case "deletePlace":

                Long id3 = Long.parseLong(request.getParameter("idLugar"));
                if (id3 == -1) {
                    request.getSession().setAttribute("errormsg", "Nenhum lugar foi selecionado.");
                } else {

                    Place p = placeDAO.findById(id3);

                    placeDAO.remove(p);

                    request.getSession().setAttribute("successmsg", "Local deletado com sucesso");
                }
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

    private UserInfoDAO lookupUserInfoDAOBean() {
        try {
            Context c = new InitialContext();
            return (UserInfoDAO) c.lookup("java:global/ProjetoLP3/ProjetoLP3-ejb/UserInfoDAO!com.br.projeto.model.dao.UserInfoDAO");
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

    private UserTypeDAO lookupUserTypeDAOBean() {
        try {
            Context c = new InitialContext();
            return (UserTypeDAO) c.lookup("java:global/ProjetoLP3/ProjetoLP3-ejb/UserTypeDAO!com.br.projeto.model.dao.UserTypeDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private String geraSenhaMD5(String senha) {

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] arrayBytes = md.digest(senha.getBytes());

            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < arrayBytes.length; i++) {
                sb.append(Integer.toHexString((arrayBytes[i] & 0xFF) | 0x100).substring(1, 3));

            }

            return sb.toString();

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserProjCommand.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

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
