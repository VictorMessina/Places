<%-- 
    Document   : updateDirectory
    Created on : 25/05/2016, 19:36:18
    Author     : 31449530
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Atualizar diretórios </title>
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

            .error{
                padding:5px;
                background-color: rgba(204, 20, 0, 0.3);
                border: 1px solid rgb(204, 20, 0);
                width: 400px;
                margin:10px auto;
                text-align: center;
                color: #fff;
                position:relative;
                animation: anim 1s;
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
            <c:if test="${listaDiretorios.size()==0}">
                <h2><c:out value="ATENÇÃO: Lista de Diretorios vazia"></c:out></h2>
            </c:if>
            <h2> Atualizar diretorio </h2> <br><br><br>
            <section>
                <c:if test="${successmsg!=null && !''.equals(successmsg)}">
                    <p class="success">${successmsg}</p>
                    <c:set scope="session" var="successmsg" value=""></c:set>
                </c:if>

                <c:if test="${errormsg!=null && !''.equals(errormsg)}">
                    <p class="error">${errormsg}</p>
                    <c:set scope="session" var="successmsg" value=""></c:set>
                </c:if>

                <p><b> Mudar Nome: </b></p>
                <form action="FrontController" method="POST">
                    <select name="idDiretorio">
                        <option value="-1"> selecionar </option>
                        <c:forEach var="dir" items="${listaDiretorios}">
                            <option value="${dir.getIdDirectory()}"> ${dir.getFkUserproj().getUserinfo().getFullname()} : nomeDiretorio: ${dir.getNameDirectory()} </option>
                        </c:forEach> 
                    </select>                    
                    <br><br><p><input type="text" name="nome" placeholder="novo nome" required/></p>
                    <input type="hidden" name="command" value="Adm" />
                    <input type="hidden" name="action" value="updateDirectory" />
                    <input type="hidden" name="action3" value="updateNameDirectory" />
                    <p> <input type="submit" value="Mudar Nome"/></p>
                </form>
            </section>

            <br><hr><br>

            <section>
                <p><b> Mudar Dono do diretorio: </b></p>
                <form action="FrontController" method="POST">
                    <select name="idUser">
                        <option value="-1"> selecionar </option>
                        <c:forEach var="user" items="${listaUsuario}">
                            <c:if test="${user.getFkUsertype().getIdUsertype()!= 1}">
                                <option value="${user.getIdUser()}"> ${user.getUserinfo().getFullname()} </option>
                            </c:if>
                        </c:forEach> 
                    </select>
                    <p></p>
                    <select name="idDiretorio">
                        <option value="-1"> selecionar </option>
                        <c:forEach var="dir" items="${listaDiretorios}">
                            <option value="${dir.getIdDirectory()}"> ${dir.getFkUserproj().getUserinfo().getFullname()} : nomeDiretorio: ${dir.getNameDirectory()} </option>
                        </c:forEach> 
                    </select>
                    <p></p>
                    <input type="hidden" name="command" value="Adm" />
                    <input type="hidden" name="action" value="updateDirectory" />
                    <input type="hidden" name="action3" value="updateOwnerDirectory" />
                    <p><input type="submit" value="Mudar Dono do diretorio "/></p>
                </form>
            </section>
        </div>
    </body>
</html>
