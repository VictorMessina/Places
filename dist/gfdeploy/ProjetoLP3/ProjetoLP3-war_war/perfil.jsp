<%-- 
    Document   : perfil
    Created on : 09/05/2016, 20:16:00
    Author     : 31449530
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Perfil </title>
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.dropotron.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/skel-layers.min.js"></script>
        <script src="js/init.js"></script>
        <script type="text/javascript">
            var form1;
            var form2;
            var form3;
            var form4;
            window.onload = function () {
                form1 = document.getElementById("form1");
                form2 = document.getElementById("form2");
                form3 = document.getElementById("form3");
                form4 = document.getElementById("form4");

                form1.style.display = "none";
                form2.style.display = "none";
                form3.style.display = "none";
                form4.style.display = "none";
            };

            function mostrar(num) {
                if (num === 1) {
                    form1.style.display = "block";
                    form2.style.display = "none";
                    form3.style.display = "none";
                    form4.style.display = "none";
                } else if (num === 2) {
                    form2.style.display = "block";
                    form1.style.display = "none";
                    form3.style.display = "none";
                    form4.style.display = "none";
                }
                else if (num === 3) {
                    form3.style.display = "block";
                    form1.style.display = "none";
                    form2.style.display = "none";
                    form4.style.display = "none";
                } else if (num === 4) {
                    form4.style.display = "block";
                    form1.style.display = "none";
                    form2.style.display = "none";
                    form3.style.display = "none";
                }
            }
        </script>
        <noscript>
        <link rel="stylesheet" href="css/skel.css" />
        <link rel="stylesheet" href="css/style.css" />
        </noscript>
        <style>
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
                        <li class="selecionado"><a href="perfil.jsp">Perfil</a></li>
                        <li><a href="search.jsp">Buscar Lugares</a></li>
                        <li><a href="FrontController?command=Directory&action=openDirectories">Diretórios</a></li>
                        <li><a href="grupo.jsp">Sobre Nós</a></li>
                        <li><a href="FrontController?command=UserProj&action=logout">Logout</a></li>
                    </ul>
                </nav>
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
                <p> Olá, ${usuarioSessao.getUserinfo().getFullname()}! Confira abaixo seus dados: </p>
                <br>
                <section>
                    <div class="row no-collapse-1">
                        <section class="6u"> 
                            <p> Nome: ${usuarioSessao.getUserinfo().getFullname()} </p>
                            <p> E-mail: ${usuarioSessao.getUserinfo().getEmail()} </p>
                            <p> Bday: ${usuarioSessao.getUserinfo().getBirthday()}</p>
                        </section>
                        <section class="6u"> 
                            <p> Login: ${usuarioSessao.getUsername()} </p>
                            <p> tipoUsuario: ${usuarioSessao.getFkUsertype().getTitle()} </p>
                        </section>
                    </div>
                </section>
                <br><br>

                <c:if test="${successmsg!=null && !''.equals(successmsg)}">
                    <p class="success">${successmsg}</p>
                    <c:set scope="session" var="successmsg" value=""></c:set>
                </c:if>

                <br><br>

                <button id="botao" onclick="mostrar(1)">Alterar Senha</button>
                <button id="botao" onclick="mostrar(2)">Alterar nome</button>
                <button id="botao" onclick="mostrar(3)">Alterar userName</button>
                <button id="botao" onclick="mostrar(4)">Alterar Email</button>

                <!-- Alterar senha -->
                <form id="form1" action="FrontController" method="post">
                    <br><br><br><h3>Alterar Senha</h3>
                    <p><input type="password" name="password" placeholder="Nova senha" required="required"/></p>
                    <p><input type="password" name="password2" placeholder="Confirmar senha nova" required="required"/></p>
                    <p><input type="hidden" name="command" value="UserProj"/></p>
                    <p><input type="hidden" name="action" value="updatePasswordUser"/></p>
                    <p><input type="submit" value="ALTERAR"/></p>
                </form>


                <form id="form2" action="FrontController" method="POST">
                    <br><br><br><h3>Alterar Nome</h3>
                    <p><input type="text" name="fullname" placeholder="nome completo" required="required"/></p>
                    <input type="hidden" name="command" value="UserProj"/>
                    <input type="hidden" name="action" value="updateFullNameUser"/>
                    <p><input type="submit" value="ATUALIZAR"/></p>
                </form>

                <form id="form3" action="FrontController" method="POST">
                    <br><br><br><h3>Alterar Username </h3>
                    <p><input type="text" name="username" placeholder="nome de usuário" required="required"/></p>
                    <input type="hidden" name="command" value="UserProj"/>
                    <input type="hidden" name="action" value="updateUserNameUser"/>
                    <p><input type="submit" value="ATUALIZAR"/></p>
                </form>

                <form id="form4" action="FrontController" method="POST">
                    <br><br><br><h3>Alterar Email</h3>
                    <p><input type="email" name="email" placeholder="email" required="required"/></p>
                    <input type="hidden" name="command" value="UserProj"/>
                    <input type="hidden" name="action" value="updateEmailUser"/>
                    <p><input type="submit" value="ATUALIZAR"/></p>
                </form>
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