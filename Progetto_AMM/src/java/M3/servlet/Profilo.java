/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M3.servlet;

import M3.classi.GruppiFactory;
import M3.classi.Post;
import M3.classi.PostFactory;
import M3.classi.Utente;
import M3.classi.UtenteFactory;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Federico
 */
public class Profilo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        request.setAttribute("page", "profilo");

        // Recupero della sessione
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("loggedIn") != null && session.getAttribute("loggedIn").equals(true)) {

            // Utente autenticato
            request.setAttribute("loggedUser", (Utente) UtenteFactory.getInstance().getUtenteById((int) session.getAttribute("idUtente")));

            Utente user = new Utente();

            if (request.getParameter("nome") != null) {

                // Controllo id utente
                if(((Utente) request.getAttribute("loggedUser")).getId() == (Integer.parseInt(request.getParameter("idUtente")))) {

                    // Dati inviati dal form
                    user.setNome((String) request.getParameter("nome"));
                    user.setCognome((String) request.getParameter("cognome"));
                    user.setDataNascita( Date.valueOf( request.getParameter("data")));
                    user.setFrasePresentazione((String) request.getParameter("presentazione"));
                    user.setPassword((String) request.getParameter("password"));
                    user.setUrlFotoProfilo((String) request.getParameter("url"));
                    
                    // Dati aggiornati
                    request.setAttribute("aggiornati", "I dati sono stati aggiornati!");
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("profilo.jsp").forward(request, response);
                }
                else {
                   
                    // Tentativo di modifica di un profilo non proprio
                    response.getWriter().println("<h1> Accesso Negato! </h1>");
                    response.getWriter().println("<p>Stai tentando di modificare un profilo non tuo!</p>");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
            }
            else {

                // Visita profilo utente loggato
                user = (Utente) request.getAttribute("loggedUser");
                request.setAttribute("user", user);
                request.getRequestDispatcher("profilo.jsp").forward(request, response);
            }
        }
        else {

            // Utente non autenticato
            response.getWriter().println("<h1> Accesso Negato! </h1>");
            response.getWriter().println("<p> Non hai effettuato il login! </p>");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
   
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
