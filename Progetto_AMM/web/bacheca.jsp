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
                
                <!-- nuovo post 
                <div id="formNewPost">
                    <form action="servlet.java" method="post">
                        <div id="postContent">
                            <div>
                                <textarea name="textPost" placeholder="Scrivi qualcosa..." id="textPost"></textarea>
                            </div>
                        </div>
                        <button type="submit">Scrivi</button>
                    </form>
                </div>
                -->
            
            <div id="listaPost">
                <!-- frase di presentazione dell'utente -->
                <div id="frase">
                    ${user.getNome()} ${user.getCognome()} : ${user.getFrasePresentazione()} 
                </div>
                
                <!-- lista dei post-->
                <c:forEach var="post" items="${posts}">
                    <div class="post">
                        <div class="name">
                            <img class="img1" alt="foto di ${user.getNome()} ${user.getCognome()}" src="${user.getUrlFotoProfilo()}">
                            <div class="text">${user.getNome()} ${user.getCognome()}</div>
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
                                <a href="${post.content}">#AMALA</a>
                            </c:if>    
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
