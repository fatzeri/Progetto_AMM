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
                        <li class="attuale"><a href="Profilo">Profilo</a></li>
                        <li><a href="Bacheca">Bacheca</a></li>
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
                <form action="Profilo" method="post">
                    <div class="campo">
                        <label for="nome">Nome</label>
                        <input type="text" name="nome" id="nome" value="${user.getNome()}">
                    </div>
                    <div class="campo">
                        <label for="cognome">Cognome</label>
                        <input type="text" name="cognome" id="cognome" value="${user.getCognome()}">
                    </div>
                    <div class="campo"> 
                        <label for="url">URL</label>
                        <input type="url" name="url" id="url" value="${user.getUrlFotoProfilo()}">
                    </div>
                    <div class="campo">
                        <label for="presentazione">Frase di presentazione</label>
                        <textarea name="presentazione" id="presentazione" >${user.getFrasePresentazione()}</textarea>
                    </div>
                    <div class="campo">
                        <label for="data">Nato il</label>
                        <input type="date" name="data" id="data" value="${user.getDataNascita()}">
                    </div>
                    <div class="campo">
                        <label for="password">Password</label>
                        <input type="password" name="password" id="password" value="${user.getPassword()}">
                    </div>
                    
                    <input type="hidden" name="idUtente" value="${user.getId()}">
                    
                    <button type="submit">Aggiorna</button>
                    
                    <div class="messaggio">${aggiornati}</div>
                </form>
            </div>
        </div>
    </body>
</html>

