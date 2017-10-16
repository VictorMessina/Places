<%-- 
    Document   : directories
    Created on : 23/05/2016, 13:24:59
    Author     : 31320600
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
            var form1;
            var form2;
            var form3;
            window.onload = function () {
                form1 = document.getElementById("form1");
                form2 = document.getElementById("form2");
                form3 = document.getElementById("form3");
                form1.style.display = "none";
                form2.style.display = "none";
                form3.style.display = "none";
            }

            function mostrar(num) {
                if (num == 1) {
                    form1.style.display = "block";
                    form2.style.display = "none";
                    form3.style.display = "none";
                } else if (num == 2) {
                    form2.style.display = "block";
                    form1.style.display = "none";
                    form3.style.display = "none";
                } else {
                    form3.style.display = "block";
                    form1.style.display = "none";
                    form2.style.display = "none";
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
            <!-- Banner -->
            <div id="banner" class="container">
                <button id="botao" onclick="mostrar(1)">Criar Diretório</button>
                <button id="botao" onclick="mostrar(2)">mudar nome do diretorio</button>
                <button id="botao" onclick="mostrar(3)">excluir diretorio</button>

                <c:if test="${successmsg!=null && !''.equals(successmsg)}">
                    <p class="success">${successmsg}</p>
                    <c:set scope="session" var="successmsg" value=""></c:set>
                </c:if>

                <c:if test="${errormsg!=null && !''.equals(errormsg)}">
                    <p class="error">${errormsg}</p>
                    <c:set scope="session" var="errormsg" value=""></c:set>
                </c:if>


                <!-- Criar diretorio -->
                <form id="form1" action="FrontController" method="post">
                    <br><br><br><h3>Criar Diretório</h3>
                    <p><input type="text" name="name" placeholder="Nome diretório" maxlength="140" required="required"/></p>
                    <p><input type="hidden" name="command" value="Directory"/></p>
                    <p><input type="hidden" name="action" value="insertDirectoryUser"/></p>
                    <p><input type="submit" value="Criar Diretório"/></p>
                </form>

                <form id="form2" action="FrontController" method="post">
                    <br><br><br><h3>Mudar nome do diretorio</h3>
                    <select name="idDiretorio">
                        <option value="-1"> selecionar </option>
                        <c:forEach var="dir" items="${listaDiretorios}">
                            <option value="${dir.getIdDirectory()}"> ${dir.getNameDirectory()} </option>
                        </c:forEach> 
                        <p><input type="text" name="name" placeholder="Nome diretório" maxlength="140" required="required"/></p>
                        <p><input type="hidden" name="command" value="Directory"/></p>
                        <p><input type="hidden" name="action" value="updateDirectoryUser"/></p>
                        <p><input type="submit" value="atualizar Diretório"/></p>
                </form>

                <form id="form3" action="FrontController" method="POST">
                    <br><br><br><h3>Excluir diretorio</h3>
                    <select name="idDiretorio">
                        <option value="-1"> selecionar </option>
                        <c:forEach var="dir" items="${listaDiretorios}">
                            <option value="${dir.getIdDirectory()}"> ${dir.getNameDirectory()} </option>
                        </c:forEach> 
                        <p><input type="hidden" name="command" value="Directory"/></p>
                        <p><input type="hidden" name="action" value="deleteDirectoryUser"/></p>
                        <p><input type="submit" value="deletar Diretório"/></p>
                </form>
            </div>
                       
             <!-- Diretórios -->
            <div class="container">
                <div class="row no-collapse-1">
                    <c:forEach var="diretorios" items="${listaDiretorios}">
                        <section class="4u"> 
                            <div class="box">
                                <h3>${diretorios.getNameDirectory()}</h3>
                                <br><a href="FrontController?command=Directory&action=moreInfoDirectory&idDirectory=${diretorios.getIdDirectory()}"><input type="submit" value="MAIS INFO"/></a>
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
