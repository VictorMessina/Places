<%-- 
    Document   : homepage
    Created on : 09/05/2016, 20:18:24
    Author     : 31449530
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Home </title>
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

        <!-- Site -->
        <div class="site">

            <!-- Header -->
            <div id="header" class="skel-panels-fixed">
                <div id="logo">
                    <h1><a href="homepage.jsp">GoPlaces</a></h1>
                </div>
                <nav id="nav">
                    <ul>
                        <li class="selecionado"><a href="homepage.jsp">Home</a></li>                        
                        <li><a href="perfil.jsp">Perfil</a></li>
                        <li><a href="search.jsp">Buscar Lugares</a></li>
                        <li><a href="FrontController?command=Directory&action=openDirectories">Diretórios</a></li>
                        <li><a href="grupo.jsp">Sobre Nós</a></li>
                        <li><a href="FrontController?command=UserProj&action=logout">Logout</a></li>
                    </ul>
                </nav>
            </div>
            
            <!-- Conteudo da pagina -->
            <div class="container">
                <h2>Bem vindo ao nosso site! Clique em "Buscar Lugares" para encontrar lugares interessantes pra ir.</h2>
            </div>
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