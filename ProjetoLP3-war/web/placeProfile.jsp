<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : placeprofile
    Created on : 19/05/2016, 02:55:25
    Author     : Vitória
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil lugar</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.dropotron.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/skel-layers.min.js"></script>
        <script src="js/init.js"></script>
        <script src="http://maps.googleapis.com/maps/api/js"></script>

        <noscript>
        <link rel="stylesheet" href="css/skel.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        </noscript>

        <style>
            .container{
                color: white;
                text-align: center;
            }
        </style>
        <script>
            function initialize() {
                var mapProp = {
                    center: new google.maps.LatLng(${place.getLatitude()}, ${place.getLongitude()}),
                    zoom: 15,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

                var marker = new google.maps.Marker({
                    position: {lat: ${place.getLatitude()}, lng: ${place.getLongitude()}},
                    map: map,
                    title: ''
                });
            }
            google.maps.event.addDomListener(window, 'load', initialize);

        </script>
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

            <h2> ${place.getName_place()}</h2><br>

            <!-- Conteudo da pagina -->
            <div class="container">       
                <section>
                    Esse lugar foi salvo em <b><u>${popularity}</u></b> diretórios de usuários <br><br>

                    <c:if test="${successmsg!=null && !''.equals(successmsg)}">
                        <p class="success">${successmsg}</p>
                        <c:set scope="session" var="successmsg" value=""></c:set>
                    </c:if>

                    <c:if test="${errormsg!=null && !''.equals(errormsg)}">
                        <p class="error">${errormsg}</p>
                        <c:set scope="session" var="errormsg" value=""></c:set>
                    </c:if>

                    <c:if test="${listaD.size()==0}">
                        <h2><c:out value="ATENÇÃO: Sua lista de diretórios esta vazia, crie um diretório antes de continuar." > </c:out></h2>
                    </c:if>

                    <form action="FrontController" method="POST">
                        <select name="idDiretorio">
                            <option value="-1"> selecionar </option>
                            <c:forEach var="dir" items="${listaD}">
                                <option value="${dir.getIdDirectory()}"> ${dir.getNameDirectory()} </option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="idAPI" value="${place.getId_place()}"/>
                        <input type="hidden" name="nameAPI" value="${place.getName_place()}"/>
                        <input type="hidden" name="command" value="Place"/>  
                        <input type="hidden" name="action" value="insertPlaceUser"/>  
                        <p><input type="submit" value="Adicionar lugar ao meu diretório"/></p>
                    </form>

                    <div class="row no-collapse-1">
                        <section class="6u"> 
                            <c:choose>
                                <c:when test="${!place.getPhotos().isEmpty()}">
                                    <div class="carrosel">
                                        <br>
                                        <div id="myCarousel" class="carousel slide" data-ride="carousel">
                                            <!-- Indicators -->
                                            <ol class="carousel-indicators">
                                                <c:forEach var="i" begin="0" end="${place.getPhotos().size()-1}">
                                                    <li data-target="#myCarousel" data-slide-to="${i}"></li>
                                                    </c:forEach>                            
                                            </ol>

                                            <!-- Wrapper de imagens -->
                                            <div class="carousel-inner" role="listbox">
                                                <c:forEach var="linkImage" items="${place.getPhotos()}" >
                                                    <c:choose>
                                                        <c:when test="${place.getPhotos().indexOf(linkImage)==0}">
                                                            <div class="item active">
                                                                <img src="${linkImage}" alt="foto lugar" width="260" height="145">
                                                            </div>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div class="item">
                                                                <img src="${linkImage}" alt="foto lugar" width="260" height="145">
                                                            </div>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </div>

                                            <!-- Controles de mudança de imagem -->
                                            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                                                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                                <span class="sr-only">Previous</span>
                                            </a>
                                            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                                <span class="sr-only">Next</span>
                                            </a>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <img src="fotos/naodisp.jpg" alt="place image" />
                                </c:otherwise>
                            </c:choose> 
                        </section>
                        <section class="6u"> 
                            <br><div id="googleMap" style="width:500px;height:400px;"></div>
                        </section>
                    </div>

                    <div class="descricao">
                        <b>Endereço:</b> ${place.getAddress()}<br>

                        <c:if test="${place.getPrice()!=null}">
                            <c:choose>
                                <c:when test="${place.getPrice() == 0}">
                                    <b>Preço:</b> Grátis<br>
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
                        </c:if>

                        <c:if test="${place.getPhone_number()!=null}">
                            <b>Telefone:</b> ${place.getPhone_number()}<br>
                        </c:if>

                        <c:if test="${place.getWebsite()!=null}">
                            <b>Website:</b> ${place.getWebsite()}<br>
                        </c:if>

                        <c:if test="${!place.getWeekdays().isEmpty()}">
                            <b>Horário de funcionamento:</b>
                            <br>
                            <c:forEach items="${place.getWeekdays()}" var="day">
                                <p>${day}</p>
                                <br>
                            </c:forEach>
                        </c:if>   

                        <c:set var="tipos" value="" />
                        <c:forEach items="${place.getTypes()}" var="currentItem">
                            <c:set var="tipos" value="${tipos}${currentItem}, " />
                        </c:forEach>
                        <b>Tags:</b> ${tipos.substring(0, tipos.length() - 2)}<br>

                    </div>

                </section>
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
            <c:remove var="placesCollection" scope="session"/>
    </body>

</html>