<%-- 
    Document   : registerDirectory
    Created on : 20/05/2016, 16:50:31
    Author     : 31320600
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Cadastro Diretório </title>
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.dropotron.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/skel-layers.min.js"></script>
        <script src="js/init.js"></script>
        <noscript>
        <link rel="stylesheet" href="css/skel.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        </noscript>
    </head>
    <body>

        <c:if test="${usuarioSessao==null}" >
            <c:redirect url="index.jsp"></c:redirect> 
        </c:if>

        <c:if test="${successmsg!=null && !''.equals(successmsg)}">
            <p class="success">${successmsg}</p>
            <c:set scope="session" var="successmsg" value=""></c:set>
        </c:if>

        <c:if test="${errormsg!=null && !''.equals(errormsg)}">
            <p class="error">${errormsg}</p>
            <c:set scope="session" var="successmsg" value=""></c:set>
        </c:if>
        <!-- Site -->
        <div class="site">

            <!-- Header -->
            <div id="header" class="skel-panels-fixed">
                <div id="logo">
                    <h1>GoPlaces</h1>
                </div>
                <nav id="nav">
                    <ul>
                        <li><a href="admFunctions.jsp">Voltar a page ADM</a></li>
                        <li><a href="FrontController?command=UserProj&action=logout">Sair</a></li>
                    </ul>
                </nav>
            </div>

            <!-- Banner -->
            <div id="banner" class="container">

                <!-- Criar diretorio -->
                <form action="FrontController" method="post">
                    <h3>Criar Diretório</h3>
                    <select name="idUser">
                        <option value="-1"> selecionar </option>
                        <c:forEach var="user" items="${listaUsuarios}">
                            <c:if test="${user.getFkUsertype().getIdUsertype()==2}">
                                <option value="${user.getIdUser()}"> ${user.getUserinfo().getFullname()} </option>
                            </c:if>
                        </c:forEach> 
                    </select>
                    <p></p>
                    <p><input type="text" name="name" placeholder="Nome diretório" maxlength="140" required="required"/></p>
                    <p><input type="hidden" name="command" value="Directory"/></p>
                    <p><input type="hidden" name="action" value="insert"/></p>
                    <p><input type="submit" value="Criar Diretório"/></p>
                </form>
            </div>

            <!-- Rodapé -->
            <div id="footer">
                <div class="container">
                    <br>
                    <ul class="icons">
                        <li><a href="https://www.facebook.com/letiglow" class="fa fa-facebook"></a></li>
                        <li><a href="https://www.facebook.com/victor.messina.7" class="fa fa-facebook"></a></li>
                        <li><a href="https://www.facebook.com/vitoria.previato.abellaneda?fref=ts" class="fa fa-facebook"></a></li>
                    </ul>
                    <ul class="copyright">
                        <li>&copy; Leticia, Victor, Vitoria</li>
                    </ul>
                </div>
            </div>
    </body>
</html>