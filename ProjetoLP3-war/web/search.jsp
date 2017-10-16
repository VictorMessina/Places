<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Busca </title>
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.dropotron.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/skel-layers.min.js"></script>
        <script src="js/init.js"></script>
        <noscript>
        <link rel="stylesheet" href="css/skel.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        </noscript>
        <style>
            h2{
                font-size: 30px;
            }
            #oo{
                text-align: center;
            }
            .ll{
                text-align: center;
                color: white;
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
                        <li class="selecionado"><a href="search.jsp">Buscar Lugares</a></li>
                        <li><a href="FrontController?command=Directory&action=openDirectories">Diretórios</a></li>
                        <li><a href="grupo.jsp">Sobre Nós</a></li>
                        <li><a href="FrontController?command=UserProj&action=logout">Logout</a></li>
                    </ul>
                </nav>
            </div>

            <h2>Buscar Lugares</h2><br>

            <!-- Conteudo da pagina -->
            <div class="container">

                <c:if test="${successmsg!=null && !''.equals(successmsg)}">
                    <p class="success">${successmsg}</p>
                    <c:set scope="session" var="successmsg" value=""></c:set>
                </c:if>

                <c:if test="${errormsg!=null && !''.equals(errormsg)}">
                    <p class="error">${errormsg}</p>
                    <c:set scope="session" var="successmsg" value=""></c:set>
                </c:if>

                <c:set var="textsearch" scope="session" value="" />

                <form method="POST" action="FrontController">
                    <p><input type="text" name="textsearch" size="100" placeholder="Digite sua busca aqui" value=""/></p>
                    <input type="hidden" name="command" value="Place"/>   
                    <input type="hidden" name="action" value="searchPlace"/>          
                    <p id="oo"><input type="submit" value="Buscar"/></p>
                    <br><br>
                </form>

                <c:choose>
                    <c:when test="${placesCollection==null}">
                        <p class="ll">O que está esperando? Procure um lugar na barra de busca acima! (Ex: restaurantes na Avenida Paulista) </p>
                    </c:when>

                    <c:otherwise>
                        <c:choose>
                            <c:when test="${!placesCollection.isEmpty()}">

                                <c:forEach var="place" items="${placesCollection}" >
                                    <article class="placeArticle"> 
                                        <c:choose>
                                            <c:when test="${!place.getPhotos().isEmpty()}">
                                                <img src="${place.getPhotos().get(0)}" alt="place image" height="40" width="40"/>
                                            </c:when>
                                            <c:otherwise>
                                                <img src="fotos/naodisp.jpg" alt="place image" height="40" width="100"/>
                                            </c:otherwise>
                                        </c:choose>               

                                        <div class="descricao">
                                            <b>Nome:</b> ${place.getName_place()}<br>
                                            <b>Endereço:</b> ${place.getAddress()}<br>

                                            <c:choose>
                                                <c:when test="${place.getPrice() == 0}">
                                                    <b>Preço: Grátis</b><br>
                                                </c:when>
                                                <c:when test="${place.getPrice() == 1}">
                                                    <b>Preço:</b> $<br>
                                                </c:when>
                                                <c:when test="${place.getPrice() == 2}">
                                                    <b>Preço:</b> $$<br>
                                                </c:when>
                                                <c:when test="${place.getPrice() == 3}">
                                                    <b>Preço:</b> $$$<br>
                                                </c:when>
                                                <c:when test="${place.getPrice() == 4}">
                                                    <b>Preço:</b> $$$$<br>
                                                </c:when>                       
                                                <c:otherwise>
                                                    <b>Preço:</b> Não informado<br>
                                                </c:otherwise>
                                            </c:choose>

                                            <c:set var="tipos" value=""/>
                                            <c:forEach items="${place.getTypes()}" var="currentItem">
                                                <c:set var="tipos" value="${tipos}${currentItem}, " />
                                            </c:forEach>

                                            <b>Tags:</b> ${tipos.substring(0, tipos.length() - 2)}

                                            <br><br><br><a href="FrontController?command=Place&action=openPlace&idPlaceAPI=${place.getId_place()}"><input type="submit" value="Mais info"/></a>
                                        </div>
                                    </article>
                                </c:forEach>

                            </c:when>

                            <c:otherwise>
                                <p class="ll">Nenhum resultado foi encontrado para a sua busca. </p>
                            </c:otherwise>
                        </c:choose> 

                    </c:otherwise>
                </c:choose>
            </div>

            <c:remove var="placesCollection" scope="session"/>

    </body>
</html>