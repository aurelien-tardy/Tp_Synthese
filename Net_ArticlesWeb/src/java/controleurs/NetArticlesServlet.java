/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import dal.Article;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import outils.Utilitaire;
import session.ArticleFacade;
import session.DomaineFacade;

/**
 *
 * @author Epulapp
 */
public class NetArticlesServlet extends HttpServlet {

    private String erreur = "";

    @EJB
    private DomaineFacade domaineFacade;

    @EJB
    private ArticleFacade articleFacade;

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
            if (demande.equalsIgnoreCase("dernierArticle.na")) {
                vueReponse = "/login.jsp";
            } else if (demande.equalsIgnoreCase("rechercher.na")) {
                vueReponse = rechercherArticles(request);
            } else if (demande.equalsIgnoreCase("listeArticlesDomaine.na")) {
                vueReponse = listerArticleDomaine(request);
            } else if (demande.equalsIgnoreCase("voirArticle.na")) {
                vueReponse = voirArticle(request);
            } else if (demande.equalsIgnoreCase("ajoutPanier.na")) {
                vueReponse = ajoutPanier(request);
            }

        } catch (Exception e) {
            erreur = Utilitaire.getExceptionCause(e);
        } finally {
            request.setAttribute("erreurR", erreur);
            request.setAttribute("pageR", vueReponse);
            // Par défaut la page à afficher est index.jsp
            // sauf s'il faut rediriger vers une fonction
            RequestDispatcher dsp = request.getRequestDispatcher("/index.jsp");
            if (vueReponse.contains(".na")) {
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

    private String rechercherArticles(HttpServletRequest request) throws Exception {
        try {
            request.setAttribute("titre", "Liste des articles d'un domaine");
            request.setAttribute("lDomainesR", domaineFacade.getFields());
            return "/rechercher.jsp";
        } catch (Exception e) {
            throw e;
        }
    }

    private String listerArticleDomaine(HttpServletRequest request) throws Exception {
        try {
            request.setAttribute("titre", "Liste des articles d'un domaine");
            request.setAttribute("lDomainesR", domaineFacade.getFields());
            if (request.getParameter("id_domaine") != null) {
                request.setAttribute("id_domaineR", request.getParameter("id_domaine"));
                request.setAttribute("lArticlesR", articleFacade.getArticlesByField(request.getParameter("id_domaine")));
            } else {
                request.setAttribute("id_domaineR", request.getParameter("cbDomaines"));
                request.setAttribute("lArticlesR", articleFacade.getArticlesByField(request.getParameter("cbDomaines")));
            }
            return "/rechercher.jsp";
        } catch (Exception e) {
            throw e;
        }
    }

    private String voirArticle(HttpServletRequest request) throws Exception {
        try {
            Article article = articleFacade.getArticleById(request.getParameter("id_article"));
            request.setAttribute("id_domaineR", article.getDomaine().getIdDomaine());
            request.setAttribute("articleR", article);
            return "/voirArticle.jsp";
        } catch (Exception e) {
            throw e;
        }
    }

    private String ajoutPanier(HttpServletRequest request) throws Exception{
        try {
            Article article = articleFacade.getArticleById(request.getParameter("id_article"));
            
            return "/panier.jsp";
        } catch (Exception e) {
            throw e;
        }
    }

}
