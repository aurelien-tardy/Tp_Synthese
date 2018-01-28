/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import dal.Achete;
import dal.Auteur;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import outils.Utilitaire;
import session.AuteurFacade;
import session.RedigeFacade;

/**
 *Servlet de connexion
 * 
 * @author Epulapp
 */
public class UserServlet extends HttpServlet {

    private String erreur = "";

    private AuteurFacade auteurF = new AuteurFacade();
    
    private RedigeFacade redigeF = new RedigeFacade();

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
        String demande;
        // Si aucune demande n'est satisfaite, c'est la vue index.jsp
        // qui sera affichée
        String vueReponse = "/index.jsp";
        erreur = "";
        try {
            demande = getDemande(request);
            if (demande.equalsIgnoreCase("login.cpt")) {
                vueReponse = login(request);
            } else if (demande.equalsIgnoreCase("connecter.cpt")) {
                vueReponse = connecter(request);
            } else if (demande.equalsIgnoreCase("deconnecter.cpt")) {
                vueReponse = deconnecter(request);
            }

        } catch (Exception e) {
            erreur = Utilitaire.getExceptionCause(e);
        } finally {
            request.setAttribute("erreurR", erreur);
            request.setAttribute("pageR", vueReponse);
            // Par défaut la page à afficher est index.jsp
            // sauf s'il faut rediriger vers une fonction
            RequestDispatcher dsp = request.getRequestDispatcher("/index.jsp");
            if (vueReponse.contains(".cpt")) {
                dsp = request.getRequestDispatcher(vueReponse);
            }
            dsp.forward(request, response);
        }

    }

    private String getDemande(HttpServletRequest request) {
        String demande = "";
        demande = request.getRequestURI();
        demande = demande.substring(demande.lastIndexOf("/") + 1);
        return demande;
    }

    /**
     * renvoie sur la page de login
     * 
     * @param request
     * @return
     * @throws Exception 
     */
    private String login(HttpServletRequest request) throws Exception {
        String vueReponse;
        try {
            vueReponse = "/login.jsp";
            return (vueReponse);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Vérifie que l'utilisateur a saisi le bon login et mot de passe
     *
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String connecter(HttpServletRequest request) throws Exception {
        String login, pwd;
        String vueReponse = "/index.jsp";
        erreur = "";
        try {
            login = request.getParameter("txtLogin");
            pwd = request.getParameter("txtPwd");
            Auteur auteur = auteurF.lireLogin(login);
            if (auteur != null) {
                if (auteur.getPwdAuteur().equals(pwd)) {
                    vueReponse = "/accueil.jsp";
                    HttpSession session = request.getSession(true);
                    session.setAttribute("userId", auteur.getIdAuteur());
                    request.setAttribute("clientR", auteur);
                    // permet d'afficher la page des acquisition dès la connexion
                    List<Achete> lAchats = redigeF.getArticlesAcheteByAuteurId(Integer.toString((Integer) request.getSession().getAttribute("userId")));
                    request.setAttribute("lAchetesR", lAchats);
                } else {
                    erreur = "Login ou mot de passe inconnus !";
                }
            }
        } catch (Exception e) {
            erreur = e.getMessage();
        } finally {
            return (vueReponse);
        }
    }

    /**
     * Page de déconnexion
     * 
     * @param request
     * @return
     * @throws Exception 
     */
    private String deconnecter(HttpServletRequest request) throws Exception {
        String vueReponse;
        erreur = "";
        try {
            HttpSession session = request.getSession(true);
            session.removeAttribute("userId");
            vueReponse = "/login.jsp";
            return (vueReponse);
        } catch (Exception e) {
            throw e;
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
