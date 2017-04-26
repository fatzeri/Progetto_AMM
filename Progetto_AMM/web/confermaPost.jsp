<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>  
    <!--  head -->
    <jsp:include page="head.jsp"/>
    <body>
        <div id="confermaPost">
            <form action="NuovoPost" method="post">
                <div class="conferm">
                    <h2>
                        Stai tentando di scrivere nella bacheca di ${nome}, confermare?
                    </h2>
                </div>
                <div class="contenutoConferma"">
                    <h3>Autore: ${loggedUser.getNome()} ${loggedUser.getCognome()}</h3>
                    <h3>Destinatario: ${nome} ${cognome}</h3>
                    <h3> Messaggio: ${textPost}</h3>
                    <h3> Allegato: ${allegato}</h3>
                </div>
                    
                <input type="hidden" name="idUtente" value="${user.getId()}">
                <input type="hidden" name="mexConferma" value="${mexConferma}">
                <input type="hidden" name="conferma" value="OK">
                <button type="submit">Conferma</button>
            </form>
        </div>
    </body>
</html>
