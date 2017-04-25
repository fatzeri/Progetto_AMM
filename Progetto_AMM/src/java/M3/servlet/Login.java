/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M3.servlet;

import M3.classi.Utente;
import M3.classi.UtenteFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Federico
 */
public class Login extends HttpServlet {

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
        
        // Recupero della sessione
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("loggedIn") != null && session.getAttribute("loggedIn").equals(true)) {

            // Utente autenticato
            request.setAttribute("loggedUser", (Utente) UtenteFactory.getInstance().getUtenteById((int) session.getAttribute("idUtente")));
            
            // Bacheca
            if(((Utente) request.getAttribute("loggedUser")).controllaProfilo())
                request.getRequestDispatcher("Bacheca").forward(request, response);
            // Profilo
            else
                request.getRequestDispatcher("Profilo").forward(request, response); 
        } else {

            // Utente non autenticato 
            
            // recupero dei dati di login dal form
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username == null && password == null) {
                
                // form del login
                request.getRequestDispatcher("login.jsp").forward(request, response);
                
            }
            else {

                // tentativo di login
                Utente userLogged = this.login(username, password);

                if (userLogged != null) {

                    // login utente
                    session = request.getSession();
                    session.setAttribute("loggedIn", true);
                    session.setAttribute("idUtente", userLogged.getId());
                    request.getRequestDispatcher("Login").forward(request, response);
                }
                else {

                    // dati errati
                    request.setAttribute("datiErrati", "I dati inseriti non sono corretti");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
        }
    }

    private Utente login(String username, String password) {

        for (Utente user : UtenteFactory.getInstance().getListaUtenti()) {

            if (user.getNome().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
                return null;
            }
        }
        return null;
    
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
