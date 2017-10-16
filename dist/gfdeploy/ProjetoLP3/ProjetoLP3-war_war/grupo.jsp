<%-- 
    Document   : grupo
    Created on : 09/05/2016, 20:21:05
    Author     : 31449530
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Sobre Nós </title>
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
            img{
                border-radius: 50%;
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
                    <h1><a href="homepage.jsp">GoPlaces</a></h1>
                </div>
                <nav id="nav">
                    <ul>
                        <li><a href="homepage.jsp">Home</a></li>                        
                        <li><a href="perfil.jsp">Perfil</a></li>
                        <li><a href="search.jsp">Buscar Lugares</a></li>
                        <li><a href="FrontController?command=Directory&action=openDirectories">Diretórios</a></li>
                        <li class="selecionado"><a href="grupo.jsp">Sobre Nós</a></li>
                        <li><a href="FrontController?command=UserProj&action=logout">Logout</a></li>
                    </ul>
                </nav>
            </div>

            <!-- Banner -->
            <div id="banner" class="container">
                <section>
                    <div class="row no-collapse-1">
                        <section class="4u"> 
                            <img src="https://scontent-gru2-1.xx.fbcdn.net/v/t1.0-9/10474454_877987978917908_2086797873274410422_n.jpg?oh=405915aba758c4b6edf020cbcb72d916&oe=57AEF02B" alt="leticia" height="200" width="200">
                        </section>

                        <section class="4u"> 
                            <img src="https://scontent-gru2-1.xx.fbcdn.net/v/t1.0-9/1457670_591978994228550_36718279_n.jpg?oh=055586f2e6041fdfda7304747a8890c2&oe=57B16605" alt="victor" height="200" width="200">
                        </section>

                        <section class="4u"> 
                            <img src="https://scontent-gru2-1.xx.fbcdn.net/v/t1.0-9/13055477_613901232096415_7971776520560186520_n.jpg?oh=948c25396f7ecede3c880fc394092f51&oe=57A6B7CC" alt="vitoria" height="200" width="200">
                        </section>
                    </div>	
                    <br><br><br>
                    <p> Alunos de Linguagem de Programação III do curso de Sistemas de Informação da Universidade Presbiteriana Mackenzie.</p>
                </section>
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