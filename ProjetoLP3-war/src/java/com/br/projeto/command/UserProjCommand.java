/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projeto.command;

import com.br.projeto.jms.JmsProdutorLocal;
import com.br.projeto.jms.MsgType;
import com.br.projeto.model.dao.DirectoryDAO;
import com.br.projeto.model.dao.UserProjDAO;
import com.br.projeto.model.dao.UserTypeDAO;
import com.br.projeto.model.entities.Directory;
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
 * @author 31449530
 */
public class UserProjCommand implements Command {
    JmsProdutorLocal jmsProdutor = lookupJmsProdutorLocal();

    DirectoryDAO directoryDAO = lookupDirectoryDAOBean();

    UserTypeDAO userTypeDAO = lookupUserTypeDAOBean();
    UserProjDAO userProjDAO = lookupUserProjDAOBean();
    
    

    private String returnPage = "/index.jsp";
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

        switch (action) {

            case "login":

                Userproj up = (Userproj) userProjDAO.findByUserName(request.getParameter("login"));

                String md5Login = geraSenhaMD5(request.getParameter("password"));

                if (up == null) {
                    request.getSession().setAttribute("errormsg", "Usuário não encontrado!, favor tente novamente");

                } else {
                    System.out.println(up.toString());

                    if (!up.getPassword().equals(md5Login)) {
                        request.getSession().setAttribute("errormsg", "Senha não confere, favor tente novamente");
                    }

                    if (up.getFkUsertype().getIdUsertype() == 1 && up.getPassword().equals(md5Login)) {

                        jmsProdutor.sendMessage(MsgType.LOGIN," Usuario ADM: " + up.getUsername() + " realizou login " + " \n" );
                        
                        request.getSession().setAttribute("usuarioSessao", up);
                        returnPage = "/admFunctions.jsp";

                    } else {

                        if (up.getFkUsertype().getIdUsertype() == 2 && up.getPassword().equals(md5Login)) {

                             jmsProdutor.sendMessage(MsgType.LOGIN,"Usuario Comum: " + up.getUsername() + " realizou login" + "\n");
                            
                            request.getSession().setAttribute("usuarioSessao", up);

                            returnPage = "/homepage.jsp";

                        }
                    }
                }
                break;

            case "logout":
                
                Userproj ups = (Userproj) request.getSession().getAttribute("usuarioSessao");
                
                 jmsProdutor.sendMessage(MsgType.LOGOUT,"Usuario: " + ups.getUsername() + " realizou logout" + "\n");
                request.getSession().setAttribute("usuarioSessao", null);

                break;

            case "insert":

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

                    String md5InsertNormal = geraSenhaMD5(request.getParameter("password"));

                    userproj.setPassword(md5InsertNormal);

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
                    request.getSession().setAttribute("usuarioSessao", userproj);
                    returnPage = "/homepage.jsp";
                }

                break;

            case "updatePasswordUser":
                String pwd = request.getParameter("password");
                String pwd2 = request.getParameter("password2");
                Userproj usuarioSessao = (Userproj) request.getSession().getAttribute("usuarioSessao");

                usuarioSessao = userProjDAO.findById(usuarioSessao.getIdUser());

                if (pwd.equals(pwd2)) {
 
                    String md5UpdateUser = geraSenhaMD5(pwd);
                    usuarioSessao.setPassword(md5UpdateUser);
                    userProjDAO.update(usuarioSessao);

                    returnPage = "/perfil.jsp";

                    request.getSession().setAttribute("successmsg", "Senha atualizada com sucesso");
                } else {
                    returnPage = "/perfil.jsp";
                    request.getSession().setAttribute("errormsg", "Senha não confere, favor digitar senhas compativeis");
                }
                break;

            case "updateFullNameUser":
                String nomeCompleto = request.getParameter("fullname");
                Userproj usuarioSessao2 = (Userproj) request.getSession().getAttribute("usuarioSessao");

                usuarioSessao2 = userProjDAO.findById(usuarioSessao2.getIdUser());
                usuarioSessao2.getUserinfo().setFullname(nomeCompleto);

                userProjDAO.update(usuarioSessao2);

                request.getSession().setAttribute("usuarioSessao", usuarioSessao2);

                request.getSession().setAttribute("successmsg", "Nome atualizado com sucesso");

                returnPage = "/perfil.jsp";

                break;

            case "updateUserNameUser":

                String username = request.getParameter("username");
                Userproj usuarioSessao3 = (Userproj) request.getSession().getAttribute("usuarioSessao");

                usuarioSessao3 = userProjDAO.findById(usuarioSessao3.getIdUser());
                usuarioSessao3.setUsername(username);

                userProjDAO.update(usuarioSessao3);

                request.getSession().setAttribute("usuarioSessao", usuarioSessao3);

                request.getSession().setAttribute("successmsg", "Nome de usuario atualizado com sucesso");

                returnPage = "/perfil.jsp";

                break;

            case "updateEmailUser":

                String email = request.getParameter("email");

                Userproj usuarioSessao4 = (Userproj) request.getSession().getAttribute("usuarioSessao");

                usuarioSessao4 = userProjDAO.findById(usuarioSessao4.getIdUser());
                usuarioSessao4.getUserinfo().setEmail(email);

                userProjDAO.update(usuarioSessao4);

                request.getSession().setAttribute("usuarioSessao", usuarioSessao4);

                request.getSession().setAttribute("successmsg", "Email atualizado com sucesso");

                returnPage = "/perfil.jsp";

                break;

        }

    }

    @Override
    public String getReturnPage() {
        return returnPage;
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

    private DirectoryDAO lookupDirectoryDAOBean() {
        try {
            Context c = new InitialContext();
            return (DirectoryDAO) c.lookup("java:global/ProjetoLP3/ProjetoLP3-ejb/DirectoryDAO!com.br.projeto.model.dao.DirectoryDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private JmsProdutorLocal lookupJmsProdutorLocal() {
        try {
            Context c = new InitialContext();
            return (JmsProdutorLocal) c.lookup("java:global/ProjetoLP3/ProjetoLP3-ejb/JmsProdutor!com.br.projeto.jms.JmsProdutorLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
