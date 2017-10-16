<%-- 
    Document   : register
    Created on : 09/05/2016, 20:15:16
    Author     : 31449530
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Cadastro </title>
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
        <!-- Site -->
        <div class="site">

            <!-- Header -->
            <div id="header" class="skel-panels-fixed">
                <div id="logo">
                    <h1>GoPlaces</h1>
                </div>
                <nav id="nav">
                    <ul>
                        <li><a href="index.jsp">Voltar</a></li>
                    </ul>
                </nav>
            </div>

            <!-- Banner -->
            <div id="banner" class="container">
                <section>
                    <c:if test="${successmsg!=null && !''.equals(successmsg)}">
                        <p class="success">${successmsg}</p>
                        <c:set scope="session" var="successmsg" value=""></c:set>
                    </c:if>

                    <c:if test="${errormsg!=null && !''.equals(errormsg)}">
                        <p class="error">${errormsg}</p>
                        <c:set scope="session" var="successmsg" value=""></c:set>
                    </c:if>

                    <p> Insira os dados da sua conta: </p>
                    <form action="FrontController" method="POST">
                        <p> Nome:  <input type="text" name="fullname" placeholder="fullname" required/></p>
                        <p> Email: <input type="email" name="email"  placeholder="email" required/></p>          
                        <p> Data de Nascimento <input type="date" name="bday" placeholder="dd/mm/yyyy" required</p>                         
                        <p> Login: <input type="text" name="login" placeholder="login" maxlength="10" required/></p>
                        <p> Senha: <input type="password" name="password"  placeholder="password" required/></p> 
                        <p> Confirme a senha: <input type="password" name="password2" placeholder="confirme senha" required/></p>                             
                        <p> <input type="hidden" name="command" value="UserProj" </p>
                        <p> <input type="hidden" name="action" value="insert" </p>
                        <p> <input type="submit" value="Criar conta"/></p>
                    </form>
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
