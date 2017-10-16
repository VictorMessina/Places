<%-- 
    Document   : index
    Created on : 09/05/2016, 20:13:01
    Author     : 31449530
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>GoPlaces</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.dropotron.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/skel-layers.min.js"></script>
        <script src="js/init.js"></script>
        <noscript>
        <link rel="stylesheet" href="css/skel.css" />
        <link rel="stylesheet" href="css/style.css" />
        </noscript>
    </head>

    <body>

        <!-- Site -->
        <div class="site">

            <!-- Header -->
            <div id="header" class="skel-panels-fixed">
                <div id="logo">
                    <h1>GoPlaces</h1>
                </div>
            </div>

            <!-- Banner -->
            <div id="banner" class="container">
                <p> Bem vindo(a) ao <strong>GoPlaces</strong>!</p>
                <br><br>
                <c:if test="${successmsg!=null && !''.equals(successmsg)}">
                    <p class="success">${successmsg}</p>
                    <c:set scope="session" var="successmsg" value=""></c:set>
                </c:if>

                <c:if test="${errormsg!=null && !''.equals(errormsg)}">
                    <p class="error">${errormsg}</p>
                    <c:set scope="session" var="successmsg" value=""></c:set>
                </c:if>
                <br><br>

                <section>
                    <div class="row no-collapse-1">

                        <section class="6u"> 
                            <p> Já possui conta? Faça login! </p>
                            <form action="FrontController" method="POST">
                                <p><input type="text" name="login" placeholder="login" required/></p>
                                <p><input type="password" name="password" placeholder="password" required/></p>
                                <p> <input type="hidden" name="command" value="UserProj" </p>
                                <p> <input type="hidden" name="action" value="login" </p>
                                <p><input type="checkbox" name="lembrar"> lembrar? </p>
                                <p><input type="submit" value="LOGIN"/></p>
                            </form>
                        </section>

                        <section class="6u"> 
                            <p> Ainda não se cadastrou? </p>
                            <p> Comece já para encontrar locais incríveis! </p>
                            <form action="register.jsp">
                                <p> <input type="submit" value="SING UP"/></p>
                            </form>
                        </section>

                    </div>	
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