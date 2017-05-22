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
public class Delete extends HttpServlet {

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
        request.setAttribute("users", UtenteFactory.getInstance().getListaUtenti());
        request.setAttribute("groups", GruppiFactory.getInstance().getListaGruppi());
        
        // Recupero della sessione
        HttpSession session = request.getSession(false);
        
        if (session != null && session.getAttribute("loggedIn") != null && session.getAttribute("loggedIn").equals(true)) {
            // Utente autenticato
            request.setAttribute("loggedUser", (Utente) UtenteFactory.getInstance().getUtenteById((int) session.getAttribute("idUtente")));
        
            String confirm;
            Utente user = (Utente) UtenteFactory.getInstance().getUtenteById((int) session.getAttribute("idUtente"));
            
            confirm = request.getParameter("confirm");
            String nome = request.getParameter("nome");
            String password = request.getParameter("password");
            
            if(confirm != null && confirm.equals("OK"))
            {
                if(nome.equals(user.getNome()) && password.equals(user.getPassword()))
                {
                    if(UtenteFactory.getInstance().cancellaUtente(user))
                    {
                        //Disconnetto l'utente
                        if(session != null){
                            session.invalidate();
                        }
                        request.removeAttribute("loggedUser");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                    else
                    {
                        request.getRequestDispatcher("profilo.jsp").forward(request, response);
                    }
                }
                else
                {
                    request.getRequestDispatcher("profilo.jsp").forward(request, response);
                }
            }
            else
            {
                request.setAttribute("confirm", confirm);
                request.getRequestDispatcher("delete.jsp").forward(request, response);
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
