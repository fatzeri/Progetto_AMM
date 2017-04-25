<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>  

<html>  
    <!--  head -->
    <jsp:include page="head.jsp"/>
    <body>
        
        <header>
            <div id="titleDescr">
                NERD BOOK
            </div>
        </header>
        <div id="divBody">
            <div id="subtitle">
                 <h1>Entra nel mondo del Nerd Book!!</h1>
                    <nav>
                        <ul>
                            <li><a href="login.jsp">Effettua il login</a></li>
                        </ul>
                    </nav>
            </div>
            <div id="sommario">
                <h2>Sommario</h2>
                    <ol>
                        <li><a href="#LaGiente">La Giente</a></li>
                        <li><a href="#ComeIscriversi">Come Iscriversi</a></li>
                        <li><a href="#Costi">Costi & Pagamenti</a></li>
                    </ol>   
            </div>
            <div id="LaGiente">
                <h2>La Giente</h2>
                    <h3>Ragazzi</h3>
                        <p>Qui ci puoi trovare ragazzi di ogni età!</p>
                    <h3>Ragazze</h3>
                        <p>Se trovi delle ragazze ti paghiamo noi!</p>
                    <h3>Animali</h3>
                        <p>Gli animali sono ben accetti!Tengono compagnia!</p>
            </div>
            <div id="ComeIscriversi">
                <h2>Come iscriversi</h2>
                    <h3>Sul nostro sito</h3>
                        <p>Vai sulla pagina principale, e clicca registrati!</p>
                    <h3>Tramite link</h3>
                        <p>Premi su questo link e prosegui con la registrazione: www.fidati.com</p>
            </div>
            <div id="Costi">
                <h2>Costi & pagamenti</h2>
                    <h3>Utente premium</h3>
                        <p>Ti costerà l'ira di dio</p>
                    <h3>Utente burdo</h3>
                        <p>Per te è gratis</p>
            </div>  
        </div>
    </body>
</html>
