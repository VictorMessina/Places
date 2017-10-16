<%-- 
    Document   : MoreInfoDirectory
    Created on : 27/05/2016, 18:31:49
    Author     :  31449530
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Diretórios</title>
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.dropotron.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/skel-layers.min.js"></script>
        <script src="js/init.js"></script>

        <script type="text/javascript">
            var form3;
            window.onload = function () {
                form3 = document.getElementById("form3");
                form3.style.display = "none";
            }

            function mostrar(num) {
                if (num == 3) {
                    form3.style.display = "block";
                }
            }
        </script>

        <noscript>
        <link rel="stylesheet" href="css/skel.css" />
        <link rel="stylesheet" href="css/style.css" />
        </noscript>
        <style>
            img{
                border-radius: 50%;
            }
            #botao{
                transition: background-color 0.35s ease-in-out;
                background: #4C5AFF;
                border-radius: 6px;
                border: 0;
                color: #fff;
                cursor: pointer;
                display: inline-block;
                padding: 10px 24px;
                text-align: center;
                text-decoration: none;
                text-transform: uppercase;
                display: inline-block;
                font-size: 19px;
                font-weight: 600;
            }
            input[type="submit"]:hover,
            #botao:hover {
                background: #00097F;
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
                        <li class="selecionado"><a href="FrontController?command=Directory&action=openDirectories">Diretórios</a></li>
                        <li><a href="grupo.jsp">Sobre Nós</a></li>
                        <li><a href="FrontController?command=UserProj&action=logout">Logout</a></li>
                    </ul>
                </nav>
            </div>
            <a class="link" href="FrontController?command=Directory&action=openDirectories">Voltar para diretórios</a>
            <h2>Diretório: ${diretorio.getNameDirectory()}</h2><br><br><br>

            <div id="banner" class="container">
                <button id="botao" onclick="mostrar(3)">excluir lugar</button>

                <c:if test="${successmsg!=null && !''.equals(successmsg)}">
                    <p class="success">${successmsg}</p>
                    <c:set scope="session" var="successmsg" value=""></c:set>
                </c:if>

                <c:if test="${errormsg!=null && !''.equals(errormsg)}">
                    <p class="error">${errormsg}</p>
                    <c:set scope="session" var="errormsg" value=""></c:set>
                </c:if>


                <form id="form3" action="FrontController" method="POST">
                    <br><br><br><h3>Excluir lugar</h3>
                    <select name="idLugar">
                        <option value="-1"> selecionar </option>
                        <c:forEach var="place" items="${listaLugar}">
                            <option value="${place.getIdPlace()}"> ${place.getNameApi()} </option>
                        </c:forEach> 
                        <p><input type="hidden" name="command" value="Place"/></p>
                        <p><input type="hidden" name="action" value="deletePlaceUser"/></p>
                        <p><input type="hidden" name="idDiretorio" value="${diretorio.getIdDirectory()}"/></p>
                        <p><input type="submit" value="Deletar lugar"/></p>
                </form>
            </div>

            <!-- Locais -->
                        
            <div class="container">
                <div class="row no-collapse-1">
                    <c:forEach var="lugar" items="${listaLugar}">
                        <section class="4u"> 
                            <div class="box">
                                <b>Local:</b> ${lugar.getNameApi()}
                                <br><b>Popularidade:</b> ${lugar.getPopularity()}
                                <br><br><a href="FrontController?command=Place&action=openPlace&idPlaceAPI=${lugar.getIdApi()}"><input type="submit" value="Mais info"/></a>
                            </div>
                        </section>
                    </c:forEach>
                </div>
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
