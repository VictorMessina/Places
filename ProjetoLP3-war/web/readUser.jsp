<%-- 
    Document   : readUserProj
    Created on : 09/05/2016, 21:12:51
    Author     : 31449530
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Ler Usuário </title>
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.dropotron.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/skel-layers.min.js"></script>
        <script src="js/init.js"></script>
        <noscript>
        <link rel="stylesheet" href="css/skel.css" />
        <link rel="stylesheet" href="css/style.css" />
        </noscript>
        <style>
            h2{
                font-size: 30px;
            }
        </style>
    </head>
    <body>
         <c:if test="${usuarioSessao==null}" >
            <c:redirect url="index.jsp"></c:redirect> 
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
        </div>

        <!-- Banner -->
        <div id="banner" class="container">
            <h2> Ler Usuário </h2> <br><br><br>
            <section>
                    <c:if test="${listaUsuario.size()==0}">
                        <h2><c:out value="ATENÇÃO: A lista de usuarios comuns esta vazia" > </c:out></h2>
                    </c:if>
                    <br><br>
                    <c:out value="LISTA DE USUARIOS COMUNS" > </c:out>
                        <br>
                    <c:forEach var="user" items="${listaUsuarios}">
                        <c:if test="${user.getFkUsertype().getIdUsertype()==2}"> 
                            ${user.getUserinfo().getFullname()} <br>
                        </c:if>
                    </c:forEach>
                </section>

                <hr>

                <section>
                    <c:if test="${listaUsuarios.size()==0}">
                        <h2><c:out value="ATENÇÃO: A lista de usuarios com funçoes de administrador esta vazia" > </c:out></h2>
                    </c:if>
                    <br><br>
                    <c:out value="LISTA DE USUARIOS COM FUNÇÕES DE ADMINISTRADOR" > </c:out>
                        <br>
                    <c:forEach var="user" items="${listaUsuarios}">
                        <c:if test="${user.getFkUsertype().getIdUsertype()!= 2}"> 
                            ${user.getUserinfo().getFullname()} <br>
                        </c:if>
                    </c:forEach>
                </section>
            </div>		
    </body>
</html>