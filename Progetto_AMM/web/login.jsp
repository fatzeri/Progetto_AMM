<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>  
    <jsp:include page="head.jsp"/>
    <body>
        
        <header id="headerLogin">
            <div id="imgLogin">
                <img id="nerd" alt="NB" src="img/nb.jpg">
            </div>
            <div id="title1">
                NERDBOOK
            </div>
        </header>
        
        <div id="divBodyLogin">
            <div id="loginBody">
                <form action="Login" method="post">
                    <div class="formLogin">
                        <label for="username">Username</label>
                        <input type="text" name="username" id="username">
                    </div>
                    <div class="formLogin">
                        <label for="password">Password</label>
                        <input type="password" name="password" id="password">
                    </div>
                    <div class="formLogin">
                    <button type="submit">Accedi</button>
                    </div>
                </form>
                <div id="datiErrati">${datiErrati}</div>
            </div>
            
        </div>
    </body>
</html>
