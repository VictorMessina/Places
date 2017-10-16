<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> ADM Functions </title>
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
            .container{
                margin-top: 100px;
                margin-left: 180px;
            }
            a.bb {
                background: #006299;
                color: fff;
                border: none;
                display: block;
                outline: 0;
                padding: 10px 16px;
                text-decoration: none;
                text-align: center;
                text-transform: uppercase;
                width: 35%;
                border-radius: 6px;
            }
        </style>
    </head>

    <body>

        <c:if test="${usuarioSessao==null}" >
            <c:redirect url="index.jsp"></c:redirect> 
        </c:if>
        
        <p>${usuarioSessao.getFkUsertype()}</p>

        <!-- Header -->
        <div id="header" class="skel-panels-fixed">
            <div id="logo">
                <h1>GoPlaces</h1>
            </div>
            <nav id="nav">
                <ul>
                    <li><h1>Welcome, ${usuarioSessao.getUserinfo().getFullname()}</h1></li>
                    <li><a href="FrontController?command=UserProj&action=logout">LOGOUT</a></li>
                </ul>
            </nav>
        </div>

        <div class="container">

            <c:if test="${successmsg!=null && !''.equals(successmsg)}">
                <p class="success">${successmsg}</p>
                <c:set scope="session" var="successmsg" value=""></c:set>
            </c:if>

            <c:if test="${errormsg!=null && !''.equals(errormsg)}">
                <p class="error">${errormsg}</p>
                <c:set scope="session" var="successmsg" value=""></c:set>
            </c:if>

            <div class="row no-collapse-1">               
                <section class="4u"> 
                    <nav>
                        <ul>
                            <br><li><a class="bb" href="registerUser.jsp"><b> Adicionar Usuários</b></a></li>
                            <br><li><a class="bb" href="FrontController?command=Adm&action=readUser"><b> Buscar Usuários </b></a></li>
                            <br><li><a class="bb" href="FrontController?command=Adm&action=updateUser"><b> Atualizar Usuários </b></a></li>
                            <br><li><a class="bb" href="FrontController?command=Adm&action=deleteUserPage"><b> Deletar Usuários </b></a></li>
                        </ul>
                    </nav>
                </section>

                <section class="4u"> 
                    <nav>
                        <ul>
                            <br><li><a class="bb" href="FrontController?command=Directory&action=registerDirectory"><b> Adicionar Diretórios </b></a></li>
                            <br><li><a class="bb" href="FrontController?command=Adm&action=readDirectory"><b> Buscar Diretórios </b></a></li>
                            <br><li><a class="bb" href="FrontController?command=Adm&action=updateDirectoryPage"><b> Atualizar Diretórios </b></a></li>
                            <br><li><a class="bb" href="FrontController?command=Adm&action=deleteDirectoryPage"><b> Deletar Diretórios </b></a></li>
                        </ul>
                    </nav>
                </section>

                <section class="4u"> 
                    <nav>
                        <ul>
                            <br><li><a class="bb" href="FrontController?command=Adm&action=readPlaces"><b> Buscar Locais </b></a></li>
                            <br><li><a class="bb" href="FrontController?command=Adm&action=deletePlacePage"><b> Deletar Locais</b></a></li>
                        </ul>
                    </nav>
                </section>
            </div>

        </div>
    </body>
</html>