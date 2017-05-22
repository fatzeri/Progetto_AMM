<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>  
    <!--  head -->
    <jsp:include page="head.jsp"/>
    <body>
        <header class="headerProfBach">
            <div class="profBachHeaderDiv">
                <!-- titolo -->
                <jsp:include page="title.jsp"/>
                
                <nav class="navigazione">
                    <ol>
                        <li><a href="Profilo">Profilo</a></li>
                        <li class="attuale"><a href="Bacheca">Bacheca</a></li>
                    </ol>
                </nav>
                <!-- logout -->
                <jsp:include page="logout.jsp"/>
            </div>
        </header>
        <div id="divBody">
            <div id="colonnaSx">
                <jsp:include page="menuSx.jsp"/>
            </div>
            
            <div id="profilo">
                <form action="Delete" method="post">
                    <div class="campo">
                        <label for="nome">Nome</label>
                        <input type="text" name="nome" id="nome">
                    </div>
                    <div class="campo">
                        <label for="cognome">Password</label>
                        <input type="password" name="password" id="password">
                    </div>
                    <button type="submit">Conferma</button>
                    <input type="hidden" name="confirm" value="OK">
                </form>
            </div>
        </div>
    </body>
</html>

