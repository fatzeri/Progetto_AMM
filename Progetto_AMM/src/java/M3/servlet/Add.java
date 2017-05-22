/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M3.servlet;

import M3.classi.Gruppo;
import M3.classi.GruppiFactory;
import M3.classi.Post;
import M3.classi.PostFactory;
import M3.classi.Utente;
import M3.classi.UtenteFactory;
import java.io.IOException;
import java.sql.SQLException;
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
public class Add extends HttpServlet {

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
        
        
        request.setAttribute("page", "bacheca");
        request.setAttribute("users", UtenteFactory.getInstance().getListaUtenti());
        request.setAttribute("groups", GruppiFactory.getInstance().getListaGruppi());
                
        // Recupero della sessione
        HttpSession session = request.getSession(false);
        
        if (session != null && session.getAttribute("loggedIn") != null && session.getAttribute("loggedIn").equals(true)) {
             // Utente autenticato
            request.setAttribute("loggedUser", (Utente) UtenteFactory.getInstance().getUtenteById((int) session.getAttribute("idUtente")));
            
            String idGroup = request.getParameter("idGroup");
            String idUser = request.getParameter("idUser");
            
            int loggedUser = ((Utente) UtenteFactory.getInstance().getUtenteById((int) session.getAttribute("idUtente"))).getId();
            //sto chiedendo l'amicizia ad un utente
            if(idGroup.equals("-1"))
            {
                UtenteFactory.getInstance().addHearths(loggedUser, Integer.parseInt(idUser));
                
                request.getRequestDispatcher("Bacheca?user=" + idUser).forward(request, response);
            }
            //sto aggiungendomi ad un gruppo
            else if(idUser.equals("-1"))
            {
                GruppiFactory.getInstance().addAtGroup(loggedUser, Integer.parseInt(idGroup));
                request.getRequestDispatcher("Bacheca?group=" + idGroup).forward(request, response);
            }
            
        }
        else {
            // utente non autenticato
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
