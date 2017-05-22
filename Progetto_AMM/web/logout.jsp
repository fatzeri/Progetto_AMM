<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="logout">
    <div id="logoutNome">${loggedUser.getNome()} ${loggedUser.getCognome()}</div>
    <div class="logoutLink"><a href="Logout">Logout</a>
    <c:if test = "${page == 'profilo'}"><a href="Delete">Discrivimi</a></div></c:if>
</div>
