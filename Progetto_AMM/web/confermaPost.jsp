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
            
            <div id="listaPost">
                <div class="conferm">
                    <h2>
                        Stai tentando di scrivere nella bacheca di ${nome} ${cognome}, confermare?
                    </h2>
                </div>
                <div class="post">
                    <div class="name">
                        <img class="img1" alt="foto di ${loggedUser.getNome()} ${loggedUser.getCognome()}" src="${loggedUser.getUrlFotoProfilo()}">
                        <div class="text">${loggedUser.getNome()} ${loggedUser.getCognome()}</div>
                    </div>
                    <div class="contenuto">
                        <c:if test="${postType == 'TEXT'}">
                            <p>${textPost}</p>
                        </c:if>
                        <c:if test="${postType == 'IMAGE'}">
                            <p>${textPost}</p>
                            <img class="img" alt="Foto del post" src="${allegato}">
                        </c:if>
                        <c:if test="${postType == 'LINK'}">
                            <p>${textPost}</p>
                            <a href="${allegato}">Clicca qui per visualizzare il link</a>
                        </c:if>    
                    </div>
                </div>
                <form action="NuovoPost" method="post">
                    <input type="hidden" name="idUtente" value="${user.getId()}">
                    <input type="hidden" name="idGruppo" value="${idGruppo}">
                    <input type="hidden" name="mexConferma" value="${mexConferma}">
                    <input type="hidden" name="postType" value="${postType}">
                    <input type="hidden" name="conferma" value="OK">
                    <input type="hidden" name="allegato" value="${allegato}"/>
                    <input type="hidden" name="textPost" value="${textPost}"/>
                    <button type="submit">Conferma</button>
                </form>
            </div>
        </div>
    </body>
</html>
