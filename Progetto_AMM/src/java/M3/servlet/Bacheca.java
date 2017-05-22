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
public class Bacheca extends HttpServlet {

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
            
            String paramUtente = request.getParameter("user");
            String paramGruppo = request.getParameter("group");
            
            int idUtente;
            int idGruppo;
            if(paramUtente != null){
                // utente richiesto
                idUtente = Integer.parseInt(paramUtente);
            }
            else {
                // utente loggato
                idUtente = (int) session.getAttribute("idUtente"); 
            }
           
            // dati user bacheca
            Utente user = (Utente) UtenteFactory.getInstance().getUtenteById(idUtente);
            request.setAttribute("user", user);
            
            if(paramGruppo != null){
            //lista posts gruppo
                idGruppo = Integer.parseInt(paramGruppo);
                
                Gruppo group = (Gruppo) GruppiFactory.getInstance().getGruppoById(idGruppo);
                request.setAttribute("group", group);
                //Assegno a where 0 per indicare che siamo in un gruppo
                request.setAttribute("where", 0);
                List<Post> posts = PostFactory.getInstance().getPostList(group);
                request.setAttribute("posts", posts);
                if(GruppiFactory.getInstance().controllaApp(((Utente) UtenteFactory.getInstance().getUtenteById((int) session.getAttribute("idUtente"))).getId(), idGruppo))
                    request.setAttribute("app", 1);
                else
                    request.setAttribute("app", 0);
            }
            else{
            // lista posts utente
                List<Post> posts = PostFactory.getInstance().getPostList(user);
                //Assegno a where 0 per indicare che siamo in un utente
                request.setAttribute("where", 1);
                request.setAttribute("posts", posts);
                //controllo se l'utente loggato e l'utente proprietario della bacheca che voglio guardare sono amici
                if(UtenteFactory.getInstance().controllaApp(((Utente) UtenteFactory.getInstance().getUtenteById((int) session.getAttribute("idUtente"))).getId(), user.getId()))
                    request.setAttribute("app", 1);
                else
                    request.setAttribute("app", 0);
            }
            // Bacheca
           
            request.getRequestDispatcher("bacheca.jsp").forward(request, response);
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