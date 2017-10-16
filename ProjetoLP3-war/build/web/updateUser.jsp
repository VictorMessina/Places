<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Atualizar Usuário </title>
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
            <c:if test="${users.size()==1}">
                <h2><c:out value="ATENÇÃO: Lista de Usuários vazia"></c:out></h2>
            </c:if>
            <h2> Atualizar Usuário </h2> <br><br><br>

            <c:if test="${successmsg!=null && !''.equals(successmsg)}">
                <p class="success">${successmsg}</p>
                <c:set scope="session" var="successmsg" value=""></c:set>
            </c:if>

            <c:if test="${errormsg!=null && !''.equals(errormsg)}">
                <p class="error">${errormsg}</p>
                <c:set scope="session" var="successmsg" value=""></c:set>
            </c:if>
                
            <section>
                <p><b> Mudar Nome: </b></p>
                <form action="FrontController" method="POST">
                    <select name="idUser">
                        <option value="-1"> selecionar </option>
                        <c:forEach var="user" items="${listaUsuarios}">
                            <option value="${user.getIdUser()}"> ${user.getUserinfo().getFullname()} </option>
                        </c:forEach> 
                    </select>                    
                    <br><br><p><input type="text" name="nome" placeholder="novo nome" required/></p>
                    <input type="hidden" name="command" value="Adm" />
                    <input type="hidden" name="action" value="update" />
                    <input type="hidden" name="action2" value="updateName" />
                    <p> <input type="submit" value="Mudar Nome"/></p>
                </form>
            </section>

            <br><hr><br>

            <section>
                <p><b> Mudar Email: </b></p>
                <form action="FrontController" method="POST">
                    <select name="idUser">
                        <option value="-1"> selecionar </option>
                        <c:forEach var="user" items="${listaUsuarios}">
                            <option value="${user.getIdUser()}"> ${user.getUserinfo().getFullname()} </option>
                        </c:forEach> 
                    </select>
                    <br><br><p><input type="text" name="email" placeholder="novo email" maxlength="30" required/></p>
                    <input type="hidden" name="command" value="Adm" />
                    <input type="hidden" name="action" value="update" />
                    <input type="hidden" name="action2" value="updateEmail" />
                    <p><input type="submit" value="Mudar Email"/></p>
                </form>
            </section>

            <br><hr><br>

            <section>
                <p><b> Mudar Data de Nascimento: </b></p>
                <form action="FrontController" method="POST">
                    <select name="idUser">
                        <option value="-1"> selecionar </option>
                        <c:forEach var="user" items="${listaUsuarios}">
                            <option value="${user.getIdUser()}"> ${user.getUserinfo().getFullname()} </option>
                        </c:forEach> 
                    </select>
                    <br><br><p><input type="date" name="bday" placeholder="dd/mm/yyyy" required/></p>
                    <input type="hidden" name="command" value="Adm" />
                    <input type="hidden" name="action" value="update" />
                    <input type="hidden" name="action2" value="updateBday" />                    
                    <p> <input type="submit" value="Mudar data"/></p>
                </form>
            </section>

            <br><hr><br>

            <section>
                <p><b> Mudar Username: </b></p>
                <form action="FrontController" method="POST">
                    <select name="idUser">
                        <option value="-1"> selecionar </option>
                        <c:forEach var="user" items="${listaUsuarios}">
                            <option value="${user.getIdUser()}"> ${user.getUserinfo().getFullname()} </option>
                        </c:forEach> 
                    </select>
                    <br><br><p><input type="text" name="username" placeholder="novo username" maxlength="10" required/></p>
                    <input type="hidden" name="command" value="Adm" />
                    <input type="hidden" name="action" value="update" />
                    <input type="hidden" name="action2" value="updateUserName" />
                    <p> <input type="submit" value="Mudar username"/></p>
                </form>
            </section>

            <br><hr><br>

            <section>
                <p><b> Mudar Senha: </b></p>
                <form action="FrontController" method="POST">
                    <select name="idUser">
                        <option value="-1"> selecionar </option>
                        <c:forEach var="user" items="${listaUsuarios}">
                            <option value="${user.getIdUser()}"> ${user.getUserinfo().getFullname()} </option>
                        </c:forEach> 
                    </select>
                    <br><br><p><input type="password" name="password"  placeholder=" nova senha" maxlength="7" required/></p>
                    <br><br><p><input type="password" name="password2" placeholder=" Confirma nova senha" maxlength="7" required/></p>
                    <input type="hidden" name="command" value="Adm" />
                    <input type="hidden" name="action" value="update" />
                    <input type="hidden" name="action2" value="updatePassword" />
                    <p> <input type="submit" value="Mudar senha"/></p>
                </form>
            </section>

            <hr>
        </div>
    </body>
</html>