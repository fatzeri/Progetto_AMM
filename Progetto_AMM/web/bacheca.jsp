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
                
                <div class="amico">
                    <c:if test = "${app == 1}">
                        <img class="hearth" alt="amicizia" src="img/amico.png">
                    </c:if>
                    <c:if test = "${app == 0}">
                        <c:if test="${where == 1}">
                            <a href="Add?idUser=${user.getId()}&idGroup=-1"><img class="hearth" alt="Foto amicizia" src="img/aggiungi_amico.png"></a>
                        </c:if>
                        <c:if test="${where == 0}">
                            <a href="Add?idGroup=${group.getId()}&idUser=-1"><img class="hearth" alt="Foto amicizia" src="img/aggiungi_amico.png"></a>
                        </c:if>    
                    </c:if>
                </div>
                <!-- frase di presentazione dell'utente -->
                <c:if test="${where == 1}">
                    <div class="frase">
                        ${user.getNome()} ${user.getCognome()} : ${user.getFrasePresentazione()} 
                    </div>
                </c:if>
                <c:if test="${where == 0}">
                    <div class="frase">
                        ${group.getNome()} : ${group.getFrasePresentazione()}
                    </div>
                </c:if>
                
                <c:if test = "${app == 1}">
                    <div id="formNewPost">
                        <form action="NuovoPost" method="post">
                            <div id="postContent">
                                <div>
                                    <textarea name="textPost" id="textPost">Scrivi qualcosa...</textarea>
                                </div>
                            </div>

                            <input type="hidden" name="idUtente" value="${user.getId()}">
                            <c:if test="${where == 1}">
                                <input type="hidden" name="idGruppo" value="">
                            </c:if> 
                            <!-- se sto cercando di scrivere in un gruppo passo come parametro anche l'id -->
                            <c:if test="${where == 0}">
                                <input type="hidden" name="idGruppo" value="${group.getId()}">
                            </c:if>    
                            <div>
                                <input type="radio" name="postType" value="TEXT" checked="checked" /> Testo
                                <input type="radio" name="postType" value="IMAGE" /> Immagine
                                <input type="radio" name="postType" value="LINK" /> Link
                            </div>
                            <div id="allegato">
                                <label for="allegato">Allegato</label>
                                <input type="text" name="allegato"/>
                            </div>
                            <div id="button"><button type="submit">Scrivi</button></div>
                        </form>
                    </div>
                </c:if>        
                <div class="messaggio">${mexConferma} ${nome}</div>
                <!-- lista dei post-->
                <c:forEach var="post" items="${posts}">
                    <div class="post">
                        <div class="name">
                            <img class="img1" alt="foto di ${post.user.getNome()} ${post.user.getCognome()}" src="${post.user.getUrlFotoProfilo()}">
                            <div class="text">${post.user.getNome()} ${post.user.getCognome()}</div>
                        </div>
                        <div class="contenuto">
                            <c:if test="${post.postType == 'TEXT'}">
                                <p>${post.text}</p>
                            </c:if>
                            <c:if test="${post.postType == 'IMAGE'}">
                                <p>${post.text}</p>
                                <img class="img" alt="Foto del post" src="${post.content}">
                            </c:if>
                            <c:if test="${post.postType == 'LINK'}">
                                <p>${post.text}</p>
                                <a href="${post.content}">Clicca qui per visualizzare il link</a>
                            </c:if>    
                        </div>
                    </div>
                </c:forEach>
                
            </div>
        </div>
    </body>
</html>
