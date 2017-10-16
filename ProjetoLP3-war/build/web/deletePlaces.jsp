<%-- 
    Document   : deletePlaces
    Created on : 25/05/2016, 18:14:28
    Author     : 31402836
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Deletar Lugares </title>
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

            <c:if test="${successmsg!=null && !''.equals(successmsg)}">
                <p class="success">${successmsg}</p>
                <c:set scope="session" var="successmsg" value=""></c:set>
            </c:if>

            <c:if test="${errormsg!=null && !''.equals(errormsg)}">
                <p class="error">${errormsg}</p>
                <c:set scope="session" var="successmsg" value=""></c:set>
            </c:if>

            <h2> Deletar Lugares  </h2> <br><br><br>
            <section>
                <p> Selecione o lugar que deseja excluir: </p>
                <form action="FrontController" method="POST">
                    <select name="idLugar">
                        <option value="-1"> selecionar </option>
                        <c:forEach var="lug" items="${listaLugares}">

                            <option value="${lug.getIdPlace()}"> ${lug.getNameApi()} </option>

                        </c:forEach>

                    </select>
                    <input type="hidden" name="command" value="Adm" />
                    <input type="hidden" name="action" value="deletePlace" />
                    <br><br><p> <input type="submit" value="Delete place"/></p>
                </form>
            </section>
        </div>	
    </body>
</html>
