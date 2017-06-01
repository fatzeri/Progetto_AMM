<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        
<div class="menuSx">
    <div>
        <label for="cerca"></label>
        <input type="text" name="cerca" value="" id="cerca" >
        <button id="search">Filter</button>
    </div>
    <div class="subMenu">
        <h2>Persone</h2>
        <ol>
            <div id="utenti">
            <div class=" userData">
                <c:forEach var="user" items="${users}">
                    <li><a href="Bacheca?user=${user.getId()}">${user.getNome()} ${user.getCognome()}</a></li>
                </c:forEach>
            </div>
            </div>
        </ol>
    </div>
    <div class="subMenu">
        <h2>Gruppi</h2>
        <ol>
            <c:forEach var="group" items="${groups}">
                <li><a href="Bacheca?group=${group.getId()}">${group.getNome()}</a></li>
            </c:forEach>
        </ol>
    </div>
</div>
