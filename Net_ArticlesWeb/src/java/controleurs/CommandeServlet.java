/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import dal.Achete;
import dal.Article;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import outils.Utilitaire;
import session.AcheteFacade;
import session.ArticleFacade;
import session.DomaineFacade;

/**
 *
 * @author Epulapp
 */
public class CommandeServlet extends HttpServlet {

    private String erreur = "";

    private DomaineFacade domaineFacade = new DomaineFacade();

    private ArticleFacade articleFacade = new ArticleFacade();
    
    private AcheteFacade acheteFacade = new AcheteFacade();

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
            if (demande.equalsIgnoreCase("voirPanier.cde")) {
                vueReponse = "/panier.jsp";
            } else if (demande.equalsIgnoreCase("ajoutPanier.cde")) {
                vueReponse = ajoutPanier(request);
            } else if (demande.equalsIgnoreCase("supprimerPanier.cde")) {
                vueReponse = supprimerPanier(request);
            } else if (demande.equalsIgnoreCase("listeAchats.cde")) {
                vueReponse = mesArticles(request);
            } else if (demande.equalsIgnoreCase("validerPanier.cde")) {
                vueReponse = validerPanier(request);
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

    private String ajoutPanier(HttpServletRequest request) throws Exception {
        try {
            HttpSession session = request.getSession();
            List<Article> panier = null;
            if (session.getAttribute("panier") == null) {
                panier = new ArrayList<>();
            } else {
                panier = (ArrayList<Article>) session.getAttribute("panier");
            }

            Article article = articleFacade.getArticleById(request.getParameter("id_article"));
            if (panier.size() > 0) {
                boolean add = true;
                for (Article art : panier) {
                    if (article.equals(art)) {
                        add = false;
                    }
                }

                if (add) {
                    panier.add(article);
                } else {
                    erreur = "L'article est déjà présent dans le panier";
                }
            } else {
                panier.add(article);
            }

            session.setAttribute("panier", panier);
            session.setAttribute("montantTotalR", montantTotal(panier));

            return "/panier.jsp";
        } catch (Exception e) {
            throw e;
        }
    }

    private String validerPanier(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("panier") != null) {
            try {
                Integer idClient = (int) session.getAttribute("clientId");
                List<Article> panier = (ArrayList<Article>) session.getAttribute("panier");
                for (Article article : panier) {
                    Achete achat = new Achete(idClient, article.getIdArticle());
                    acheteFacade.validerPanier(achat);
                }
                session.removeAttribute("panier");
                session.removeAttribute("montantTotalR");    
            } catch (Exception e) {
                throw e;
            }
        }
        List<Achete> lAchete = acheteFacade.getListAcheteByIdClient((Integer) request.getSession().getAttribute("clientId"));
        request.setAttribute("lAchetesR", lAchete);
        return "/listeAchats.jsp";
    }

    private String supprimerPanier(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        List<Article> panier = (ArrayList<Article>) session.getAttribute("panier");
        Article article = articleFacade.getArticleById(request.getParameter("id_article"));
        int i = 0;
        for (Article art : panier) {
            if (article.equals(art)) {
                panier.remove(i);
                break;
            } else {
                i++;
            }
        }

        if(panier.size() > 0) {
            session.setAttribute("panier", panier);
        } else {
            session.removeAttribute("panier");
            List<Achete> lAchete = acheteFacade.getListAcheteByIdClient((Integer) request.getSession().getAttribute("clientId"));
            request.setAttribute("lAchetesR", lAchete);
            return "/listeAchats.jsp";
        }
        
        session.setAttribute("montantTotalR", montantTotal(panier));

        return "/panier.jsp";
    }

    private String mesArticles(HttpServletRequest request) throws Exception {
        List<Achete> lAchete = acheteFacade.getListAcheteByIdClient((Integer) request.getSession().getAttribute("clientId"));
        request.setAttribute("lAchetesR", lAchete);
        return "/listeAchats.jsp";
    }

    private int montantTotal(List<Article> panier) {
        int total = 0;
        for (Article art : panier) {
            total += art.getPrix().intValue();
        }
        return total;
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
