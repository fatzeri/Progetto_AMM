<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="logout">
    <div id="logoutNome">${loggedUser.getNome()} ${loggedUser.getCognome()}</div>
    <div id="logoutLink"><a href="Logout">Logout</a></div>
</div>
